package top.getidea.filesync.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @Description :
 * @Author : Getidea
 * @Date: 2020-02-29 14:47
 */
@Data
public class UpLoadFileDTO {

    @NotNull(message = "您没有选择文件")
    private MultipartFile srcFile;
    @NotNull(message = "您不能在为登陆状态下上传文件")
    private String account;
    @NotNull(message = "您不能在为登陆状态下上传文件")
    private String password;
    private String platform = "unknown";
}
