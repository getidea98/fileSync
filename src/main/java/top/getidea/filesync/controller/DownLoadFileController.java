package top.getidea.filesync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import top.getidea.filesync.provide.FileUtil;
import top.getidea.filesync.service.FileService;
import top.getidea.filesync.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
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

    @CrossOrigin
    @RequestMapping(value = "/downloadFile/{id}")
    @ResponseBody
    public void downloadFile(HttpServletResponse response,
                               @PathVariable(value = "id") String fileId) {
        top.getidea.filesync.module.File file = fileService.queryById(fileId);
        Map<Object, Object> result;
        if (file != null) {
            result = fileUtil.downloadFile(response, file.getFileUrl());
        } else {
            result = new HashMap<>();
            result.put("status", '1');
            result.put("info", "数据库中没有数据");
        }
        //response.setStatus(303);
        //return result;
    }
}
