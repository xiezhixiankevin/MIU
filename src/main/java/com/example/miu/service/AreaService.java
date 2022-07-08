package com.example.miu.service;

import com.example.miu.pojo.table.Area;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * <Description> AreaService
 *
 * @author 26802
 * @version 1.0
 * @ClassName AreaService
 * @taskId
 * @see com.example.miu.service
 */
public interface AreaService {


    int addArea(Area area, MultipartFile multipartFile);

    int deleteArea(Area area);

    List<Area> listArea();



}
