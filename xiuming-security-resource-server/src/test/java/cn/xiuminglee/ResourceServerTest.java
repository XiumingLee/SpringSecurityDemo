package cn.xiuminglee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author Xiuming Lee
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceServerTest {


    @Test
    public void encode(){
        String encode = new BCryptPasswordEncoder().encode("123456");
        System.out.println(encode);
    }
}
