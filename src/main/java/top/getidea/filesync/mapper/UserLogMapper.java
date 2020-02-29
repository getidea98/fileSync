package top.getidea.filesync.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import top.getidea.filesync.module.UserLog;

@Component
public interface UserLogMapper {

    @Insert("INSERT INTO USERLOG(account,GMT_Create,GMT_Modified,platform,operate_Log,OBJECT_ID) VALUES(#{account},#{gmtCreate},#{gmtModified},#{platform},#{operate_Log},#{OBJECT_ID})")
    Integer insert(UserLog userLog);
}
