package top.zhz.boot.config.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.zhz.boot.config.service.OssService;

/**
 * @author zhz
 */
@RestController
@RequestMapping("/oss")
public class OssController {
    @Resource
    private OssService ossService ;

    @PostMapping("upLoad")
    public String upLoad(MultipartFile file) {
        return ossService.upload(file);
    }
}
