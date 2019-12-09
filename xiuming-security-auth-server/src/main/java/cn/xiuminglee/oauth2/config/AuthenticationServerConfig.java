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
        // 定义了两个客户端应用的通行证
        /**
        必须设置回调地址redirectUris,并且格式是`http://客户端IP:端口/login`的格式，
        否则会报:
        OAuth Error error=”invalid_request”,
        error_description=”At least one redirect_uri must be registered with the client.”
        的错误
        */
        clients.inMemory()
                .withClient("client1")
                .secret(passwordEncoder.encode("123456"))
                .authorizedGrantTypes("authorization_code","refresh_token")
                .redirectUris("http://127.0.0.1:8011/login")
                .scopes("all")
                .autoApprove(false)
                .and()
                .withClient("client2")
                .secret(passwordEncoder.encode("123456"))
                .redirectUris("http://127.0.0.1:8012/login")
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .scopes("all")
                .autoApprove(false);
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.userDetailsService(mingSecurityUserDetailService);
        endpoints.tokenStore(jwtTokenStore()).accessTokenConverter(jwtAccessTokenConverter());
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
