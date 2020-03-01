package top.getidea.filesync.DTO;

import lombok.Data;

/**
 * @Description :
 * @Author : Getidea
 * @Date: 2020-02-29 09:55
 */
@Data
public class AuthorizeDTO {
    private String account;
    private String password;
    private String platform;
}
