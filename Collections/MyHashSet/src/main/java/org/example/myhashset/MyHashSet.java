package org.example.myhashset;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class MyHashSet<E> extends AbstractSet implements Serializable, Cloneable, Set {
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;


    public MyHashSet() {
        map = new HashMap<>();
    }

    public MyHashSet(Collection<? extends E> collection) {
        map = new HashMap(Math.max(16, (int) (collection.size()/.75f + 1)));
        for (E e : collection) {
            map.put(e, PRESENT);
        }
    }

    public boolean add(Object e) {
        return map.put((E) e, PRESENT)==null;
    }


    @Override
    public Iterator iterator() {
        Iterator<E> iterator = map.keySet().iterator();
        return iterator;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o) != null;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public MyHashSet clone() throws InternalError {
        try {
            MyHashSet copy = (MyHashSet) super.clone();
            copy.map = (HashMap) map.clone();
            return copy;
        } catch (Exception e) {
            throw new InternalError();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();

        Set<E> keySet = map.keySet();

        Object [] array = keySet.toArray();
        objectOutputStream.writeObject(array);

        Object loadFactor = HashMapReflectionHelper.callHiddenMethod(map,"loadFactor");
        objectOutputStream.writeObject(loadFactor);

        Object capacity = HashMapReflectionHelper.callHiddenMethod(map,"capacity");
        objectOutputStream.writeObject(capacity);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();

        Object[] arrayKeys = (Object[]) objectInputStream.readObject();
        float loadFactor = (float) objectInputStream.readObject();
        int capacity = (int) objectInputStream.readObject();

        map = new HashMap<>(capacity, loadFactor);

        for (Object element : arrayKeys) {
            map.put((E) element, PRESENT);
        }
    }
}
