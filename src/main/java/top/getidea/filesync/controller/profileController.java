package top.getidea.filesync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.getidea.filesync.module.File;
import top.getidea.filesync.module.User;
import top.getidea.filesync.service.FileService;
import top.getidea.filesync.service.UserService;

import javax.servlet.http.HttpServletRequest;
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
    public Object getList(HttpServletRequest httpServletRequest) {
        Map<Object,Object> result = new HashMap<>();
        User user = (User) httpServletRequest.getSession().getAttribute("user");
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

    @PostMapping("/profile/del/{id}")
    @ResponseBody
    public Object delFile(@PathVariable String id) {
        Map<Object,Object> result = new HashMap<>();
        if(fileService.delFileById(id) != 0){
            result.put("status",2);
        }else {
            result.put("status",1);
        }
        return result;
    }
}
