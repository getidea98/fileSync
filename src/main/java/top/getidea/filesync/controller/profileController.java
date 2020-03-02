package top.getidea.filesync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.getidea.filesync.DTO.MessageDTO;
import top.getidea.filesync.module.File;
import top.getidea.filesync.module.User;
import top.getidea.filesync.service.FileService;
import top.getidea.filesync.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description :
 * @Author : Getidea
 * @Date: 2020-03-01 19:49
 */
@Controller
public class profileController {

    @Autowired
    UserService userService;

    @Autowired
    FileService fileService;

    @PostMapping("/profile")
    @ResponseBody
    public Object getList(@RequestBody @Valid MessageDTO messageDTO) {
        Map<Object,Object> result = new HashMap<>();
        User user = userService.queryByAccount(messageDTO);
        //判断密码是否正确
        if(user != null){
            List<File> fileList = fileService.queryAllByAccount(user.getAccount());
            result.put("data",fileList);
            result.put("status",2);
        }else{
            result.put("status",1);
            result.put("data",null);
        }
        return result;
    }
}
