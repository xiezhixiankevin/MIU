package com.example.miu.controller;

import com.example.miu.pojo.table.User;
import com.example.miu.pojo.table.WifiRecord;
import com.example.miu.service.WifiRecordService;
import com.example.miu.utils.Global;
import com.example.miu.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * <Description> WifiRecordController
 *
 * @author 26802
 * @version 1.0
 * @ClassName WifiRecordController
 * @taskId
 * @see com.example.miu.controller
 */
@RequestMapping("/wifiRecord")
@ResponseBody
@Controller
public class WifiRecordController {

    @Autowired
    private WifiRecordService wifiRecordService;

    //当前在线用户
    private List<User> aliveUserList = new ArrayList<>();

    @PostMapping("/addWifiRecord")
    @ResponseBody
    public ReturnObject<String> addWifiRecord(WifiRecord wifiRecord){
        if (wifiRecordService.addWifiRecord(wifiRecord) == Global.SUCCESS){
            return new ReturnObject<>(Global.SUCCESS,String.valueOf(Global.SUCCESS));
        }
        return new ReturnObject<>(Global.FAIL,String.valueOf(Global.FAIL));
    }


    @PostMapping("/getLocation")
    @ResponseBody
    public ReturnObject<WifiRecord> getLocation(WifiRecord wifiRecord){
        if (checkWifiRecord(wifiRecord)){
            WifiRecord result = wifiRecordService.calculateLocation(
                    wifiRecordService.wifiRecord2Extend(wifiRecord),
                    wifiRecordService.wifiRecordList2Extend(wifiRecordService.listWifiRecordByAreaId(wifiRecord.getAreaId()))
            );

            return new ReturnObject<>(Global.SUCCESS,result);
        }
        return new ReturnObject<>(Global.FAIL,null);
    }

    @PostMapping("/getLocationAndAliveUser")
    @ResponseBody
    public ReturnObject<List<User>> getLocationAndAliveUser(WifiRecord wifiRecord,User user){
        if (checkWifiRecord(wifiRecord)){
            WifiRecord result = wifiRecordService.calculateLocation(
                    wifiRecordService.wifiRecord2Extend(wifiRecord),
                    wifiRecordService.wifiRecordList2Extend(wifiRecordService.listWifiRecordByAreaId(wifiRecord.getAreaId()))
            );
            user.setX(result.getX());
            user.setY(result.getY());
            user.setNowArea(wifiRecord.getAreaId());
            //添加或更新活跃用户list
            addAliveUser(user);
            //更新当前活跃用户
            checkUserAlive();
            //构造返回list
            List<User> userList = new ArrayList<>();
            userList.add(user); //当前用户默认放在第一个
            //添加该区域其他允许共享位置的活跃用户
            initUserList(userList,user.getNowArea(),user.getId());

            return new ReturnObject<>(Global.SUCCESS,userList);
        }
        return new ReturnObject<>(Global.FAIL,null);
    }

    private boolean checkWifiRecord(WifiRecord wifiRecord){
        if (wifiRecord == null)
            return false;
        if (wifiRecord.getAreaId() == null)
            return false;
        if (wifiRecord.getAps() == null)
            return false;
        if (wifiRecord.getStrength() == null)
            return false;
        return true;
    }

    private synchronized void addAliveUser(User user){
        //首先遍历list,看看这个user是不是已经存在
        for (User user1 : aliveUserList) {
            if (user1.getId() == user.getId()){
                //存在，更新其x,y,和areaId,和时间
                user1.setX(user.getX());
                user1.setY(user.getX());
                user1.setNowArea(user.getNowArea());
                user1.setDate(new Date());
                user1.setIfShare(user.getIfShare());
                return;
            }
        }
        //不在，加进去
        aliveUserList.add(user);
    }

    private synchronized void checkUserAlive(){
        //默认超过10s没发送getLocation请求则认为该用户不在该区域了
        Date date = new Date();
        Iterator<User> iterator = aliveUserList.iterator();
        while (iterator.hasNext()){
            User user = iterator.next();
            long sec = (date.getTime() - user.getDate().getTime())/1000;
            if (sec >30){
                //超过60s移除用户
                iterator.remove();
            }
        }
    }

    private synchronized void initUserList(List<User> userList,int areaId,int nowUserId){
        for (User user : aliveUserList) {
            if (user.getIfShare()!=null && user.getIfShare() && user.getNowArea() == areaId && user.getId() != nowUserId)
                userList.add(user);
        }
    }

}
