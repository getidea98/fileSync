package top.getidea.filesync.DTO;

import lombok.Data;

/**
 * @Description :
 * @Author : Getidea
 * @Date: 2020-02-29 10:44
 */
@Data
public class GitHubAccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
}
