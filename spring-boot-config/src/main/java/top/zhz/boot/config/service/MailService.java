package top.zhz.boot.config.service;

import org.springframework.stereotype.Service;
import top.zhz.boot.config.enums.ResultStatus;
import top.zhz.boot.config.model.Mail;

/**
 * @author zhz
 */
@Service
public interface MailService {
    ResultStatus sendSimpleMail(Mail mail);

    ResultStatus sendHtmlMail(Mail mail);
}
