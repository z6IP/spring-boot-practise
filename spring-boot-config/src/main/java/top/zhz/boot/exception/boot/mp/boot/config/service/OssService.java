package top.zhz.boot.exception.boot.mp.boot.config.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhz
 */
public interface OssService {
    String upload(MultipartFile file);
}
