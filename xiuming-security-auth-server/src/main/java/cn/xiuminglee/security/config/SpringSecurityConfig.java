package cn.xiuminglee.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Xiuming Lee
 * @Description Spring Security 配置类
 */
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/customLogin")
                .loginProcessingUrl("/auth/authorize");  // 注意这里的登录请求页是/auth/authorize。不是之前的/login

        http.authorizeRequests()
                .antMatchers("/oauth/**","/customLogin").permitAll()
                .anyRequest().authenticated();

        http.cors();
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
