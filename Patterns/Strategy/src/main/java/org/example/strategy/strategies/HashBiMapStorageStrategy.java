package org.example.strategy.strategies;

import com.google.common.collect.HashBiMap;

//Хранилище на основе сторонней библиотеки.

public class HashBiMapStorageStrategy implements StorageStrategy {
    private HashBiMap<Long, String> data = HashBiMap.create();

    @Override
    public boolean containsKey(Long key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return data.containsValue(value);
    }

    @Override
    public void put(Long key, String value) {
        data.put(key, value);
    }

    @Override
    public Long getKey(String value) {
        return data.inverse().get(value);
    }

    @Override
    public String getValue(Long key) {
        return data.get(key);
    }
}
