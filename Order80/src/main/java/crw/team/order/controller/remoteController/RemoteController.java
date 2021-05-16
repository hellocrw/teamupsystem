package crw.team.order.controller.remoteController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import crw.team.order.service.remoteService.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/remote")
public class RemoteController {

    private ProjectService projectService;

    @Autowired
    public RemoteController(ProjectService projectService){
        this.projectService = projectService;
    }

    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "hystrixFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),  // 是否开启
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),  // 设置超时时间， 默认1s
    })
    public Object remoteProject(@PathVariable Long id) {
        System.out.println("远程调用");
        Object projectInfo = projectService.findById(id);
        System.out.println(projectInfo);
        return projectInfo;
    }

    public String hystrixFallback(Long id){
        System.out.println("parameter: " + id);
        return "温馨提醒：网络连接超时!!!";
    }
}
