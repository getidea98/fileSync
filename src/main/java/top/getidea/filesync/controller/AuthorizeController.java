package top.getidea.filesync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.getidea.filesync.DTO.AuthorizeDTO;
import top.getidea.filesync.DTO.GitHubAccess;
import top.getidea.filesync.DTO.GitHubAccessTokenDTO;
import top.getidea.filesync.provide.GitHubProvide;
import top.getidea.filesync.service.AuthorizeService;
import top.getidea.filesync.service.FileService;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description :账户授权
 * @Methods：1。login：登陆处理；2。register：注册处理
 * @Author : Getidea
 * @Date: 2020-02-29 08:51
 */
@Controller
public class AuthorizeController {

    @Autowired
    AuthorizeService authorizeService;

    @Autowired
    FileService fileService;

    @Value("${github.client_id}")
    private String client_id;

    @Value("${github.client_secret}")
    private String client_secret;

    @RequestMapping("/login")
    public String login(@RequestParam(name = "account") String account,
                        @RequestParam(name = "password") String password,
                        @RequestParam(name = "platform",defaultValue = "unknown") String platform,
                        Model model){
        AuthorizeDTO authorizeDTO = new AuthorizeDTO();
        authorizeDTO.setPlatform(platform);
        authorizeDTO.setPassword(password);
        authorizeDTO.setAccount(account);
        model.addAttribute("fileList",authorizeService.login(authorizeDTO));
        model.addAttribute("account",account);
        return "profile";
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

        Map<Object,Object> result = new HashMap<>();
        GitHubAccessTokenDTO gitHubAccessTokenDTO = gitHubAccessTokenDTO2Bean(code);
        GitHubProvide gitHubProvide = new GitHubProvide();
        String ResponseCode = gitHubProvide.getGitHubAccessToken(gitHubAccessTokenDTO);
        GitHubAccess gitHubAccessInfo = gitHubProvide.getGitHubAccessInfo(ResponseCode);
        Integer registerResult = authorizeService.register(gitHubAccessInfo);

        if(registerResult == 0){
            return "注册失败";
        }else{
            result.put("Account",gitHubAccessInfo.getId());
            result.put("Name",gitHubAccessInfo.getName());
            result.put("Status",2);
            result.put("Profile","http://filesync.getidea.top/"+gitHubAccessInfo.getId());
            return result;
        }
    }

    /**
     * @Description: gitHubAccessTokenDTO2Bean多参数合并成Bean
     * @param code
     * @return
     */
    GitHubAccessTokenDTO gitHubAccessTokenDTO2Bean(String code){
        GitHubAccessTokenDTO gitHubAccessTokenDTO = new GitHubAccessTokenDTO();
        gitHubAccessTokenDTO.setClient_id(client_id);
        gitHubAccessTokenDTO.setClient_secret(client_secret);
        gitHubAccessTokenDTO.setCode(code);
        return gitHubAccessTokenDTO;
    }
}
