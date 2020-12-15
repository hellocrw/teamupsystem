package crw.team.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ONLINE-TEAM")
@Service
public interface TeamService {

    @GetMapping("/team/{id}")
    String findById(@PathVariable("id") Long id);
}
