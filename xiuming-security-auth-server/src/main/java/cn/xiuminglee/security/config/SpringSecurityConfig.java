package cn.xiuminglee.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author Xiuming Lee
 * @Description Spring Security 配置类
 */
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .anyRequest().authenticated();
    }

    /**
     * 密码加盐加密
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        //Spring自带的每次会随机生成盐值，即使密码相同，加密后也不同
        return new BCryptPasswordEncoder();
    }
}
