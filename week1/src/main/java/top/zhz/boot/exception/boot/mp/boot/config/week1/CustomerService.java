package top.zhz.boot.exception.boot.mp.boot.config.week1;

import org.springframework.stereotype.Service;
@Service
public class CustomerService {
    public String handleRequest(RequestType requestType) {
        return switch (requestType) {
            case QUERY -> handleQuery();
            case COMPLAINT -> handleComplaint();
            case SUGGESTION -> handleSuggestion();
        };
    }
    private String handleQuery() {
        return "Handling user query...";
    }
    private String handleComplaint() {
        return "Handling user complaint...";
    }
    private String handleSuggestion() {
        return "Handling user suggestion...";
    }
}
