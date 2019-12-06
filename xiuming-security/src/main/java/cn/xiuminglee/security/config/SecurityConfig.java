package cn.xiuminglee.security.config;

import cn.xiuminglee.security.handle.LoginFailureHandle;
import cn.xiuminglee.security.handle.LoginSuccessHandler;
import cn.xiuminglee.security.handle.RequestAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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

    /** 内存中添加用户角色 */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // `{noop}123456`不加密。此处的角色不需要加"ROLE_"前缀
        auth.inMemoryAuthentication() .withUser("Xiuming") .password("{noop}123456") .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginProcessingUrl("/api/login")
                .successHandler(loginSuccessHandler) // 设置自己的登录成功处理器
                .failureHandler(loginFailureHandle); // 设置自己的登录失败处理器

        //允许所有用户访问"/"和"/api/login"
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/needAdmin").hasRole("ADMIN")
                .antMatchers("/api/needRoot").hasRole("ROOT")
                //其他地址的访问均需验证权限
                .anyRequest().authenticated();

        // AccessDeniedHandler处理器 拒绝访问时的处理器
        http.exceptionHandling()
                .accessDeniedHandler(requestAccessDeniedHandler);

        // 关闭csrf
        http.csrf().disable();
        //开启跨域
        http.cors();
    }
}
