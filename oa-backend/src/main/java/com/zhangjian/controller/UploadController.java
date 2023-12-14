package com.zhangjian.controller;

import com.aliyun.oss.AliOSSUtils;
import com.zhangjian.pojo.Result;
import com.zhangjian.utils.FileStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;
import java.io.IOException;


@Slf4j
@RestController
public class UploadController {

    @Autowired
    private FileStorage fileStorage;

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping( "/upload" )
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
        log.info("文件上传");

        // 1. 获取原始文件名
        String originalFilename = file.getOriginalFilename();

        // 2. 使用 uuid 构建保存的文件名
        int index = originalFilename.lastIndexOf('.');
        String substring = originalFilename.substring(index);

        String newFilename = UUID.randomUUID().toString() + substring;

        // 3. 将文件保存到 static/images 目录下
        File dir = new File("oa-backend/src/main/resources/static/images");

        log.info(dir.getAbsolutePath());

        if (!dir.exists()) { // 创建目录
            dir.mkdirs();
        }

        file.transferTo(new File(dir.getAbsolutePath(), newFilename));

        // 4. 其他常用方法
        log.info(String.valueOf(file.getSize())); // 获取文件大小， 单位字节
//        log.info(Arrays.toString(file.getBytes())); // 获取文件内容的数组
//        file.getInputStream();  // 获取文件内容的输入流

        return Result.success();
    }

    @PostMapping( "/upload/ali/oss" )
    public Result upload(MultipartFile file) throws IOException {
        log.info("上传文件到OSS");
//        String url = fileStorage.upload(file);
        String url = aliOSSUtils.upload(file);
        log.info("文件上传成功，访问地址: {}", url);
        return Result.success(url);
    }
}
