package top.getidea.filesync.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import top.getidea.filesync.module.User;

/**
 * @Description :
 * @Author : Getidea
 * @Date: 2020-02-29 09:03
 */
@Component
public interface UserMapper {

    @Select("SELECT * FROM USER WHERE ACCOUNT = #{ACCOUNT} AND PASSWORD = #{PASSWORD}")
    User queryByAccountAndPassword(@Param("ACCOUNT") String id, @Param("PASSWORD") String password);

    @Insert("INSERT INTO USER(ACCOUNT,NAME,GMT_CREATE,GMT_Modified) VALUES(#{account},#{name},#{gmtCreate},#{gmtModified})")
    Integer insert(User user);

}
