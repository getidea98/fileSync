package top.getidea.filesync.DTO;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description :
 * @Author : Getidea
 * @Date: 2020-02-29 11:52
 */
@Data
public class RegisterResultDTO {
    private String status;
    private String account;
    private String name;
    private String profile;
}
