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

    private Node<E> findMinNode (Node<E> delNode) {
        Node<E> minNode = null;
        Node<E> minNodeParent = null;
        Node<E> currentNode = delNode.rightBranch;
        while (currentNode != null) {
            minNodeParent = minNode;
            minNode = currentNode;
            currentNode = currentNode.leftBranch;
        }

        if (minNode != delNode.rightBranch) {
            minNodeParent.leftBranch = minNode.rightBranch;
            minNode.rightBranch = delNode.rightBranch;
        }
        return minNode;
    }

    // удаляет элемент из контейнера
    // если удаляемого элемента в контейнере нет, то возвращает false
    // в противном случае удаляет элемент и возвращает true
    // ВАЖНО! при удалении элемента дерево не должно потерять свойства бинарного дерева поиска
    public boolean remove(E element) {
        Node<E> current = root;
        Node<E> parent = root;
        boolean isLeftNode = false;
        while (current.element != element) {
            parent = current;
            if (element.compareTo(current.element) > 0) {
                isLeftNode = false;
                current = current.rightBranch;
            } else {
                isLeftNode = true;
                current = current.leftBranch;
            }
            if (current == null) {
                return false;
            }
        }

        if (current.leftBranch == null && current.rightBranch == null) {
            if (current == root) {
                root = null;
            }
            if (isLeftNode) {
                parent.leftBranch = null;
            } else {
                parent.rightBranch = null;
            }
        }
        else if (current.rightBranch == null) {
            if (current == root) {
                root = current.leftBranch;
            }
            if (isLeftNode) {
                parent.leftBranch = current.leftBranch;
            } else {
                parent.leftBranch = current.rightBranch;
            }
        }
        else if (current.leftBranch == null) {
            if (current == root) {
                root = current.rightBranch;
            }
            if (isLeftNode) {
                parent.leftBranch = current.rightBranch;
            } else {
                parent.rightBranch = current.rightBranch;
            }
        }
        else if (current.rightBranch != null && current.leftBranch != null) {
            Node<E> minNode = findMinNode(current);
            if (current == root) {
                root = minNode;
            }
            if (isLeftNode) {
                parent.leftBranch = minNode;
            } else {
                parent.rightBranch = minNode;
            }
            minNode.leftBranch = current.leftBranch;
        }
        return true;
    }

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
