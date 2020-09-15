package org.example.strategy;

import org.example.strategy.strategies.HashBiMapStorageStrategy;
import org.example.strategy.strategies.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {

    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date start = new Date();
        ids = Main.getIds(shortener, strings);
        Date end = new Date();
        return end.getTime() - start.getTime();
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date start = new Date();
        strings = Main.getStrings(shortener, ids);
        Date end = new Date();
        return end.getTime() - start.getTime();
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
                origStrings.add(Helper.generateRandomString());
        }

        long time1 = getTimeToGetIds(shortener1, origStrings, new HashSet<Long>());
        long time2 = getTimeToGetIds(shortener2, origStrings, new HashSet<Long>());

        Assert.assertTrue(time1 > time2);

        Set<Long> ids = Main.getIds(shortener1, origStrings);

        long t1 = getTimeToGetStrings(shortener1, ids, new HashSet<>());
        long t2 = getTimeToGetStrings(shortener2, ids, new HashSet<>());

        Assert.assertEquals(t1, t2, 30);
    }
}
