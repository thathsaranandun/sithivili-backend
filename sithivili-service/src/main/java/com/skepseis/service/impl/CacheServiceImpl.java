package com.skepseis.service.impl;

import com.skepseis.rocksdb.repo.impl.RocksDBRepositoryImpl;
import com.skepseis.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    RocksDBRepositoryImpl rocksDBRepository;

    @Override
    public String findInCache(String key) {
        return rocksDBRepository.find(key);
    }

    @Override
    public void saveCacheData(String key, String value) {
        rocksDBRepository.save(key,value);
    }
}
