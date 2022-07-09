package com.example.miu.controller;

import com.example.miu.pojo.table.Tag;
import com.example.miu.service.TagService;
import com.example.miu.utils.FileUtil;

import com.example.miu.utils.Global;
import com.example.miu.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;

/**
 * <Description> TagController
 *
 * @author 26802
 * @version 1.0
 * @ClassName TagController
 * @taskId
 * @see com.example.miu.controller
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/addTag")
    public ReturnObject<String> addTag(Tag tag,
                                       @RequestParam(value = "image",required = false) MultipartFile image){
        if (checkTag(tag)){
            tagService.addTag(tag);
            final String basePath = "/home/project/miu/images/tag/" + tag.getId();

            if (tag.getId() == null){
                return new ReturnObject<>(Global.FAIL,"-1");
            }
            if (image != null){
                //新建文件夹
                FileUtil.createFolder(basePath);
                File file = new File(basePath + "/" + image.getOriginalFilename());
                try {
                    //存入图片
                    image.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Tag var1 = new Tag();
                var1.setId(tag.getId());
                var1.setPicturePath(basePath + "/" + image.getOriginalFilename());
                tagService.updateTag(var1);
            }


            return new ReturnObject<>(Global.SUCCESS,"200");
        }
        return new ReturnObject<>(Global.FAIL,"-1");
    }

    private boolean checkTag(Tag tag){
        if (tag.getAreaId() == null ||tag.getX() == null ||tag.getY()==null||tag.getUserId() == null){
            return false;
        }
        return true;
    }


}
