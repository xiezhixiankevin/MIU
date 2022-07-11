package com.example.miu.cache;

import org.springframework.stereotype.Service;

@Service
public class TokenCache extends BaseCache<String>{

    private static final String TOKEN_CATEGORY = "shiro_token";
    @Override
    public String getKey(String key) {
        return String.format("%s_%s", TOKEN_CATEGORY, key);
    }
}
