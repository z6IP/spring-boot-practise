package top.zhz.boot.exception.boot.mp.boot.config.week1;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requests")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/{type}")
    public String handleRequest(@PathVariable RequestType type) {
        return customerService.handleRequest(type);
    }
}
