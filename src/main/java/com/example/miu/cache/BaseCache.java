package com.example.miu.cache;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Slf4j
public abstract class BaseCache<T> {
    @Autowired
    protected StringRedisTemplate redisTemplate;

    public void setDataToCache(String key, T data){
        String storeKey = getKey(key);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(storeKey, JSON.toJSONString(data));
    }

    public void setDataToCacheWithExpireTime(String key, T data, int time){
        String storeKey = getKey(key);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(storeKey, JSON.toJSONString(data), time);
    }

    public T getDataFromCache(String key){
        String storeKey = getKey(key);
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String result = valueOperations.get(storeKey);
        Type superclass = getClass().getGenericSuperclass();
        if(superclass instanceof ParameterizedType){
            Type actualTypeArgument = ((ParameterizedType) superclass).getActualTypeArguments()[0];
            return JSON.parseObject(result,actualTypeArgument);
        }
        throw new RuntimeException("泛型类型错误");
    }

    public boolean deleteDataFromCache(String key){
        String storeKey = getKey(key);
        return redisTemplate.delete(storeKey);
    }
    public abstract String getKey(String key);
}
