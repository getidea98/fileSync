package top.getidea.filesync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    public Object upLoadFileByAndroid(@RequestParam(value = "account") String account,
                                      @RequestParam(value = "password") String password,
                                      @RequestParam(value = "platform",defaultValue = "unknown") String platform,
                                      @RequestParam(value = "file", defaultValue = "") MultipartFile srcFile) {
        Map<Object, Object> result = new HashMap<>();
        //判断是否在线
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setPlatform(platform);
        messageDTO.setPassword(password);
        messageDTO.setAccount(account);
        if (authorizeService.isOnline(messageDTO) != null) {
            //判断文件是否写入成功，并且记录到数据库
            if (!srcFile.isEmpty()) {
                if (fileService.upLoadFile(srcFile, messageDTO.getAccount(), messageDTO.getPlatform())) {
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
}
