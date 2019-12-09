package cn.xiuminglee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

/**
 * @Author Xiuming Lee
 * @Description
 */
@SpringBootApplication
@EnableOAuth2Sso
public class ResourceTwoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResourceTwoApplication.class,args);
    }
}
