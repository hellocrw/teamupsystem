package crw.lock.cloudlock.controller;

import crw.lock.cloudlock.service.LockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/lock")
public class LockController {

    @Resource
    private LockService lockService;

    @GetMapping("lockDemo")
    public String lockDemo() {
        return "lockDemo";
    }

    @GetMapping("synchorizedDemo")
    public String synchorizedDemo(){
        lockService.synchorizedDemo();
        return "synchorizedDemo";
    }
}
