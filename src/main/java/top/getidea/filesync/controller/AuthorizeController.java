package top.getidea.filesync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.plugin2.message.Message;
import top.getidea.filesync.DTO.GitHubAccess;
import top.getidea.filesync.DTO.GitHubAccessTokenDTO;
import top.getidea.filesync.DTO.MessageDTO;
import top.getidea.filesync.mapper.UserMapper;
import top.getidea.filesync.module.User;
import top.getidea.filesync.provide.GitHubProvide;
import top.getidea.filesync.service.AuthorizeService;
import top.getidea.filesync.service.FileService;
import top.getidea.filesync.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    UserService userService;

    @Autowired
    FileService fileService;

    @Value("${github.client_id}")
    private String client_id;

    @Value("${github.client_secret}")
    private String client_secret;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestBody @Valid MessageDTO messageDTO,
                        Model model){
        Map<Object,Object> result = new HashMap<>();
        User user = userService.queryByAccount(messageDTO);
        //判断密码是否正确
        if(user != null){
            user = user2Bean(user);
            userService.updateById(user);
            model.addAttribute("user",user);
            result.put("status",2);
            result.put("data",user);
        }else{
            result.put("status",1);
            result.put("data",null);
        }
        return result;
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
     * @Description：退出登录处理
     * @param response
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletResponse response,
                         HttpServletRequest request){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
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

    User user2Bean(User user){
        //为本次添加token
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        user.setGmtModified(System.currentTimeMillis());
        return user;
    }

}
