package top.zhz.boot.exception.boot.mp.boot.config.enums;

import lombok.Getter;

/**
 * @author zhz
 * 快递的状态
 */

@Getter
public enum ExpressStatus {
    CREATED("已揽收"),
    TRANSIT("在途中"),
    SUCCESS("签收");


    private final String label;

    ExpressStatus(String label) {
        this.label = label;
    }

}
