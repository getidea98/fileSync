package top.getidea.filesync.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.getidea.filesync.provide.FileUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @Description :
 * @Author : Getidea
 * @Date: 2020-03-01 15:24
 */

@Controller
public class DownLoadFileController {

    @Value("${save.folder}")
    String folder;

    @RequestMapping("downloadFile")
    @ResponseBody
    public void downloadFile(HttpServletResponse response,
                             @RequestParam("account") String account,
                             @RequestParam("password") String password,
                             @RequestParam()String fileName) {
        String fileSrc = folder+ File.separator+account+File.separator+fileName;
        System.out.println(fileSrc);
        String result = FileUtil.downloadFile(response, fileSrc);
    }
}
