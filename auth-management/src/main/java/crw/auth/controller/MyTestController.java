package crw.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限管理平台测试类
 */
@RestController
public class MyTestController {

    @GetMapping("/test")
    public String demo(){
        return "测试方法";
    }
}
