package cn.xiuminglee.security.config;

import cn.xiuminglee.security.handle.RequestAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author Xiuming Lee
 * @Description
 */
@EnableWebSecurity
@EnableOAuth2Sso
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RequestAccessDeniedHandler requestAccessDeniedHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/api/needAdmin").hasRole("ADMIN")
                .antMatchers("/api/needRoot").hasRole("ROOT")
                //其他地址的访问均需验证权限
                .anyRequest().authenticated();
        http.exceptionHandling()
                .accessDeniedHandler(requestAccessDeniedHandler);
    }
}