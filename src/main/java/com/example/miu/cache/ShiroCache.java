package com.example.miu.cache;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class ShiroCache {
    @Resource
    protected RedisTemplate redisTemplate;


    public byte[] getDataFromCache(byte[] key){;
        ValueOperations<byte[], byte[]> valueOperations = this.redisTemplate.opsForValue();
        return valueOperations.get(key);

    }

    public void setDataToCache(byte[] key, byte[] data){

        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, data);
    }

    public void setDataToCacheWithExpireTime(byte[] key, byte[] data, int time){

        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, data, time);
    }

    public boolean deleteDataFromCache(byte[] key){
        return redisTemplate.delete(key);
    }

}
