package com.example.miu.cache;

public class CIMCache extends BaseCache<String>{
    private static final String APNS_DEVICE_TOKEN_CATEGORY = "APNS_OPEN_%s";
    @Override
    public String getKey(String key) {
        return String.format(String.format(APNS_DEVICE_TOKEN_CATEGORY,key));
    }
}
