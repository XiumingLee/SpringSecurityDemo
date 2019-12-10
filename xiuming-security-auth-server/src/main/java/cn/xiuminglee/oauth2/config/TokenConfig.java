package cn.xiuminglee.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @Author Xiuming Lee
 * @Description
 */
@Configuration
public class TokenConfig {

    /**
     * 使用jwt时的配置，默认生效
     * matchIfMissing = true的意思就是系统中没有配置时，默认此配置生效
     */
    @Configuration
    @ConditionalOnProperty(prefix = "ming.security", name = "token-type", havingValue = "jwt", matchIfMissing = true)
    public static class JwtConfig {
        private String SIGNING_KEY = "Ming";

        @Bean
        public TokenStore tokenStore() {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setSigningKey(SIGNING_KEY); // 此处使用对称秘钥，资源服务器使用该秘钥来验证，没有使用rsa非对称加密
            return converter;
        }
    }

    /**
     * 使用redis存储token的配置，只有在ming.security.tokenType配置为redis时生效
     */
    @Configuration
    @ConditionalOnProperty(prefix = "ming.security", name = "token-type", havingValue = "redis")
    public static class RedisConfig {
        @Autowired
        private RedisConnectionFactory redisConnectionFactory;

        @Bean
        public TokenStore redisTokenStore() {
            return new RedisTokenStore(redisConnectionFactory);
        }
    }
}
