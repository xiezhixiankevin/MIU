package com.example.miu.controller;

import com.example.miu.pojo.table.User;
import com.example.miu.service.AreaService;
import com.example.miu.service.TagService;
import com.example.miu.service.UserService;
import com.example.miu.utils.Global;
import com.example.miu.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;

/**
 * <Description> FileController
 *
 * @author 26802
 * @version 1.0
 * @ClassName FileController
 * @taskId
 * @see com.example.miu.controller
 */
@Controller
@RequestMapping("/file")
@ResponseBody
public class FileController {

    private final String areaImage = "areaImage";
    private final String tag = "tag";
    private final String user = "user";
    private final String areaPhoto = "areaPhoto";

    @Autowired
    private UserService userService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private TagService tagService;

    @PostMapping("/upload")
    public ReturnObject<String> upload(@RequestParam("file") MultipartFile multipartFile,
                                              @RequestParam("type") String param,
                                              @RequestParam("id") String id){

        String savePath = "";
        String fileName = fileName = multipartFile.getOriginalFilename();
        Integer type = 1;
        if (param.equals(areaImage)){
            savePath = "/home/project/miu/images/area/image/" + id;
        }
        if (param.equals(areaPhoto)){
            savePath = "/home/project/miu/images/area/photo/" + id;
            type = 2;
        }
        if (param.equals(user)){
            savePath = "/home/project/miu/images/user/" + id;
            type = 3;
        }
        if (param.equals(tag)){
            savePath = "/home/project/miu/images/tag/" + id;
            type = 4;
        }

        if (uploadFile(savePath,fileName,multipartFile,type,Integer.parseInt(id))){
            return new ReturnObject<>(Global.SUCCESS,"200");
        }

        return new ReturnObject<>(Global.FAIL,"-1");
    }

    private boolean uploadFile(String savePath,String fileName,MultipartFile upload,Integer type,Integer id){
        File folder = new File(savePath);

        //不存在则创建文件夹
        if (!folder.exists()){
            folder.mkdir();
        }

        String filePath = savePath+"//"+fileName; //文件路径
        File newFile = new File(filePath);

        try {
            upload.transferTo(newFile); //上传文件
            //将文件路径写入对应数据库
            switch (type) {
                case 1:
                    //写入
                    break;
                case 2:
                    break;
                case 3:
                    User user = new User();
                    user.setId(id);
                    user.setPhotoPath(filePath);
                    userService.updateUser(user);
                    break;
                case 4:
                    break;
            }
            return true;
        } catch (IOException e) {
            return false;
        }

    }

}
