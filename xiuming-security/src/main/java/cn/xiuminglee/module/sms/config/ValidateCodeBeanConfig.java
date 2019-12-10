package cn.xiuminglee.module.sms.config;

import cn.xiuminglee.module.sms.code.SmsCodeGenerator;
import cn.xiuminglee.module.sms.code.ValidateCodeGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Xiuming Lee
 * @Description
 */
@Configuration
public class ValidateCodeBeanConfig {

    /**
     *这句话的意思是当用户没有配置名字为smsValidateCodeGenerator的生成器时，走这里的默认方法。
     * */
    @Bean
    @ConditionalOnMissingBean(name = "smsValidateCodeGenerator")
    public ValidateCodeGenerator smsValidateCodeGenerator(){
        SmsCodeGenerator smsCodeGenerator = new SmsCodeGenerator();
        return smsCodeGenerator;
    }
}
