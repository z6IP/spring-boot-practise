package top.zhz.boot.config.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zhz.boot.config.common.ApiResponse;
import top.zhz.boot.config.enums.ResultStatus;
import top.zhz.boot.config.model.Mail;
import top.zhz.boot.config.service.MailService;

/**
 * @author zhz
 */

@Slf4j
@RestController
@RequestMapping("/mail")
public class MailController {
    @Resource
    private MailService mailService;

    @PostMapping("/simple")
    public ResponseEntity<ApiResponse<ResultStatus>> sendMail(@Valid @RequestBody Mail mail) {
        ResultStatus rs = mailService.sendSimpleMail(mail);
        if(rs == ResultStatus.SUCCESS) {
            return ResponseEntity.ok(ApiResponse.success("发送成功", rs));
        }else {
            return ResponseEntity.ok(ApiResponse.error("发送失败"));
        }
    }

    @PostMapping("/html")
    public ResponseEntity<ApiResponse<ResultStatus>> sendHtmlMail(@Valid @RequestBody Mail mail) {
        ResultStatus rs = mailService.sendHtmlMail(mail);
        return rs == ResultStatus.SUCCESS ?
                ResponseEntity.ok(ApiResponse.success("发送成功", rs)) :
                ResponseEntity.ok(ApiResponse.error("发送失败"));
    }
}
