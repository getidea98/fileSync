package top.getidea.filesync.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description :
 * @Author : Getidea
 * @Date: 2020-02-29 11:08
 */
@Controller
public class IndexController {

    @GetMapping("/hello")
    public String index(){
        return "hello";
    }
}
