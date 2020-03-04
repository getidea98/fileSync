package top.getidea.filesync.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description : Controller测试类
 * @Author : Getidea
 * @Date: 2020-03-03 08:27
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public Object test(HttpServletRequest request){
        Map<Object,Object> result = new HashMap<>();
       result.put("status", request.getHeader("Authorize"));
        return result;
    }
}
