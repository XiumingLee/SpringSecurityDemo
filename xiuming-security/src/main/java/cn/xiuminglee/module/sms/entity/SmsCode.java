package cn.xiuminglee.module.sms.entity;

import java.time.LocalDateTime;

/**
 * @Author Xiuming Lee
 * @Description 短信验证码实体类
 */
public class SmsCode {
    //短信验证码
    private String code;
    //过期时间  LocalDateTime为java8新特性
    private LocalDateTime expireTime;
    // 构造函数
    public SmsCode(String code, LocalDateTime expireTime) { //传入过期的时间
        this.code = code;
        this.expireTime = expireTime;
    }
    public SmsCode(String code, int expireIn) {  //传入秒数
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }
    //是否过期
    public  boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
