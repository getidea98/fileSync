package top.getidea.filesync.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description :
 * @Author : Getidea
 * @Date: 2020-03-01 21:12
 */
@RestController
public class SessionController {

    @PostMapping("/session")
    @ResponseBody
    public Object session(HttpServletRequest request){
//        MessageDTO messageDTO = new MessageDTO();
//        messageDTO.setAccount((String)request.getSession().getAttribute("account"));
//        messageDTO.setAccount((String)request.getSession().getAttribute("password"));
//        messageDTO.setAccount((String)request.getSession().getAttribute("platform"));
        Map<Object,Object> result = new HashMap<>();
        result.put("account",request.getSession().getAttribute("account"));
        result.put("password",request.getSession().getAttribute("password"));
        return result;
    }
}
