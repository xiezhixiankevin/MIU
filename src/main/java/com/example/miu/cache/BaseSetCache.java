package com.example.miu.cache;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;


public abstract class BaseSetCache<T> {
    @Autowired
    protected RedisTemplate redisTemplate;

    public void add(String key, T value) {
        key =getKey(key);
        redisTemplate.opsForSet().add(key, JSON.toJSONString(value));
    }
    public void remove(String key, T value) {
        key =getKey(key);
        redisTemplate.opsForSet().remove(key, JSON.toJSONString(value));
    }
    public void contains(String key, T value) {
        key =getKey(key);
        redisTemplate.opsForSet().isMember(key,  JSON.toJSONString(value));
    }
    public Set<T> values(String key) {
        key =getKey(key);
        Set<String> opsResult = redisTemplate.opsForSet().members(key);
        Set<T> result = new HashSet<>();
        Type superclass = getClass().getGenericSuperclass();

        if(superclass instanceof ParameterizedType){
            Type actualTypeArgument = ((ParameterizedType) superclass).getActualTypeArguments()[0];
            for(String item : opsResult){
                result.add(JSON.parseObject(item,actualTypeArgument));
            }
        }

        return result;
    }
    public void removeKey(String key){
        key =getKey(key);
        redisTemplate.delete(key);
    }
    public abstract String getKey(String key);
}
