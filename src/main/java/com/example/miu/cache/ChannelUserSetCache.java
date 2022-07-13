package com.example.miu.cache;

import org.springframework.stereotype.Service;

@Service
public class ChannelUserSetCache extends BaseSetCache<String>{
    private static final String CHANNEL_USER_CATEGORY = "CHANNEL_USER_CATEGORY_";
    @Override
    public String getKey(String channelId) {
        return CHANNEL_USER_CATEGORY+channelId;
    }
}
