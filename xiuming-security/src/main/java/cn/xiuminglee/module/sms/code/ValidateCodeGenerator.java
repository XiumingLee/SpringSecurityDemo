package cn.xiuminglee.module.sms.code;

import cn.xiuminglee.module.sms.entity.SmsCode;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Xiuming Lee
 * @Description 生成验证码的接口。
 */
public interface ValidateCodeGenerator {
    SmsCode smsGenerate(HttpServletRequest request);
}
