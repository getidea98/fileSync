package top.getidea.filesync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.getidea.filesync.mapper.UserMapper;
import top.getidea.filesync.module.User;

/**
 * @Description :
 * @Author : Getidea
 * @Date: 2020-03-01 19:02
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
    public User queryByAccount(User user) {
        return userMapper.queryByAccountAndPassword(user);
    }

    public Integer updateById(User user) {
        return userMapper.updateById(user);
    }

    public User queryByToken(String token) {
        return userMapper.queryByToken(token);
    }
}
