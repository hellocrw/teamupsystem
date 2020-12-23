package crw.session.sessiondemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SessionController {

    @GetMapping("/test")
    public String test(HttpServletRequest request){
        request.getSession().setAttribute("xxx", "xxxx");
        return "测试";
    }

}
