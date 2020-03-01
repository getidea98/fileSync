package top.getidea.filesync.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.getidea.filesync.service.AuthorizeService;
import top.getidea.filesync.service.FileService;

/**
 * @Description :
 * @Author : Getidea
 * @Date: 2020-02-29 11:08
 */
@Controller
public class IndexController {

    @Autowired
    AuthorizeService authorizeService;

    @Autowired
    FileService fileService;

    @GetMapping("/")
    public String index(){
        return "signin";
    }
}
