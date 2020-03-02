package top.getidea.filesync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.getidea.filesync.DTO.GitHubAccess;
import top.getidea.filesync.DTO.MessageDTO;
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

    @Autowired
    FileService fileService;


    /**
     * @Description : 查询数据库；根据查询结果，返回是否有当前账号
     *
     * @param messageDTO
     * @return resultMap
     */
    public Object login(MessageDTO messageDTO) {
        Map<Object,Object> resultMap = new HashMap<>();
        User result = userMapper.queryByAccountAndPassword(messageDTO.getAccount(),messageDTO.getPassword());
        if(result == null){
            resultMap.put("1","后台没有您的数据呦");
        }else{
            UserLog userLog= UserLog2Bean(messageDTO.getPlatform(),result);
            //操作记录
            userLogMapper.insert(userLog);
            resultMap.put("2","登陆成功");
            resultMap.put("result",fileService.queryAllByAccount(messageDTO.getAccount()));
        }
        return fileService.queryAllByAccount(messageDTO.getAccount());
    }

    /**
     * @Descritpin：多参数合成Bean
     * @param platform
     * @param user
     * @return
     */
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

    /**
     * @Description：根据前端来的数据，将新注册的用户插入数据库
     * @param gitHubAccessInfo
     * @return
     */
    public Integer register(GitHubAccess gitHubAccessInfo) {
        User user = new User();
        user.setAccount(gitHubAccessInfo.getId());
        user.setName(gitHubAccessInfo.getName());
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(System.currentTimeMillis());
        return userMapper.insert(user);
    }

    public User isOnline(MessageDTO messageDTO){
        return userMapper.queryByAccountAndPassword(messageDTO.getAccount(),messageDTO.getPassword());
    }
}
