package top.getidea.filesync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.getidea.filesync.mapper.FileMapper;
import top.getidea.filesync.mapper.UserLogMapper;
import top.getidea.filesync.module.UserLog;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @Description : upLoadFile：文件上传处理；
 * UserLog2Bean：多参数合并成Bean
 * File2Bean：多参数合并成Bean
 * @Author : Getidea
 * @Date: 2020-02-29 12:16
 */
@Service
public class FileService {

    @Value("${save.folder}")
    private String UPLOAD_FOLDER;
    @Autowired
    UserLogMapper userLogMapper;
    @Autowired
    FileMapper fileMapper;

    /**
     * @param srcFile
     * @param creator
     * @return boolean
     * @Description：在指定目录写文件--》信息保存到file、userlog数据库
     */
    public boolean upLoadFile(MultipartFile srcFile, String creator, String platform) {
        File destFile = new File(UPLOAD_FOLDER + File.separator + creator);
        if (!destFile.exists()) {
            destFile.mkdir();
        }
        try {
            byte[] bytes = srcFile.getBytes();
            Path path = Paths.get(destFile.getAbsolutePath() + File.separator + srcFile.getOriginalFilename());
            Files.write(path, bytes);
            String token = UUID.randomUUID().toString();
            fileMapper.insert(File2Bean(srcFile,creator,token));
            userLogMapper.insert(UserLog2Bean(srcFile,creator,platform,token));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @Dercription: 多参数合并成Bean
     * @param srcFile
     * @param creator
     * @param platform
     * @param id
     * @return
     */
    UserLog UserLog2Bean(MultipartFile srcFile,String creator,String platform,String id) {
        UserLog userLog = new UserLog();
        userLog.setAccount(creator);
        userLog.setGmtModified(System.currentTimeMillis());
        userLog.setGmtCreate(System.currentTimeMillis());
        userLog.setPlatform(platform);
        userLog.setOperate_Log(srcFile.getOriginalFilename());
        userLog.setOBJECT_ID(id);
        return userLog;
    }

    /**
     * @Dercription: 多参数合并成Bean
     * @param srcFile
     * @param creator
     * @param token
     * @return
     */
    top.getidea.filesync.module.File File2Bean(MultipartFile srcFile, String creator, String token){
        top.getidea.filesync.module.File file = new top.getidea.filesync.module.File();
        file.setCreator(creator);
        file.setFileName(srcFile.getOriginalFilename());
        file.setFileSize(srcFile.getSize());
        file.setFileType(srcFile.getOriginalFilename().substring(srcFile.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase());
        file.setGmtCreate(System.currentTimeMillis());
        file.setGmtModified(System.currentTimeMillis());
        file.setFileUrl(UPLOAD_FOLDER + File.separator + creator + File.separator + srcFile.getOriginalFilename());
        file.setToken(token);
        return file;
    }
}
