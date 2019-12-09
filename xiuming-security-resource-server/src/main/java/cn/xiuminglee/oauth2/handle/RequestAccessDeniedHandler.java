package cn.xiuminglee.oauth2.handle;

import cn.xiuminglee.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Xiuming Lee
 * @Description
 */
@Component
public class RequestAccessDeniedHandler implements AccessDeniedHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ResponseUtil responseBody = ResponseUtil.of(ResponseUtil.ResponseCode.SUCCESS, false, "test--无权限访问");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(responseBody));
    }
}
