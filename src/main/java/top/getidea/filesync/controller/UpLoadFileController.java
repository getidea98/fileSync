package top.getidea.filesync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.getidea.filesync.module.User;
import top.getidea.filesync.service.AuthorizeService;
import top.getidea.filesync.service.FileService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:文件上传处理
 * @Author : Getidea
 * @Date: 2020-02-29 12:09
 */
@RestController
public class UpLoadFileController {


    @Autowired
    FileService fileService;

    @Autowired
    AuthorizeService authorizeService;

    @PostMapping("/upLoadFile")
    @ResponseBody
    public Object upLoadFileByAndroid(@RequestParam("file") MultipartFile proFile,
                                      HttpServletRequest request) {
        Map<Object, Object> result = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        String platform = request.getHeader("Platform");
        if(!proFile.isEmpty()){
            if(fileService.upLoadFile(proFile,user.getAccount(),platform)){
                result.put("status",'2');
                result.put("info",proFile.getOriginalFilename()+"上传成功");
            }else {
                result.put("status",'1');
                result.put("info",proFile.getOriginalFilename()+"上传失败");
            }
        }else{
            result.put("status",'1');
        }
        return result;
    }
}
