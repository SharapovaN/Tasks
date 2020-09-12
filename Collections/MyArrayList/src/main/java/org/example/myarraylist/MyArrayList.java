package org.example.myarraylist;

import java.util.*;
import java.util.function.UnaryOperator;

public class MyArrayList<E> implements List<E> {
    private int size = 0;
    private int capacity = 10;
    private Object[] array = new Object[capacity];
    private int index = 0;


    @Override
    public String toString() {
        Object[] arrayCopy= Arrays.copyOf(array, size);
        return Arrays.toString(arrayCopy);
    }

    @Override
    public boolean add(E e) {

        if (index == array.length - 1) {
            growArray();
        }
        array[index] = e;
        index++;
        size++;
        return true;
    }

    private void growArray() {
        E[] newArray = (E[]) new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        throw new UnsupportedOperationException();
    }


    @Override
    public Spliterator<E> spliterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(array, size, a.getClass());
        System.arraycopy(array, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[]a=c.toArray();
        int l=a.length;
        if(l==0){
            return  false;
        }
        if(array.length<(size+l)){
            growArray();
        }
        System.arraycopy(a, 0, array, size-1, l);
        size+=l;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E get(int index) {
        if(index < 0 || index >= size()){
            return (E) array[index];
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        final int[] cursor = {0};

        ListIterator<E> iterator = new ListIterator<E>() {

            @Override
            public boolean hasNext() {
                if (cursor[0] < size) return true;
                return false;
            }

            @Override
            public E next() {
                if (cursor[0] >= size) {
                    throw new NoSuchElementException("next");

                }
                cursor[0]++;
                return (E) array[cursor[0] - 1];

            }

            @Override
            public boolean hasPrevious() {
                if(cursor[0] !=0&& cursor[0] <=size) return true;
                return  false;
            }

            @Override
            public E previous() {
                if(cursor[0] ==0|| cursor[0] >size){
                    throw new NoSuchElementException("prev");
                }
                cursor[0]--;
                return (E) array[cursor[0] +1];
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException("nextIndex");
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException("prevIndex");
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(E e) {
                if (cursor[0] <= size) {
                    array[cursor[0] -1] = e;
                }
            }

            @Override
            public void add(E e) {
                throw new UnsupportedOperationException();
            }
        };
        return iterator;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}
