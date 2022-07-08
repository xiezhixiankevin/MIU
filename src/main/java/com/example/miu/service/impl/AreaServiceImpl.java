package com.example.miu.service.impl;

import com.example.miu.mapper.AreaMapper;
import com.example.miu.pojo.table.Area;

import com.example.miu.service.AreaService;
import com.example.miu.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <Description> AreaServiceImpl
 *
 * @author 26802
 * @version 1.0
 * @ClassName AreaServiceImpl
 * @taskId
 * @see com.example.miu.service.impl
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;


    @Override
    public int addArea(Area area, MultipartFile multipartFile) {

        areaMapper.insertSelective(area);

        //建立图片存储文件夹
        String imagePath = "/home/project/miu/images/area/image/" + area.getId();
        String photoPath = "/home/project/miu/images/area/photo/" + area.getId();

//        //建立图片存储文件夹
//        String imagePath = "G:\\image\\" + area.getId();
//        String photoPath = "G:\\photo\\" + area.getId();

        area.setImagePath(imagePath+"/"+ multipartFile.getOriginalFilename());
        areaMapper.updateByPrimaryKeySelective(area);

        File folder = new File(imagePath);
        //不存在则创建文件夹
        if (!folder.exists()){
            folder.mkdir();
        }
        folder = new File(photoPath);
        //不存在则创建文件夹
        if (!folder.exists()){
            folder.mkdir();
        }

        //创建区域时只能上传简介图，懒得学怎么多文件上传了。。
        if (multipartFile != null){
            String filePath = imagePath+"/"+ multipartFile.getOriginalFilename(); //文件路径
            File newFile = new File(filePath);

            try {
                multipartFile.transferTo(newFile); //上传文件
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return Global.SUCCESS;
    }

    @Override
    public int deleteArea(Area area) {
        areaMapper.deleteByPrimaryKey(area.getId());
        return Global.SUCCESS;
    }

    @Override
    public List<Area> listArea() {
        return areaMapper.selectByExample(null);
    }


}
