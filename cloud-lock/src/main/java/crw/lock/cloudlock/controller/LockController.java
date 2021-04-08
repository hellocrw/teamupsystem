package crw.lock.cloudlock.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lock")
public class LockController {

    @GetMapping("/lock-demo")
    public String lockDemo() {
        return "lockDemo";
    }
}
