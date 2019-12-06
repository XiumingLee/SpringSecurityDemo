package cn.xiuminglee.security.rbac;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Xiuming Lee
 * @Description
 */
@Service("rbacService")
public class RbacServiceImpl implements RbacService {
    
    private AntPathMatcher antPathMatcher = new AntPathMatcher();


    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        // 这里我们可以通过Authentication到用户信息，进行查询数据库的操作。
        //User user = (User) authentication.getPrincipal();
        //System.out.println(user.getUsername());

        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        // 判断是否是匿名用户
        if (principal instanceof UserDetails){ // 不是匿名用户
            // POST /some/path.html HTTP/1.1 需要模糊匹配对比。
            String currentURI = request.getRequestURI();
            // example, GET, POST, or PUT.
            String currentMethod = request.getMethod();

            //读取用户所拥有的所有url
            // List<RBACEntity> urls = menuService.findUserUrlById(userId);
            List<RBACEntity> entityList = mockData();

            for (RBACEntity url : entityList){
                //判断urls中有没有和当前请求的url匹配的。
                if (antPathMatcher.match(url.getUrl(),currentURI) && currentMethod.equals(url.getMethod())){
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }

    /** 模拟数据，可使用数据库中查询出的数据 */
    private List<RBACEntity> mockData(){
        List<RBACEntity> entityList = new ArrayList<>();
        entityList.add(new RBACEntity("/api/needAdmin","GET"));
        entityList.add(new RBACEntity("/api/needAdmin","POST"));

        return entityList;
    }


}
