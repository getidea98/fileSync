package top.getidea.filesync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.getidea.filesync.module.User;
import top.getidea.filesync.provide.FileUtil;
import top.getidea.filesync.service.FileService;
import top.getidea.filesync.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @Description :
 * @Author : Getidea
 * @Date: 2020-03-01 15:24
 */

@RestController
public class DownLoadFileController {

    @Value("${save.folder}")
    String folder;

    @Autowired
    UserService userService;

    @Autowired
    FileUtil fileUtil;

    @Autowired
    FileService fileService;

    @RequestMapping("/downloadFile")
    @ResponseBody
    public Object downloadFile(HttpServletResponse response,
                             HttpServletRequest request,
                             @RequestParam(required = true)String fileName) {
        User user = (User)request.getSession().getAttribute("user");
        if(user != null){
            top.getidea.filesync.module.File file = fileService.queryByCreatorAndFileName(user.getAccount(),fileName);
            if(file != null){
                String result = fileUtil.downloadFile(response, file.getFileUrl());
                return result;
            }
        }
        return "fail";
    }
}
