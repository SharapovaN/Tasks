package org.example.myhashset;

import java.io.*;
import java.util.Set;
/*
MyHashSet
Написать свою реализацию HashSet.
class MyHashSet<E> extends AbstractSet{...}

Напиcать свою реализацию следующих методов при условии, что нужно работать с ключами Map:
* Iterator<E> iterator()
* int size()
* boolean isEmpty()
* boolean contains(Object o)
* void clear()
* boolean remove(Object o)

Написать свою реализацию метода Object clone(), сделать поверхностное клонирование.
* В случае возникновения исключений выбросить InternalError.

Реализовать сериализацию и десериализацию.

 */

public class Main {
    public static void main(String[] args) throws IOException {
      MyHashSet<Integer> set = new MyHashSet();

      set.add(1);
      for (int i = 0; i < 10; i++) {
          set.add(i);
      }

      //Проверка клонирования
      MyHashSet clonedSet = set.clone();
      System.out.println("Ожидаемый ответ true. Ответ - " + set.equals(clonedSet));

      System.out.println("Ожидаемый ответ 10. Ответ - " + set.size());
      System.out.println("Ожидаемый ответ true. Ответ - " + set.contains(1));
      System.out.println("Ожидаемый ответ false. Ответ - " + set.isEmpty());
      set.remove(1);
      System.out.println("Ожидаемый ответ false. Ответ - " + set.contains(1));

      //Проверка сериализации/десериализации
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.txt"))) {
            oos.writeObject(set);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.txt"))) {
            MyHashSet<Integer> newSet = (MyHashSet)ois.readObject();
            System.out.println("Ожидаемый ответ true. Ответ - " + newSet.equals(set));
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        set.clear();
        System.out.println("Ожидаемый ответ 0. Ответ - " + set.size());

    }
}
