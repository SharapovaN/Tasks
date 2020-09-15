package org.example.strategy.strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//Хранилише использует файлы в качестве buckets.

public class FileStorageStrategy implements StorageStrategy {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000L;
    private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    private long maxBucketSize;

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    public Entry[] toEntry(Long key) {
        List<Entry> temp = new ArrayList<>();
        Entry entry = table[indexFor(hash(key), table.length)].getEntry();
        temp.add(entry);
        while (entry.next != null) {
            entry = entry.next;
            temp.add(entry);
        }
        Entry[] tab = temp.toArray(new Entry[temp.size()]);
        return tab;
    }

    int hash(Long k) {
        return (Objects.hash(k));
    }

    int indexFor(int hash, int length) {
        return (length - 1) & hash;
    }

    Entry getEntry(Long key) {
        int hash = hash(key);
        Entry first, e; int n; Long k;
        Entry[] tab = toEntry(key);
        if ((tab) != null && (n = tab.length) > 0 &&
                (first = tab[(n - 1) & hash]) != null) {
            if (first.hash == hash && // always check first node
                    ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.next) != null) {
                do {
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }

    void resize(int newCapacity) {
        table = new FileBucket[newCapacity];
        for (FileBucket fileBucket : table) {
            fileBucket.remove();
        }
    }

    void transfer(FileBucket[] newTable) {
        for (int i = 0; i < newTable.length; i++) {
            table[i] = newTable[i];
        }
    }

    void addEntry(int hash, Long key, String value, int bucketIndex) {
        FileBucket temp;
        if (table[bucketIndex] == null) {
            table[bucketIndex] = new FileBucket();
            table[bucketIndex].putEntry(new Entry(hash, key, value, null));
        }
        else {
            Entry entry = new Entry(hash, key, value, null);
            Entry tempEntry = table[bucketIndex].getEntry();
            entry.next = tempEntry;
            table[bucketIndex].putEntry(entry);
        }
        if (table[bucketIndex].getFileSize() > getBucketSizeLimit()) {
            FileBucket[] newTable = table;
            resize(table.length*2);
            transfer(newTable);
        }
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        Entry[] temp;
        FileBucket[] tab;
        String v;
        if ((tab = table) != null) {
            for (FileBucket fileBucket : tab) {
                if(fileBucket != null) {
                    temp = toEntry(fileBucket.getEntry().getKey());
                    for (int i = 0; i < temp.length; i++) {
                         if ((v = temp[i].value) == value || (value != null && value.equals(v)))
                                return true;
                    }
                } else {
                    return false;
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
        for (FileBucket tab : table) {
            Entry[] temp = toEntry(tab.getEntry().key);
            for (int i = 0; i < temp.length; i++) {
                if (temp[i].getValue().equals(value))
                    return temp[i].getKey();
                while (temp[i].next != null) {
                    temp[i] = temp[i].next;
                    if (temp[i].getValue().equals(value))
                        return temp[i].getKey();
                }
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        Entry[] tab = toEntry(key);
        for (int i = 0; i < tab.length; i++) {
            if (tab[i].getKey().equals(key))
                return tab[i].getValue();
            while (tab[i] != null) {

                if (tab[i].getKey().equals(key))
                    return tab[i].getValue();
                tab[i] = tab[i].next;
            }
        }
        return null;
    }

}
