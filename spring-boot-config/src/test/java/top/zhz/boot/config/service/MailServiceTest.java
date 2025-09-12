package top.zhz.boot.config.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.zhz.boot.config.model.Mail;

@SpringBootTest
class MailServiceTest {

    @Resource
    private MailService mailService;
    @Test
    void sendSimpleMail() {
        Mail mail = new Mail();
        mail.setTo("1967430835@qq.com");
        mail.setSubject("测试邮件");
        mail.setContent("测试邮件内容");
        mailService.sendSimpleMail(mail);
        System.out.println("发送成功");
    }

    @Test
    void sendHtmlMail() {
        Mail mail = new Mail();
        mail.setTo("1967430835@qq.com");
        mail.setSubject("html测试邮件");
        mail.setContent("<html><body><h1>测试</h1></body></html>");
        mailService.sendHtmlMail(mail);
        System.out.println("发送成功");
    }
}