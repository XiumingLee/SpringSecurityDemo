package cn.xiuminglee.util;

import lombok.Getter;

import java.io.Serializable;

/**
 * @Author Xiuming Lee
 * @Description
 */
@Getter
public class ResponseUtil<T> implements Serializable {
    private String code;
    private Boolean flag;
    private T data;

    private ResponseUtil(String code, Boolean flag, T data) {
        this.code = code;
        this.flag = flag;
        this.data = data;
    }

    public static <T> ResponseUtil of(ResponseCode code, Boolean flag, T data){
        return new ResponseUtil(code.getValue(),flag,data);
    }


    public enum ResponseCode{
        SUCCESS("200"),
        NEED_LOGIN("403"),
        ERR("500");
        private final String value;

        ResponseCode(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }
}
