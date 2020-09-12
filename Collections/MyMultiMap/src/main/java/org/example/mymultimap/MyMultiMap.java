package org.example.mymultimap;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    //Метод возвращает количество значений в MyMultiMap.
    @Override
    public int size() {
        int size = 0;
        List<V> list;
        for (Entry<K, List<V>> pair : map.entrySet()) {
            list = pair.getValue();
            size += list.size();
        }
        return size;
    }

    //Метод добавляет элемент value по ключу key. Если в map такой ключ уже есть, и количество
    //значений по этому ключу меньше, чем repeatCount элемент value будет добавлен в конец листа в объекте map. Если по такому
    //ключу количество значений равняется repeatCount то из листа в объекте map будет удален элемент с индексом ноль, и в
    //конец листа будет добавлено value. Метод возвращает значение последнего добавленного элемента по ключу key.
    //Если по ключу key значений еще нет метод вернет null.
    @Override
    public V put(K key, V value) {
        V rValue = null;
        List<V> list;
        if (map.containsKey(key)) {
            list = map.get(key);
            rValue = list.get(list.size() - 1);
            if (list.size() == repeatCount) {
                list.remove(0);
            }
            list.add(list.size(), value);
        } else {
            list = new ArrayList<>();
            list.add(value);
            map.put(key, list);
        }
        return rValue;
    }

    //Метод удаляет элемент по ключу key. Если по этому ключу хранится несколько элементов метож удалить элемент из листа
    //с индексом ноль. Если по какому-то ключу хранится лист размером ноль элементов метод удалит пару ключ : значение.
    //Метод возвращает удаленный элемент. Если в map нет ключа key метод вернет null.
    @Override
    public V remove(Object key) {
        V removedValue = null;
        List<V> list;
        if (map.containsKey(key)) {
            list = map.get(key);
            if (list.size() > 0) {
                removedValue = list.remove(0);
            }
            if (list.size() == 0) {
                map.remove(key);
            }
        }
        return removedValue;
    }

    //Метод вернет сет всех ключей, которые есть в map
    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    //Метод вернет ArrayList<V> всех значений
    @Override
    public Collection<V> values() {
        ArrayList<V> list = new ArrayList<>();
        List<V> temp;
        for (Entry pair : map.entrySet()) {
            temp = (List<V>) pair.getValue();
            list.addAll(temp);
        }
        return list;
    }

    //Метод вернет true, если в map присутствует ключ key, иначе вернет false
    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    //Метод вернет true, если в map присутствует значение value, иначе вернет false
    @Override
    public boolean containsValue(Object value) {
        boolean returnedValue = false;
        List<V> temp;
        for (Entry pair : map.entrySet()) {
            temp = (List<V>) pair.getValue();
            returnedValue = temp.contains(value);
            if (returnedValue) {
                break;
            }
        }
        return returnedValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}