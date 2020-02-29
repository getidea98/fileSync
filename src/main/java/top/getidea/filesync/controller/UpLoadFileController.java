package top.getidea.filesync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.getidea.filesync.DTO.UpLoadFileDTO;
import top.getidea.filesync.service.AuthorizeService;
import top.getidea.filesync.service.FileService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    Map<Object,Object> result = new HashMap<>();
    @PostMapping("/upLoadFile")
    @ResponseBody
    public Object upLoadFile(@RequestBody UpLoadFileDTO upLoadFileDTO) {
        if(upLoadFileDTO.getPlatform().equals("Android")){
            authorizeService.isOnline(upLoadFileDTO);
        }
        boolean b = fileService.upLoadFile(upLoadFileDTO.getSrcFile(), upLoadFileDTO.getCreator(), upLoadFileDTO.getPlatform());
        if (b) {
            result.put(2,"上传成功");
        } else {
            result.put(1,"上传失败");
        }
        return result;
    }
}
