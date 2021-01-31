package crw.team.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ConfigController {

    @Value("${info.version}")
    private String version;

    @GetMapping("/version")
    public String getVersion(){
        return version;
    }
}