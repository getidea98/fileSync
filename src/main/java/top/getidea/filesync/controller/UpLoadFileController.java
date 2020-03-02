package top.getidea.filesync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.getidea.filesync.DTO.MessageDTO;
import top.getidea.filesync.service.AuthorizeService;
import top.getidea.filesync.service.FileService;

import javax.validation.Valid;
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
    public Object upLoadFileByAndroid(@Valid MessageDTO messageDTO) {
        Map<Object, Object> result = new HashMap<>();
        if (authorizeService.isOnline(messageDTO) != null) {
            //判断文件是否写入成功，并且记录到数据库
            if (!messageDTO.getSrcFile().isEmpty()) {
                if (fileService.upLoadFile(messageDTO.getSrcFile(), messageDTO.getAccount(), messageDTO.getPlatform())) {
                    result.put(2, "上传成功");
                } else {
                    result.put(1, "上传失败");
                }
            } else {
                result.put(1, "请选择文件");
            }
        } else {
            result.put(1, "后台没有您的数据呦");
        }
        return result;
    }

    @GetMapping("/hello")
    public String hello(){
        return "234234";
    }
}
