package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    //本地磁盘存储
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
//        log.info("接收到的参数：{}, {}, {}",name,age,file);
//        String originalFileName=file.getOriginalFilename();
//        //随机生成不重复的文件名
//        String extension= originalFileName.substring(originalFileName.lastIndexOf("."));
//        String newFileName= UUID.randomUUID().toString()+extension;
//        //保存文件
//        file.transferTo(new File("D:/XzssyX/Desktop/Yellow_Ender_Chest/Java/web-ai-project02/tlias-web-management/uploads/"+newFileName));
//        return Result.success();
//    }

    //阿里云OSS存储
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传：{}",file.getOriginalFilename());
        String url=aliyunOSSOperator.upload(file.getBytes(),file.getOriginalFilename());
        log.info("文件上传OSS，url：{}",url);
        return Result.success(url);
    }
}
