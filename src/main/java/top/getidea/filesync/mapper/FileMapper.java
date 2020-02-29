package top.getidea.filesync.mapper;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;
import top.getidea.filesync.module.File;

@Component
public interface FileMapper {

    @Insert("INSERT INTO FILE(FILENAME,DESCRIPTION,fileSize,fileType,GMT_Create,GMT_Modified,CREATOR,fileUrl,token) VALUES(#{fileName},#{description},#{fileSize},#{fileType},#{gmtCreate},#{gmtModified},#{creator},#{fileUrl},#{token})")
    Integer insert(File file);
}
