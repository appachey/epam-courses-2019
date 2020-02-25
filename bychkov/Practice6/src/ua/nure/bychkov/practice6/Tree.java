package ua.nure.bychkov.practice6;

public class Tree <E extends Comparable<E>> {
    private Node<E> root = null;
    // добавляет элемент в контейнер
    // если в контейнере есть элемент равный по compareTo добавляемому,
    // то добавления не происходит и метод возвращает false
    // в противном случае элемент попадает в контейнер и метод возвращает true
    // первый добавляемый элемент становится корнем дерева
    // автобалансировки в дереве нет

    public boolean add(E element) {
        Node<E> node = new Node<>();
        node.element = element;
       if (root == null) {
           root = node;
           return true;
       }
       Node<E> curent = root;
       Node<E> parent;
       while (true) {
           parent = curent;
           if (element.compareTo(curent.element) < 0) {
               curent = curent.leftBranch;
               if (curent == null) {
                   parent.leftBranch = node;
                   return true;
               }
           } else if (element.compareTo(curent.element) > 0) {
               curent = curent.rightBranch;
               if (curent == null) {
                   parent.rightBranch = node;
                   return true;
               }
           } else {
               return false;
           }
       }
    }

    // добавляет все элементы из массива в контейнер (вызов в цикле метода add, см. выше)
    public void add(E[] elements) {
        for (E element : elements) {
            add(element);
        }
    }

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
