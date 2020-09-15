package org.example.proxy;

public interface Storage {
    void add(Object storedObject);

    Object get(long id);
}
