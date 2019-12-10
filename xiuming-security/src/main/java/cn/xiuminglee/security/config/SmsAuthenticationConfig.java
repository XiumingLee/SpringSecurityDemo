package cn.xiuminglee.security.config;

import cn.xiuminglee.module.sms.authentication.SmsAuthenticationFilter;
import cn.xiuminglee.module.sms.authentication.SmsAuthenticationProvider;
import cn.xiuminglee.security.handle.LoginFailureHandle;
import cn.xiuminglee.security.handle.LoginSuccessHandler;
import cn.xiuminglee.security.user.MingSecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @Author Xiuming Lee
 * @Description
 */
@Component
public class SmsAuthenticationConfig
        extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailureHandle loginFailureHandle;
    @Autowired
    private MingSecurityUserDetailsService mingSecurityUserDetailsService;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsAuthenticationFilter smsAuthenticationFilter = new SmsAuthenticationFilter();
        smsAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsAuthenticationFilter.setAuthenticationSuccessHandler(loginSuccessHandler);
        smsAuthenticationFilter.setAuthenticationFailureHandler(loginFailureHandle);

        SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider();
        smsAuthenticationProvider.setUserDetailsService(mingSecurityUserDetailsService);

        http.authenticationProvider(smsAuthenticationProvider)
                .addFilterAfter(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
