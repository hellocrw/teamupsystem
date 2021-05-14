package crw.cloud.cloudstudy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/study")
public class StudyController {

    @GetMapping("demo")
    public String demo() {
        return Thread.currentThread().getThreadGroup().getName();
    }
}
