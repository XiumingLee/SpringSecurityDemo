package cn.xiuminglee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Xiuming Lee
 * @Description
 */
@Controller
public class MainController {
    /**
     * 返回主页面
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "index.html";
    }
}
