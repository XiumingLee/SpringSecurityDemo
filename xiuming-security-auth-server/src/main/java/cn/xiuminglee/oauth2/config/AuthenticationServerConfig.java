package cn.xiuminglee.oauth2.config;

import cn.xiuminglee.security.user.MingSecurityUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

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
    private MingSecurityUserDetailService mingSecurityUserDetailService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()
                .withClient("client1")
                .secret(passwordEncoder.encode("123456"))
                .authorizedGrantTypes("authorization_code","refresh_token")
                .redirectUris("http://127.0.0.1:3000/auth_code")
                .scopes("all")
                .autoApprove(false);
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.userDetailsService(mingSecurityUserDetailService);
        endpoints.tokenStore(jwtTokenStore()).accessTokenConverter(jwtAccessTokenConverter());
        endpoints.pathMapping("/oauth/confirm_access","/custom/confirm_access"); //使用自己的页面替换授权页面
    }

    /** 令牌访问端点的安全策略 */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()");
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("ming");
        return converter;
    }

}
