package cn.xiuminglee.security.config;

import cn.xiuminglee.module.sms.authentication.SmsCodeFilter;
import cn.xiuminglee.security.handle.LoginFailureHandle;
import cn.xiuminglee.security.handle.LoginSuccessHandler;
import cn.xiuminglee.security.handle.RequestAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author Xiuming Lee
 * @Description Spring Security 配置文件
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailureHandle loginFailureHandle;
    @Autowired
    private RequestAccessDeniedHandler requestAccessDeniedHandler;

    @Autowired
    private SmsAuthenticationConfig smsAuthenticationConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 将验证短信验证码的过滤器添加到容器中
        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setAuthenticationFailureHandler(loginFailureHandle);
        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class);

        http.apply(smsAuthenticationConfig);

        http.formLogin()
                .loginProcessingUrl("/api/login")
                .successHandler(loginSuccessHandler) // 设置自己的登录成功处理器
                .failureHandler(loginFailureHandle); // 设置自己的登录失败处理器

        //允许所有用户访问"/"和"/api/login"
        http.authorizeRequests()
                .antMatchers("/","/validate/smsCode","/mobileLogin").permitAll()
                //其他地址的访问均需验证权限,参数Spring会自动注入进去
                .anyRequest().authenticated();
                //.anyRequest().access("@rbacService.hasPermission(request,authentication)");

        // AccessDeniedHandler处理器 拒绝访问时的处理器
        http.exceptionHandling()
                .accessDeniedHandler(requestAccessDeniedHandler);

        // 关闭csrf
        http.csrf().disable();
        //开启跨域
        http.cors();
    }

    /**
     * 密码加盐加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder (){
        return  new BCryptPasswordEncoder();
    }
}
