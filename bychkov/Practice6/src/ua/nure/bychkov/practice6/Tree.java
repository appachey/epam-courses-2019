package ua.nure.bychkov.practice6;

public class Tree <E extends Comparable<E>> {
    private Node<E> root = null;
    // добавляет элемент в контейнер
    // если в контейнере есть элемент равный по compareTo добавляемому,
    // то добавления не происходит и метод возвращает false
    // в противном случае элемент попадает в контейнер и метод возвращает true
    // первый добавляемый элемент становится корнем дерева
    // автобалансировки в дереве нет
    private boolean addElem(Node<E> element, E value) {
        Node<E> currentStruct = root;
        if (element == null) {
            element = new Node<>();
            element.element = value;
            if (root == null) {
                root = element;
                return true;
            }
            root = currentStruct;
            return true;
        }

        if (value.compareTo(element.element) < 0) {
            return addElem(currentStruct.leftBranch, value);
        }
        if (value.compareTo(element.element) > 0) {
            return addElem(currentStruct.rightBranch, value);
        }
        return false;
    }
    public boolean add(E element) {
       return addElem(root, element);
    }

    // добавляет все элементы из массива в контейнер (вызов в цикле метода add, см. выше)
    public void add(E[] elements) {}

    // удаляет элемент из контейнера
    // если удаляемого элемента в контейнере нет, то возвращает false
    // в противном случае удаляет элемент и возвращает true
    // ВАЖНО! при удалении элемента дерево не должно потерять свойства бинарного дерева поиска
    public boolean remove(E element) {return false;}

    // распечатывает дерево, так чтобы было видно его древовидную структуру, см. ниже пример
    public void print() {}

    // вложенный класс, объекты этого класса составляют дерево
    private static class Node<E> {
        private Node<E> leftBranch;
        private Node<E> rightBranch;
        private E element;

        Node() {
            element = null;
            leftBranch = null;
            rightBranch = null;
        }
    }
}
