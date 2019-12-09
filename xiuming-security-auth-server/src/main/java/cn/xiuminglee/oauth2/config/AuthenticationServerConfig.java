package cn.xiuminglee.oauth2.config;

import cn.xiuminglee.security.user.MingSecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @Author Xiuming Lee
 * @Description
 */
@Configuration
@EnableAuthorizationServer
public class AuthenticationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private MingSecurityUserDetailsService mingSecurityUserDetailsService;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client1")
                .secret(passwordEncoder.encode("123456"))
                // 该client允许的授权类型 authorization_code,password,refresh_token,implicit,client_credentials
                .authorizedGrantTypes("authorization_code", "password","client_credentials","implicit","refresh_token")
                .scopes("all")
                .autoApprove(false)
                // 加上验证回调地址
                .redirectUris("http://example.com/");
    }

    /** 令牌访问端点 */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 这里将MingSecurityUserDetailsService注入,让其使用我们自定义的UserDetailsService。
        endpoints.userDetailsService(mingSecurityUserDetailsService);
        endpoints.authenticationManager(authenticationManager); // 密码模式需要
        endpoints.authorizationCodeServices(authorizationCodeServices); // 授权码模式需要
        endpoints.tokenServices(tokenService()); // 令牌管理服务
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.POST); //允许post提交
    }

    /** 令牌访问端点的安全策略 */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()") //(1)   `/oauth/token_key`公开
                .checkTokenAccess("permitAll()") //(2)  `/oauth/check_token`公开
                .allowFormAuthenticationForClients(); //(3)
    }


    // 令牌管理
    @Bean
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices service=new DefaultTokenServices();
        service.setClientDetailsService(clientDetailsService);
        service.setSupportRefreshToken(true);
        service.setTokenStore(tokenStore);
        service.setAccessTokenValiditySeconds(7200); // 令牌默认有效期2小时
        service.setRefreshTokenValiditySeconds(259200); // 刷新令牌默认有效期3天
        return service;
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        //设置授权码模式的授权码如何 存取，暂时采用内存方式
        return new InMemoryAuthorizationCodeServices();
    }
}
