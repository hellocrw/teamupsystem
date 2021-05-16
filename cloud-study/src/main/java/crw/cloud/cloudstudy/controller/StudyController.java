package crw.cloud.cloudstudy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/study")
public class StudyController {

    @GetMapping("demo")
    public String demo() {
        ConcurrentHashMap<String ,Object> concurrentHashMap = new ConcurrentHashMap<String, Object>();
        return Thread.currentThread().getThreadGroup().getName();
    }
}
