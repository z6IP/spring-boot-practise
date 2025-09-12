package top.zhz.boot.config.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zhz.boot.config.model.Team;

/**
 * @author zhz
 */
@Slf4j
@RestController
@RequestMapping("/teams")
public class TeamController {

    @RequestMapping("add")
    public Team createTeam(@Valid @RequestBody Team team) {
        log.info("创建团队: {}", team);
        return team;
    }
}
