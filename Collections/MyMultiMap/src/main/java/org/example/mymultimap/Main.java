package org.example.mymultimap;

import java.util.Map;

/*
MyMultiMap
Реализовать параметризованную дженериками структуру данных, наследующуюся от HashMap,
и реализовывающую интерфейсы Cloneable и Serializable. Конструктор должен принимать число типа
int repeatCount - это количество значений, которое может хранится по одному ключу.

Должны быть реализованны следующие методы:
1) int size() - должен возвращать количество значений в MyMultiMap.
2) V put(K key, V value) - должен добавить элемент value по ключу key. Если в MyMultiMap такой ключ уже есть, и количество
 значений по этому ключу меньше, чем repeatCount - то добавить элемент value в конец листа в объекте map. Если по такому
 ключу количество значений равняется repeatCount - то удалть из листа в объекте map элемент с индексом ноль, и добавить в
 конец листа value. Метод должен возвращать значение последнего добавленного элемента по ключу key (но не значение,
 которое было добавлено сейчас). Если по ключу key значений еще нет метод должен вернуть null.
3) V remove(Object key) - должен удалить элемент по ключу key. Если по этому ключу хранится несколько элементов - должен
 удаляться элемент из листа с индексом ноль. Если по какому-то ключу хранится лист размером ноль элементов - нужно удалить
 пару ключ : значение. Метод должен возвращать удаленный элемент. Если в map нет ключа key метод должен вернуть null.
4) Set<K> keySet() - должен вернуть сет всех ключей, которые есть в map.
5) Collection<V> values() - должен вернуть ArrayList<V> всех значений. Порядок значений в листе не имеет значения.
6) boolean containsKey(Object key) - должен вернуть true, если в map присутствует ключ key, иначе вернуть false.
7) boolean containsValue(Object value) - должен вернуть true, если в map присутствует значение value, иначе вернуть false.
 */

public class Main {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new MyMultiMap<>(3);
        for (int i = 0; i < 7; i++) {
            map.put(i, i);
        }
        map.put(5, 56);
        map.put(5, 57);
        System.out.println(map.put(5, 58));             // Expected: 57

        System.out.println(map);                        // Expected: {0=0, 1=1, 2=2, 3=3, 4=4, 5=56, 57, 58, 6=6}
        System.out.println(map.size());                 // Expected: size = 9

        System.out.println(map.remove(5));          // Expected: 56
        System.out.println(map);                        // Expected: {0=0, 1=1, 2=2, 3=3, 4=4, 5=57, 58, 6=6}
        System.out.println(map.size());                 // Expected: size = 8

        System.out.println(map.keySet());               // Expected: [0, 1, 2, 3, 4, 5, 6]
        System.out.println(map.values());               // Expected: [0, 1, 2, 3, 4, 57, 58, 6]

        System.out.println(map.containsKey(5));         // Expected: true
        System.out.println(map.containsValue(57));      // Expected: true
        System.out.println(map.containsValue(7));       // Expected: false

    }
}
