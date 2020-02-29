package top.getidea.filesync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import top.getidea.filesync.DTO.GitHubAccess;
import top.getidea.filesync.DTO.UpLoadFileDTO;
import top.getidea.filesync.mapper.UserLogMapper;
import top.getidea.filesync.mapper.UserMapper;
import top.getidea.filesync.module.User;
import top.getidea.filesync.module.UserLog;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description :
 * @Author : Getidea
 * @Date: 2020-02-29 08:57
 */
@Service
public class AuthorizeService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserLogMapper userLogMapper;

    Map<String,Object> resultMap = new HashMap<>();

    /**
     * @Description : 查询数据库；根据查询结果，返回是否有当前账号 1：无，2：有
     *                若有当前结果，把本次登录记录插入UserLog表
     * @param id
     * @param password
     * @return resultMap
     */
    public Object login(String id, String password, String platform) {
        User result = userMapper.queryByIdAndPassword(id,password);
        if(result == null){
            resultMap.put("status",1);
        }else{
            UserLog userLog= UserLog2Bean(platform,result);
            userLogMapper.insert(userLog);
            resultMap.put("status",2);
        }
        return resultMap;
    }

    UserLog UserLog2Bean(String platform,User user){
        UserLog userLog = new UserLog();
        userLog.setAccount(user.getAccount());
        userLog.setGmtCreate(System.currentTimeMillis());
        userLog.setGmtModified(System.currentTimeMillis());
        userLog.setId(user.getId());
        userLog.setPlatform(platform);
        userLog.setOperate_Log("Login");
        return userLog;
    }

    public Integer register(GitHubAccess gitHubAccessInfo) {
        User user = new User();
        user.setAccount(gitHubAccessInfo.getId());
        user.setName(gitHubAccessInfo.getName());
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(System.currentTimeMillis());
        return userMapper.insert(user);
    }

    public boolean isOnline(UpLoadFileDTO upLoadFileDTO){

    }
}
