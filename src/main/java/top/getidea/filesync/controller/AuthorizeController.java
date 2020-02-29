package top.getidea.filesync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import top.getidea.filesync.DTO.AuthorizeDTO;
import top.getidea.filesync.DTO.GitHubAccess;
import top.getidea.filesync.DTO.GitHubAccessTokenDTO;
import top.getidea.filesync.DTO.RegisterResultDTO;
import top.getidea.filesync.provide.GitHubProvide;
import top.getidea.filesync.service.AuthorizeService;

/**
 * @Description :账户授权
 * @Author : Getidea
 * @Date: 2020-02-29 08:51
 */
@RestController
public class AuthorizeController {

    @Autowired
    AuthorizeService authorizeService;
    @Value("${github.client_id}")
    private String client_id;
    @Value("${github.client_secret}")
    private String client_secret;

    @ResponseBody
    @PostMapping("/login")
    public Object login(@RequestBody AuthorizeDTO authorizeDTO){
        return authorizeService.login(authorizeDTO.getId(),authorizeDTO.getPassword(),authorizeDTO.getPlatform());
    }


    /**
     * @Description: github账户登录回调函数；
     * 接收 code state
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping("/callback")
    public Object register(@RequestParam("code") String code){
        GitHubAccessTokenDTO gitHubAccessTokenDTO = new GitHubAccessTokenDTO();
        gitHubAccessTokenDTO.setClient_id(client_id);
        gitHubAccessTokenDTO.setClient_secret(client_secret);
        gitHubAccessTokenDTO.setCode(code);
        GitHubProvide gitHubProvide = new GitHubProvide();
        String ResponseCode = gitHubProvide.getGitHubAccessToken(gitHubAccessTokenDTO);
        GitHubAccess gitHubAccessInfo = gitHubProvide.getGitHubAccessInfo(ResponseCode);
        Integer result = authorizeService.register(gitHubAccessInfo);
        if(result == 0){
            return "注册失败";
        }else{
            RegisterResultDTO registerResultDTO = new RegisterResultDTO();
            registerResultDTO.setAccount(gitHubAccessInfo.getId());
            registerResultDTO.setName(gitHubAccessInfo.getName());
            registerResultDTO.setStatus("2");
            registerResultDTO.setProfile("http://filesync.getidea.top/"+gitHubAccessInfo.getId());
            return registerResultDTO;
        }

    }
}
