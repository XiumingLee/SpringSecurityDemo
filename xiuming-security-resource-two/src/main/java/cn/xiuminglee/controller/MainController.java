package cn.xiuminglee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Xiuming Lee
 * @Description
 */
@Controller
public class MainController {

    @RequestMapping("/")
    public String mainPage(){
        return "index.html";
    }
}
