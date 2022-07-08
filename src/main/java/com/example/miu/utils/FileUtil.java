package com.example.miu.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * <Description> FileUtil
 *
 * @author 26802
 * @version 1.0
 * @ClassName FileUtil
 * @taskId
 * @see com.example.miu.utils
 */
public class FileUtil {

    public static boolean deleteAllFile(String path){
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
        }
        return flag;
    }

    public static String updateFile(String basePath, MultipartFile file){
        String fileName = file.getOriginalFilename();
        String filePath = basePath + "/" +fileName;

        File newFile = new File(filePath);

        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            return null;
        }

        return filePath;
    }

}
