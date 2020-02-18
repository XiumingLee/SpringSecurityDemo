package cn.xiuminglee.security.config;

import cn.xiuminglee.security.handle.LoginSuccessHandler;
import cn.xiuminglee.security.handle.RequestAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Xiuming Lee
 * @Description
 */
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Autowired
    private RequestAccessDeniedHandler requestAccessDeniedHandler;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/api/login");

        http.authorizeRequests()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/needAdmin").hasRole("ADMIN")
                .antMatchers("/api/needRoot").hasRole("ROOT")
                //其他地址的访问均需验证权限
                .anyRequest().authenticated();
        http.exceptionHandling()
                .accessDeniedHandler(requestAccessDeniedHandler);

        // 关闭csrf
        http.csrf().disable();
        http.cors();
    }


    // 允许跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}