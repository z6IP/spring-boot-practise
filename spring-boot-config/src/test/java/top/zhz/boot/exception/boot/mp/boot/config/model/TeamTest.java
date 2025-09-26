package top.zhz.boot.exception.boot.mp.boot.config.model;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
class TeamTest {
    @Resource
    private Team team;

    @Test
    void testTeam() {
        assertEquals(team.getName(), "Web2班", team.getName());
        assertEquals(team.getLeader(), "zhz", team.getLeader());
    }
}