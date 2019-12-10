package cn.xiuminglee.module.sms.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @Author Xiuming Lee
 * @Description 短信登录Token
 */
public class SmsAuthenticationToken extends AbstractAuthenticationToken {

    /** 主要信息，这里是手机号 */
    private final Object principal;

    /** 未授权的Token */
    public SmsAuthenticationToken(Object principal) {
        super(null);
        this.principal = principal;
        // 表示未授权
        super.setAuthenticated(false);
    }

    /** 授权后的Token */
    public SmsAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principal) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
