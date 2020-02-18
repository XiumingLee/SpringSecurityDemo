package cn.xiuminglee.security.controller;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author Xiuming Lee
 * @Description
 */
@Controller
@SessionAttributes("authorizationRequest")  // {@see AuthorizationEndpoint}中也是用的此方法。
// @SessionAttributes 只能作用在类上，作用是将指定的Model中的键值对添加至session中，方便在下一次请求中使用。
public class SecurityController {

    // 自定义登录页
    @RequestMapping("/customLogin")
    public String loginPage() {
        return "customLogin"; // html页面名称
    }


    // 自定义授权页  ；这个方法参数`ModelMap model`；使用`Map<String,Object> model`也可以
    @RequestMapping("/custom/confirm_access")
    public ModelAndView getAccessConfirmation(ModelMap model) throws Exception {
        // 获取授权请求。需要上面@SessionAttributes注解的帮助。
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");

        ModelAndView view = new ModelAndView();

        view.setViewName("customGrant"); // html页面名称

        view.addObject("clientId", authorizationRequest.getClientId());
        view.addObject("scopes", authorizationRequest.getScope());
        return view;
    }

}
