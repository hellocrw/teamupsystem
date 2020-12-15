package crw.team.order.controller;

import crw.team.order.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/order/team/{id}")
    public void findById(@PathVariable("id") Long id){
        String teamInfo = teamService.findById(id);
        System.out.println(teamInfo);
    }


}
