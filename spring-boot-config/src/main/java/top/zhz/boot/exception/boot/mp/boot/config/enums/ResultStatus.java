package top.zhz.boot.exception.boot.mp.boot.config.enums;

import lombok.Getter;

/**
 * @author zhz
 */

@Getter
public enum ResultStatus {
    SUCCESS("成功"),
    FAIL("失败");

    private String label;

    ResultStatus(String label) {
        this.label = label;
    }
}
