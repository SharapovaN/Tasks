package org.example.strategy;

import org.example.strategy.strategies.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
      testStrategy(new HashMapStorageStrategy(), 10000);
      testStrategy(new OurHashMapStorageStrategy(), 10000);
      testStrategy(new FileStorageStrategy(), 100);
      testStrategy(new OurHashBiMapStorageStrategy(), 10000);
      testStrategy(new HashBiMapStorageStrategy(), 10000);
      testStrategy(new DualHashBidiMapStorageStrategy(), 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> result = new HashSet<>();
        for (String string : strings) {
            result.add(shortener.getId(string));
        }
        return result;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> result = new HashSet<>();
        for (Long id : keys) {
            result.add(shortener.getString(id));
        }
        return result;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> testStrings = new HashSet<>();
        for (int i = 1; i <= elementsNumber; i++) {
           testStrings.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        Date start = new Date();
        Set<Long> ids = getIds(shortener, testStrings);
        Date end = new Date();
        Helper.printMessage(String.valueOf(end.getTime() - start.getTime()));

        Date start1 = new Date();
        Set<String> strings = getStrings(shortener, ids);
        Date end1 = new Date();
        Helper.printMessage(String.valueOf(end1.getTime() - start1.getTime()));

        if (strings.containsAll(testStrings) && strings.size() == testStrings.size())
            Helper.printMessage("Тест пройден.");
        else {
            Helper.printMessage("Тест не пройден.");
        }
    }
}
