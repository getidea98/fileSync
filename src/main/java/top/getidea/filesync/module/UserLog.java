package top.getidea.filesync.module;

import lombok.Data;

/**
 * @Description :
 * @Author : Getidea
 * @Date: 2020-02-29 09:20
 */
@Data
public class UserLog {
    private Integer id;
    private String account;
    private long gmtCreate;
    private long gmtModified;
    private String platform;
    private String operate_Log;
    private String OBJECT_ID;
}
