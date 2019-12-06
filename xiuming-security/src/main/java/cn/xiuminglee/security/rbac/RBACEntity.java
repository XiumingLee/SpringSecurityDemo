package cn.xiuminglee.security.rbac;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Xiuming Lee
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RBACEntity {
    private String url;
    private String method;
}
