package com.example.miu.service.impl;

import com.example.miu.mapper.ApMapper;
import com.example.miu.pojo.table.Ap;
import com.example.miu.pojo.table.ApExample;
import com.example.miu.pojo.table.Area;
import com.example.miu.service.ApService;
import com.example.miu.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <Description> ApServiceImpl
 *
 * @author 26802
 * @version 1.0
 * @ClassName ApServiceImpl
 * @taskId
 * @see com.example.miu.service.impl
 */
@Service
public class ApServiceImpl implements ApService {

    @Autowired
    private ApMapper apMapper;

    @Override
    public int addAp(Ap ap) {
        if (!ifExistAp(ap)){
            apMapper.insertSelective(ap);
            return Global.SUCCESS;
        }

        return Global.FAIL;
    }

    @Override
    public int deleteAp(Ap ap) {
        return 0;
    }

    @Override
    public List<Area> listAp() {
        return null;
    }

    @Override
    public boolean ifExistAp(Ap ap) {
        ApExample apExample = new ApExample();
        apExample.createCriteria().andBssidEqualTo(ap.getBssid()).andSsidEqualTo(ap.getSsid());
        return !apMapper.selectByExample(apExample).isEmpty();
    }

    @Override
    public Ap getApBybssidByssid(String bssid, String ssid) {
        ApExample apExample = new ApExample();
        apExample.createCriteria().andBssidEqualTo(bssid).andSsidEqualTo(ssid);
        List<Ap> apList = apMapper.selectByExample(apExample);
        if (apList.isEmpty()){
            return null;
        }
        return apList.get(0);
    }
}
