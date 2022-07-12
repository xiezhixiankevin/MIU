package com.example.miu.cache;

import org.springframework.stereotype.Service;

@Service
public class UserChannelCache extends BaseSetCache<String>{
    private static final String USER_CHANNEL_CACHE = "USER_CHANNEL_CACHE_";
    @Override
    public String getKey(String key) {
        return USER_CHANNEL_CACHE+key;
    }
}
