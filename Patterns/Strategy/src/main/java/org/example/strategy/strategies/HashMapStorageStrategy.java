package org.example.strategy.strategies;

import java.util.HashMap;
import java.util.Map;

//Хранилище данных на основе HashMap. Ключ - идентификатор строки, значение - сама строка.

public class HashMapStorageStrategy implements StorageStrategy {
    private HashMap<Long, String> data = new HashMap<>();

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
        Long result = 0L;
        for (Map.Entry<Long, String> pair : data.entrySet()) {
            if (pair.getValue().equals(value))
                result = pair.getKey();
        }
        return result;
    }

    @Override
    public String getValue(Long key) {
        return data.get(key);
    }
}
