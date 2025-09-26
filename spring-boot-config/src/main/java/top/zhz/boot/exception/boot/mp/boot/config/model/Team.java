package top.zhz.boot.exception.boot.mp.boot.config.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;


/**
 * @author zhz
 */
@Data
@Component
public class Team {

    @Value("${team.name}")
    @NotNull
    @NotEmpty
    @NotBlank
    @Length(min = 3, max = 20, message = "团队名长度应在3-20之间")
    private String name;

    @Value("${team.leader}")
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 8, message = "团队负责人长度应在3-8之间")
    private String leader;

    @Value("${team.phone}")
    @Pattern(regexp = "^[0-9]{11}$", message = "手机号格式不正确")
    private String phone;

    @Value("${team.age}")
    @Max(4)
    @Min(1)
    private Integer age;

    @Past(message = "创建时间不能晚于当前时间")
    private LocalDate createTime;

    @Future(message = "更新时间不能早于当前时间")
    private LocalDate updateTime;

}
