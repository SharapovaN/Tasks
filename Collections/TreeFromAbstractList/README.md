TreeFromAbstractList
Написать свою реализацию дерева на базе AbstractList.
TreeFromAbstractList extends AbstractList implements Cloneable, Serializable{.....}

Добавление узлов в дерево должно происходить на самом низком уровне, поиск места для вставки узла
вести слева направо.

При удалении узла все его потомки так же удаляются.

Методы
public String get(int index)
public String set(int index, String element)
public void add(int index, String element)
public String remove(int index)
public List<String> subList(int fromIndex, int toIndex)
protected void removeRange(int fromIndex, int toIndex)
public boolean addAll(int index, Collection<? extends String> c)
должны бросать UnsupportedOperationException.
