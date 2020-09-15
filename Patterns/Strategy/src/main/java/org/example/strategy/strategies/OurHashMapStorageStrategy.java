package org.example.strategy.strategies;

import java.util.Objects;

//Хранилище реализовано как коллекция аналогичная HashMap.

public class OurHashMapStorageStrategy implements StorageStrategy {
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    private float loadFactor = DEFAULT_LOAD_FACTOR;

    int hash(Long k) {
        return (Objects.hash(k));
    }

    int indexFor(int hash, int length) {
        return (length - 1) & hash;
    }

    Entry getEntry(Long key) {
        int hash = hash(key);
        Entry[] tab; Entry first, e; int n; Long k;
        if ((tab = table) != null && (n = tab.length) > 0 &&
                (first = tab[(n - 1) & hash]) != null) {
            if (first.getHash() == hash &&
                    ((k = first.getKey()) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.getNext()) != null) {
                do {
                    if (e.getHash() == hash &&
                            ((k = e.getKey()) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.getNext()) != null);
            }
        }
        return null;
    }

    void resize(int newCapacity) {
        table = new Entry[newCapacity];
    }

    void transfer(Entry[] newTable) {
        table = newTable;
    }

    void addEntry(int hash, Long key, String value, int bucketIndex) {
        Entry temp;
        if (table[bucketIndex] == null)
            table[bucketIndex] = new Entry(hash, key, value, null);
        else {
            temp = table[bucketIndex];
            table[bucketIndex] = new Entry(hash, key, value, temp);
        }
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        Entry[] tab; String v;
        if ((tab = table) != null) {
            for (Entry e : tab) {
                for (; e != null; e = e.getNext()) {
                    if ((v = e.getValue()) == value || (value != null && value.equals(v)))
                        return true;
                }
            }
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        int bucketIndex = indexFor(hash(key), table.length);
        addEntry(hash(key), key, value, bucketIndex);
    }

    @Override
    public Long getKey(String value) {
        for (Entry e : table) {
            if (e.getValue().equals(value))
                return e.getKey();
            while (e.getNext() != null) {
                e = e.getNext();
                if (e.getValue().equals(value))
                    return e.getKey();
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        for (Entry e : table) {
            if (e.getKey().equals(key))
                return e.getValue();
            while (e != null) {
                if (e.getKey().equals(key))
                    return e.getValue();
                e = e.getNext();
            }
        }
        return null;
    }
}
