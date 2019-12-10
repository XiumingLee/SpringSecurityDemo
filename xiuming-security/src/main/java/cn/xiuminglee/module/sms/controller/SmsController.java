package cn.xiuminglee.module.sms.controller;

import cn.xiuminglee.module.sms.code.ValidateCodeGenerator;
import cn.xiuminglee.module.sms.entity.SmsCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Xiuming Lee
 * @Description
 */
@RestController
public class SmsController {
    @Autowired
    private ValidateCodeGenerator validateCodeGenerator;

    @GetMapping("/validate/smsCode")
    public void smsCode(HttpServletRequest request, HttpServletResponse response){
        SmsCode smsCode = validateCodeGenerator.smsGenerate(request);
        // 输出生成的短信验证码
        System.out.println(smsCode.getCode());
    }
}
