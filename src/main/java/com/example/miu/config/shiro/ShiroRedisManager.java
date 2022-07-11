package com.example.miu.config.shiro;

import cn.easyproject.shirorediscache.RedisManager;
import com.example.miu.cache.ShiroCache;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRedisManager extends RedisManager {
    @Autowired
    private ShiroCache shiroCache;
    @Override
    public void init(){}

    @Override
    public byte[] get(byte[] key){
        return shiroCache.getDataFromCache(key);
    }

    @Override
    public byte[] set(byte[] key, byte[] value){
        shiroCache.setDataToCache( key, value);
        return value;
    }

    @Override
    public byte[] set(byte[] key, byte[] value, int expire){
        shiroCache.setDataToCacheWithExpireTime(key, value, expire);
        return value;
    }

    @Override
    public void del(byte[] key){
        shiroCache.deleteDataFromCache(key);
    }


}
