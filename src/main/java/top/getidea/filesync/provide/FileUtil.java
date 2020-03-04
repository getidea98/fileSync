package top.getidea.filesync.provide;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description :
 * @Author : Getidea
 * @Date: 2020-03-01 15:26
 */
@Component
public class FileUtil {
    /**
     * 下载项目根目录下doc下的文件
     * @param response response
     * @param fileSrc 文件名
     * @return 返回结果 成功或者文件不存在
     */
    public Map downloadFile(HttpServletResponse response, String fileSrc) {
        Map<Object,Object> result = new HashMap<>();
        File file = new File(fileSrc);
        // 如果文件存在，则进行下载
        if (file.exists()) {
            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setContentLengthLong(file.length());
            // 下载文件能正常显示中文
            try {
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileSrc.split(File.separator)[fileSrc.split(File.separator).length-1], "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            // 实现文件下载
            byte[] buffer = new byte[1024];
            BufferedInputStream bis = null;
            try {
                bis = new BufferedInputStream(new FileInputStream(file));
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                os.flush();
                result.put("status",2);
                result.put("info","Success");

            } catch (Exception e) {
                result.put("status",1);
                result.put("info","fail");

            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;

    }
}
