package cn.xiuminglee.security.config;

import cn.xiuminglee.security.handler.LoginFailureHandle;
import cn.xiuminglee.security.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author Xiuming Lee
 * @Description
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailureHandle loginFailureHandle;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginProcessingUrl("/api/login")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandle);

        //允许所有用户访问"/"和"/api/login"
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                //其他地址的访问均需验证权限
                .anyRequest().authenticated();

        // 关闭csrf
        http.csrf().disable();
        //开启跨域
        http.cors();
    }

    /**
     * 需要配置这个支持password模式
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
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
