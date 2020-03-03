package top.getidea.filesync.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import top.getidea.filesync.module.File;

import java.util.List;

@Component
public interface FileMapper {

    @Insert("INSERT INTO FILE(FILENAME,DESCRIPTION,fileSize,fileType,GMT_Create,GMT_Modified,CREATOR,FILEURL,TOKEN,PLATFORM) VALUES(#{fileName},#{description},#{fileSize},#{fileType},#{gmtCreate},#{gmtModified},#{creator},#{fileUrl},#{token},#{platform})")
    Integer insert(File file);

    @Select("SELECT * FROM FILE WHERE CREATOR = #{CREATOR}")
    List<File> queryAllByAccount(@Param("CREATOR") String account);

    @Select("SELECT * FROM FILE WHERE ID = #{ID}")
    File queryById(@Param("ID") String id);

    @Delete("DELETE FROM FILE WHERE ID = #{ID}")
    Integer delFileById(@Param("ID") String id);
}
