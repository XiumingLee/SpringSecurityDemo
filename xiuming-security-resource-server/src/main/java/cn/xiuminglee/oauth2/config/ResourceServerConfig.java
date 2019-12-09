package cn.xiuminglee.oauth2.config;

import cn.xiuminglee.oauth2.handle.RequestAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @Author Xiuming Lee
 * @Description 资源服务配置
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    // 资源服务id
    public static final String RESOURCE_ID = "client_1";

    @Autowired
    private RequestAccessDeniedHandler requestAccessDeniedHandler;

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID)
                //.tokenServices(tokenService())
                .tokenStore(tokenStore)
                .stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").access("#oauth2.hasScope('all')")
                .and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 设置无权访问时的处理器。
        http.exceptionHandling()
                .accessDeniedHandler(requestAccessDeniedHandler);
    }


    // 资源服务令牌解析服务
    //@Bean
    //public ResourceServerTokenServices tokenService() {
    //    //使用远程服务请求授权服务器校验token,必须指定校验token 的url、client_id，client_secret
    //    RemoteTokenServices service=new RemoteTokenServices();
    //    service.setCheckTokenEndpointUrl("http://127.0.0.1:8080/oauth/check_token");
    //    service.setClientId("client1");
    //    service.setClientSecret("123456");
    //    return service;
    //}
}
