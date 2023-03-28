package com.atguigu.oss.test;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

/**
 * @author:厚积薄发
 * @create:2022-08-05-14:38
 */
public class OssTest {
    public static void main(String[] args) throws Exception {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "LTAI5tDfr98YYfM9TCoxDXpo";
        String accessKeySecret = "XfbJ9GjJEKLr4fuKFbae0OnQ02Fjf1";
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "yygh-ljtestoss";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //创建存储空间
        ossClient.createBucket(bucketName);
        //关闭OssClient
        ossClient.shutdown();
    }
}
