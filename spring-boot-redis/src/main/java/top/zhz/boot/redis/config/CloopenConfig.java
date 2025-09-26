package top.zhz.boot.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * @Author: zhz
 * @Date: 2025/9/24
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "zhz.sms.ccp")
public class CloopenConfig {
    private String serverIp;
    private String port;
    private String accountSid;
    private String accountToken;
    private String appId;
    private String templateId;
}
