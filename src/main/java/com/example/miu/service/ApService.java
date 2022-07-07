package com.example.miu.service;

import com.example.miu.pojo.table.Ap;

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

    List<Ap> listApByAreaId(Integer areaId);

    boolean ifExistAp(Ap ap);

    Ap getApBybssidByssid(String bssid,String ssid);

}
