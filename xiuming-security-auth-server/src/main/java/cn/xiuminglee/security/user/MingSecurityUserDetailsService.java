package cn.xiuminglee.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author Xiuming Lee
 * @Description
 */
@Service
public class MingSecurityUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 模拟从数据库中查询用户信息并添加到Spring Security，待与前端传来的进行比对
     * @param s 前端传来的用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if( !"xiuming".equals(s) ) {
            throw new UsernameNotFoundException("用户" + s + "不存在" );
        }
        User user = new User(s, passwordEncoder.encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
        return user;
    }
}
