package cn.xiuminglee.security.handle;

import cn.xiuminglee.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Xiuming Lee
 * @Description Spring Security登录成功处理器
 */
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        //设置返回类型
        response.setContentType("application/json;charset=UTF-8");
        ResponseUtil responseBody = ResponseUtil.of(ResponseUtil.ResponseCode.SUCCESS,true,"登录成功！");
        response.getWriter().write(objectMapper.writeValueAsString(responseBody));
    }
}
