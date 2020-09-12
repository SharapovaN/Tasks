package org.example.TreeFromAbstractList;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;
    private int size = 0;
    ArrayList<Entry> entryList = new ArrayList<>();

    public CustomTree() {
        this.root = new Entry<>("0");
        root.level = 0;
        entryList.add(root);
    }

    //Метод возвращает имя родительского узела для узла, имя которого было получено в качестве параметра.
    //Если узла с заданным именем нет в дереве метод вернет "null".
    public String getParent(String name) {
        for (Entry entry : entryList) {
            if (entry.elementName.equals(name))
                return entry.parent.elementName;
        }
        return "null";
    }

    //Добавляет узел с заданным именем в дерево. Поиск места вставки идет слева направо на самом низком уровне.
    @Override
    public boolean add(String s) {
        Entry newElement = new Entry(s);
        ArrayList<Entry> temp = new ArrayList<>(entryList);

        for(int i = 0; i < entryList.size(); i++) {
            Entry parent = temp.remove(0);
            if (parent.isAvailableToAddChildren()) {
                newElement.parent = parent;
                newElement.level = parent.level + 1;
                entryList.add(newElement);
                size++;
                if (parent.availableToAddLeftChildren) {
                    parent.availableToAddLeftChildren = false;
                    parent.leftChild = newElement;
                    return true;
                } else {
                    parent.availableToAddRightChildren = false;
                    parent.rightChild = newElement;
                    return true;
                }
            }
        }
        refresh(entryList);
        add(s);
       return true;
    }

    //Метод удаляет узел с заданой вершиной из дерева вместе со всеми потомками.
    @Override
    public boolean remove(Object o) {
        if (o.getClass().toString().contains("String")) {
        Entry removed;

        Entry temp = null;
        for (Entry entry : entryList) {
            if (entry.elementName.equals(o))
                temp = entry;
        }
        removed = entryList.remove(entryList.indexOf(temp));
        size--;

        if (removed != null && removed.leftChild != null) {
            remove(removed.leftChild.elementName);
        }
        if (removed != null && removed.rightChild != null) {
            remove(removed.rightChild.elementName);
        }

        } else {
            throw new UnsupportedOperationException();
        }
        return true;
    }

    //Метод обновляет данные об узлах дерева
    public void refresh (ArrayList<Entry> entryList) {
        for (Entry entry : entryList){
            if (entry.leftChild != null || entry.rightChild != null) {
                if (entry.leftChild != null && !entryList.contains(entry.leftChild)) {
                    entry.leftChild = null;
                    entry.availableToAddLeftChildren = true;
                }
                if (entry.rightChild != null && !entryList.contains(entry.rightChild)) {
                    entry.rightChild = null;
                    entry.availableToAddRightChildren = true;
                }
            }
        }
    }

    //Метод возвращает количество вершин в дереве
    @Override
    public int size() {
        return size;
    }

    //Класс описывает элемент дерева
    static class Entry<T> implements Serializable {
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;
        int level;


        public Entry (String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        //Метод возвращает true если данному узлу возможно добавить потомка
        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren||availableToAddRightChildren;
        }

    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }
}
