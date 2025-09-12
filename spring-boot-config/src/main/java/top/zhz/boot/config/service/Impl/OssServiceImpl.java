package top.zhz.boot.config.service.Impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.zhz.boot.config.config.OssConfig;
import top.zhz.boot.config.service.OssService;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author zhz
 */
@Slf4j
@Service
public class OssServiceImpl implements OssService {

    @Resource
    private OssConfig ossConfig;
    @Override
    public String upload(MultipartFile file) {
        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            assert originalFilename != null;
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = UUID.randomUUID() + suffix;

            log.info("新文件名: {}", newFilename);
            //读取配置文件
            String endpoint = ossConfig.getEndpoint();
            String bucket = ossConfig.getBucket();
            String accessKey = ossConfig.getAccessKey();
            String secretKey = ossConfig.getSecretKey();
            String dir = ossConfig.getDir();

            //调用SDK,实现文件上传
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, secretKey);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("image/jpg");
            String uploadPath = dir + newFilename;
            InputStream inputStream;
            try {
                inputStream = file.getInputStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            ossClient.putObject(bucket, uploadPath, inputStream,metadata);

            ossClient.shutdown();
            return "https://" + bucket + "." + endpoint + "/" + uploadPath;
        }
        return "上传失败";
    }
}
