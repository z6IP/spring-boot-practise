package top.zhz.boot.exception.boot.mp.boot.config.week1;

import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/adults")
    public List<String> getAdultUserNames() {
        return userService.getAdultUserNames();
    }
}
