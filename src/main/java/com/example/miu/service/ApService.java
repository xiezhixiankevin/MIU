package com.example.miu.service;

import com.example.miu.pojo.table.Ap;
import com.example.miu.pojo.table.Area;

import java.util.List;

/**
 * <Description> ApService
 *
 * @author 26802
 * @version 1.0
 * @ClassName ApService
 * @taskId
 * @see com.example.miu.service
 */
public interface ApService {

    int addAp(Ap ap);

    int deleteAp(Ap ap);

    List<Area> listAp();

    boolean ifExistAp(Ap ap);

    Ap getApBybssidByssid(String bssid,String ssid);

}
