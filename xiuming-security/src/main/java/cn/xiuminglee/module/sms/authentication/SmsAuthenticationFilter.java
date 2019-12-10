package cn.xiuminglee.module.sms.authentication;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Xiuming Lee
 * @Description 短信授权过滤器
 */
public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    // 请求中携带手机号参数的名字mobile
    public static final String MOBILE_KEY = "mobile";
    private String mobileParameter = MOBILE_KEY;
    private boolean postOnly = true;

    // 处理的是"/mobileLogin"请求
    public SmsAuthenticationFilter() {
        super(new AntPathRequestMatcher("/mobileLogin", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String mobile = this.obtainMobile(request);
            if (mobile == null) {
                mobile = "";
            }

            mobile = mobile.trim();
            // 封装一个未授权的Token
            SmsAuthenticationToken smsAuthenticationToken = new SmsAuthenticationToken(mobile);
            this.setDetails(request,smsAuthenticationToken);
            return this.getAuthenticationManager().authenticate(smsAuthenticationToken);
        }
    }

    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(this.mobileParameter);
    }
    /**
     * 将请求的详情set到SmsAuthenticationToken中
     */
    protected void setDetails(HttpServletRequest request, SmsAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setMobileParameter(String mobileParameter) {
        Assert.hasText(mobileParameter, "Mobile parameter must not be empty or null");
        this.mobileParameter = mobileParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public String getMobileParameter() {
        return mobileParameter;
    }
}
