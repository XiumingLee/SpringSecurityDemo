package cn.xiuminglee.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author Xiuming Lee
 * @Description :配置文件
 */
@Component
@ConfigurationProperties(prefix = "ming.security")
public class MingSecurityProperties {

    /** redis是普通Token，生成后保存到redis中。
     * 可选`redis`和`jwt`  */
    private String tokenType;

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
