package crw.team.order.controller.remoteController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    @HystrixCommand(fallbackMethod = "hystrixFallback")
    public String remoteProject(@PathVariable Long id) {
        System.out.println("远程调用");
        return projectService.findById(id);
    }

    public String hystrixFallback(Long id){
        return "温馨提醒：网络连接超时!!!";
    }
}
