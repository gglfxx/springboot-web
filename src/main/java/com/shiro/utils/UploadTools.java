package com.shiro.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 上传文件
 * 1、上传到文件服务器通过http
 * 2、上传到ftp服务器
 */
public class UploadTools {

    private static final Logger logger = LoggerFactory.getLogger(UploadTools.class);

    /**
     * 上传文件到当前服务器
     * @param filePath 文件路径
     * @param bytes  文件二进制流
     * @param fileName 文件名称
     */
    public static boolean uploadToCurrent(String filePath,byte[] bytes,String fileName){
        boolean flag = false;
        try{
            File file = new File(filePath);
            if(!file.exists()){
                file.mkdirs();
            }
            Path path = Paths.get(file.getAbsolutePath()+"/"+fileName);
            Files.write(path,bytes);
            flag = true;
        }catch (Exception e){
            logger.warn("上传文件到当前服务器异常："+e.getMessage());
        }
        return flag;
    }

    public boolean uploadToOtherServers(){
        return false;
    }
}
