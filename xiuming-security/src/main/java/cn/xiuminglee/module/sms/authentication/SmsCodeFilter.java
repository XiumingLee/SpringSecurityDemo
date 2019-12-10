package cn.xiuminglee.module.sms.authentication;

import cn.xiuminglee.module.sms.entity.SmsCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Xiuming Lee
 * @Description 验证手机验证码是否正确
 */
public class SmsCodeFilter extends OncePerRequestFilter {

    // 引入spring提供的AuthenticationFailureHandler，并实现get/set方法。这样我们可以注入我们自定义的错误处理器
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws IOException, ServletException {
        // 判断是不是短信登录请求，
        if(StringUtils.equals("/mobileLogin",httpServletRequest.getRequestURI())
                && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(),"post")){
            try {
                validate(new ServletWebRequest(httpServletRequest));
            } catch (BadCredentialsException e) {
                // 如果捕获到异常用AuthenticationFailureHandler处理，将信息返回给前端。
                // 之前我们已经自己写过自定义的AuthenticationFailureHandler，该处抛给自定义的处理
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
                return;
            }
        }

        // 如果不是，调用后面的过滤器
        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        // 获取session的值
        SmsCode smsCode = (SmsCode) request.getRequest().getSession().getAttribute("sms_code");
        // 获取前台传过来的验证码
        String requestCode = ServletRequestUtils.getStringParameter(request.getRequest(), "code");
        // 清理验证码session
        request.getRequest().getSession().removeAttribute("sms_code");
        if (StringUtils.isBlank(requestCode)){
            throw new BadCredentialsException("验证码不能为空");
        } else if (smsCode == null){
            throw new BadCredentialsException("验证码不存在");
        } else if (!(StringUtils.equalsIgnoreCase(smsCode.getCode(),requestCode))){
            throw new BadCredentialsException("验证码错误");
        } else if (smsCode.isExpired()){
            throw new BadCredentialsException("验证码已过期");
        }
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }
}
