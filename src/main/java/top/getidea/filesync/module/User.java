package top.getidea.filesync.module;

import lombok.Data;

/**
 * @Description :
 * @Author : Getidea
 * @Date: 2020-02-29 09:17
 */
@Data
public class User {
    private Integer id;
    private String account;
    private long gmtModified;
    private long gmtCreate;
    private String name;
    private String password;
    private String token;
}
