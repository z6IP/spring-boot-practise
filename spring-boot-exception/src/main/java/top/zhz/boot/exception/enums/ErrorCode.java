package top.zhz.boot.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhz
 */

@AllArgsConstructor
@Getter
public enum ErrorCode {

    PARAM_ERROR (400, "参数错误"),
    UNAUTHORIZED (401, "登录失效,请重新登录"),
    NOT_PERMISSION (403, "权限不足"),
    NOT_FOUND (404, "未找到该资源"),
    METHOD_ERROR (405, "方法不允许"),
    SERVER_ERROR (500, "服务器异常,请稍后再试");

    private final int code;
    private final String msg;

}
