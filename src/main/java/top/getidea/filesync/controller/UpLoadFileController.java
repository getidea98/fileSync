package top.getidea.filesync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.getidea.filesync.DTO.UpLoadFileDTO;
import top.getidea.filesync.service.AuthorizeService;
import top.getidea.filesync.service.FileService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description :
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
    public Object upLoadFileByAndroid(@Valid UpLoadFileDTO upLoadFileDTO) {
        Map<Object,Object> result = new HashMap<>();
        if(authorizeService.isOnline(upLoadFileDTO)){
            boolean b = fileService.upLoadFile(upLoadFileDTO.getSrcFile(), upLoadFileDTO.getAccount(), upLoadFileDTO.getPlatform());
            if (b) {
                result.put(2,"上传成功");
            } else {
                result.put(1,"上传失败");
            }
        }else{
            result.put(1,"后台没有您的数据呦");
        }
        return result;
    }
}
