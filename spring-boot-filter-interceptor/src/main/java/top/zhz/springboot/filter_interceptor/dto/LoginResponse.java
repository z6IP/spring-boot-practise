package top.zhz.springboot.filter_interceptor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private Long userId;
    private String username;
    private String role;
    private String token;
}