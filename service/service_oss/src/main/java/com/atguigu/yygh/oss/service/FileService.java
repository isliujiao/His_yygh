package com.atguigu.yygh.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author:厚积薄发
 * @create:2022-08-05-15:00
 */
public interface FileService {
    //上传文件到阿里云oss
    String upload(MultipartFile file);
}
