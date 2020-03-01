package top.getidea.filesync.DTO;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * @Description :
 * @Author : Getidea
 * @Date: 2020-02-29 14:47
 */
@Data
public class MessageDTO {

    @NotNull(message = "您还为登陆呦")
    private String account;

    @NotNull(message = "您还为登陆呦")
    private String password;

    private String platform = "unknown";
}
