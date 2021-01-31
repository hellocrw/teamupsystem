package crw.session.sessiondemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SessionController {

    @GetMapping("/setSession")
    public String test(HttpServletRequest request){
        request.getSession().setAttribute("xxx", "xxxx");
        return "测试";
    }

    @GetMapping("/getSession")
    public String getSession(HttpServletRequest request){
        return request.getSession().getId();
    }

}
