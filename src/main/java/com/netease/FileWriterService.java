package com.netease;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by switch on 16/9/29.
 */
// 配置写文件服务bean
@Component("fileWriterService")
public class FileWriterService {
    // 路径
    @Value("${filepath}")
    private String filePath;

    // 文件名
    @Value("${filename}")
    private String fileName;

    // 打印写出字符流
    private PrintWriter writer = null;

    // 构造后回调方法
    // 加载写文件服务所需要的IO流(已附加形式)使用绝对路径
    @PostConstruct
    public void init() {
        try {
            writer = new PrintWriter(new FileWriter(filePath + fileName, true), true);
            // 表示写文件服务已创建
            System.out.println("——————————写文件服务已创建——————————");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    // 销毁前回调方法
    @PreDestroy
    public void destory() {
        // 销毁时关闭文件
        // 关闭文件
        writer.close();
        System.out.println("——————————写文件服务已关闭——————————");
    }


    // 写文件服务
    public void write(String content) {
        writer.append(content);
    }


    // 获取器
    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public PrintWriter getWriter() {
        return writer;
    }
}
