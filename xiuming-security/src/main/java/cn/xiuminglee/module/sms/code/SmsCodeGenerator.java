package cn.xiuminglee.module.sms.code;

import cn.xiuminglee.module.sms.entity.SmsCode;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Xiuming Lee
 * @Description 验证码的默认实现，手机验证码生成。
 */
public class SmsCodeGenerator implements ValidateCodeGenerator {
    @Override
    public SmsCode smsGenerate(HttpServletRequest request) {
        // 生成6位短信验证码
        String code = RandomStringUtils.randomNumeric(6);
        // 过期时间60s
        SmsCode smsCode = new SmsCode(code,60);
        // 将短信验证码保存到session中
        request.getSession().setAttribute("sms_code",smsCode);
        return smsCode;
    }
}
