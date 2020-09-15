package org.example.strategy.strategies;

import java.io.Serializable;
import java.util.Objects;

public class Entry implements Serializable {
    protected Long key;
    protected String value;
    protected Entry next;
    protected int hash;

    public Entry getNext() {
        return next;
    }

    public int getHash() {
        return hash;
    }

    public Entry(int hash, Long key, String value, Entry next) {
      this.hash = hash;
      this.key = key;
      this.value = value;
      this.next = next;
    }

    public Long getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public int hashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    
    public String toString() {
        return key + "=" + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry)) return false;
        Entry entry = (Entry) o;
        return key.equals(entry.key) &&
                value.equals(entry.value);
    }
}
