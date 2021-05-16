package crw.team.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ConfigController {

    @Value("${server.port}")
    private String version;

    @GetMapping("/port")
    public String getVersion(){
        return version;
    }
}
