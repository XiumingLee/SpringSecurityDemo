package cn.xiuminglee.controller;

import cn.xiuminglee.util.ResponseUtil;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Xiuming Lee
 * @Description
 */
@RestController
@RequestMapping("/api")
public class SourceController {

    @GetMapping("/user")
    public Authentication user(Authentication user) {
        return user;
    }

    @RequestMapping("/needAdmin")
    public ResponseUtil adminRole(){
        return ResponseUtil.of(ResponseUtil.ResponseCode.SUCCESS,true,"访问成功：needAdmin");
    }

    @RequestMapping("/needRoot")
    public ResponseUtil rootRole(){
        return ResponseUtil.of(ResponseUtil.ResponseCode.SUCCESS,true,"访问成功：needRoot");
    }
}
