package org.example.strategy;


import org.example.strategy.strategies.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {

    public void testStorage(Shortener shortener) {
        String s1 = Helper.generateRandomString();
        String s2 = Helper.generateRandomString();
        String s3 = s1;

        Long id1 = shortener.getId(s1);
        Long id2 = shortener.getId(s2);
        Long id3 = shortener.getId(s3);

        Assert.assertNotEquals(id1, id2);
        Assert.assertNotEquals(id2, id3);
        Assert.assertEquals(id1, id3);

        String st1 = shortener.getString(id1);
        Assert.assertEquals(s1, st1);

        String st2 = shortener.getString(id2);
        Assert.assertEquals(s2, st2);

        String st3 = shortener.getString(id3);
        Assert.assertEquals(s3, st3);
    }

    @Test
    public void testHashMapStorageStrategy() {
      Shortener shortener = new Shortener(new HashMapStorageStrategy());
      testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
       Shortener shortener = new Shortener(new OurHashMapStorageStrategy());
       testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        Shortener shortener = new Shortener(new FileStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashBiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        Shortener shortener = new Shortener(new DualHashBidiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        Shortener shortener = new Shortener(new OurHashBiMapStorageStrategy());
        testStorage(shortener);
    }
}