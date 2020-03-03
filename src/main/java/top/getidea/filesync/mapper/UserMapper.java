package top.getidea.filesync.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import top.getidea.filesync.module.User;

/**
 * @Description :
 * @Author : Getidea
 * @Date: 2020-02-29 09:03
 */
@Component
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USER WHERE ACCOUNT = #{account} AND PASSWORD = #{password}")
    User queryByAccountAndPassword(User user);

    @Insert("INSERT INTO USER(ACCOUNT,NAME,GMT_CREATE,GMT_Modified) VALUES(#{account},#{name},#{gmtCreate},#{gmtModified})")
    Integer insert(User user);

    @Select("SELECT * FROM USER WHERE TOKEN = #{TOKEN} ORDER BY GMT_Modified DESC LIMIT 1")
    User queryByToken(@Param("TOKEN") String token);

    @Update("UPDATE USER SET PASSWORD = #{password},NAME = #{name},GMT_Modified = #{gmtModified},TOKEN = #{token} WHERE id = #{id}")
    Integer updateById(User user);
}
