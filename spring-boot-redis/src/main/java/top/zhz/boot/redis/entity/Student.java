package top.zhz.boot.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student implements Serializable {


    @Serial
    private static final long serialVersionUID = -3009229624228110421L;

    private Integer id;

    private String name;

    private Address address;


}
