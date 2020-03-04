package com.skepseis.service;

public interface CacheService {
    String findInCache(String key);
    void saveCacheData(String key, String value);
}
