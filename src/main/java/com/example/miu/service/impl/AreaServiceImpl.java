package com.example.miu.service.impl;

import com.example.miu.mapper.AreaMapper;
import com.example.miu.pojo.table.Area;
import com.example.miu.service.AreaService;
import com.example.miu.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public int addArea(Area area) {
        areaMapper.insertSelective(area);
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
