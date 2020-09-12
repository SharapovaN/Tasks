package org.example.myarraylist;

import java.util.Collections;
/*
MyArrayList
Написать свою реализацию ArrayList на основе массива.
class MyArrayList<T> implements List<T>{...}

Проверить, что на ней работают методы из java.util.Collections:
Collections.addAll(Collection<? super T> c, T... elements)
Collections.static <T> void copy(List<? super T> dest, List<? extends T> src)
Collections.static <T> void sort(List<T> list, Comparator<? super T> c)

1) Проверяйте на коллекциях с 20 и больше элементами.
2) MyArrayList должен имплементировать ТОЛЬКО ОДИН интерфейс - List.
3) Если метод не имплементирован, то он должен выбрасывать исключение UnsupportedOperationException.
 */

public class Main {
    public static void main(String[] args) {

        var myList = new MyArrayList<Integer>();
        var forCopy = new MyArrayList<Integer>();

        for (int i = 25; i > 0; i--) {
            myList.add(i);
        }

        for (int i = 100; i < 129; i++) {
            forCopy.add(i);
        }
        System.out.println("Исходная коллекция: "+myList);
        System.out.println();

        Collections.addAll(myList, 45, 47, 48, 49);
        System.out.println("Коллекция после применения метода addAll: "+myList);
        System.out.println();

        Collections.sort(myList);
        System.out.println("Отсортированная коллекция: "+myList);
        System.out.println();

        Collections.copy(forCopy, myList);
        System.out.println("Копия исходной коллекции 'ForCopy': "+forCopy);

    }
}
