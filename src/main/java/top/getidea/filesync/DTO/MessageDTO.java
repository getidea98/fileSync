package top.getidea.filesync.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description :
 * @Author : Getidea
 * @Date: 2020-02-29 14:47
 */
@Data
public class MessageDTO implements Serializable {

    @NotNull(message = "您还为登陆呦")
    private String account;

    @NotNull(message = "您还为登陆呦")
    private String password;

    private String platform;

    private MultipartFile srcFile;
}
