package com.zhangjian.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.zhangjian.pojo.AliOSSProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 文件存储类
 */
@Slf4j
@Component // 将文件存储类交给IOC容器
public class FileStorage {

    @Autowired
    private AliOSSProperties aliOSSProperties;


    private OSS getOSS() {
        return new OSSClientBuilder().build(aliOSSProperties.getEndpoint(), aliOSSProperties.getAccessKeyId(), aliOSSProperties.getAccessKeySecret());
    }

    public boolean bucketIsExist(String bucketName) {
        OSS oss = getOSS();
        boolean exist = oss.doesBucketExist(bucketName);
        oss.shutdown();

        return exist;
    }

    public void createBucket(String bucketName) {
        OSS oss = getOSS();

        try {
            oss.createBucket(bucketName);
        } catch (OSSException | ClientException e) {
            e.printStackTrace();
        } finally {
            oss.shutdown();
        }
    }

    public String getBucketEndpoint() {
        StringBuilder builder = new StringBuilder(aliOSSProperties.getEndpoint());

        if (aliOSSProperties.getEndpoint().startsWith("http:")) {
            builder.insert(builder.indexOf("//") + 2, aliOSSProperties.getBucketName() + ".");
        }else {
            builder.insert(0, "http://" + aliOSSProperties.getBucketName() + ".");
        }

        return builder.toString();
    }

    public String upload(MultipartFile file) throws IOException {
        OSS oss = getOSS();

        String originalFilename = file.getOriginalFilename();

        String suffix = originalFilename.substring(originalFilename.lastIndexOf('.'));

        String newFilename = UUID.randomUUID() + suffix;

        try {
            oss.putObject(aliOSSProperties.getBucketName(), newFilename, file.getInputStream());
            return getBucketEndpoint() + "/" + newFilename;
        } catch (OSSException | ClientException e) {
            e.printStackTrace();
            return null;
        } finally {
            oss.shutdown();
        }
    }
}
