package ua.nure.bychkov.practice6.part5;

public class Tree <E extends Comparable<E>> {
    private Node<E> root;

    public boolean add(E element) {
        Node<E> node = new Node<>(element);
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

    public int getSize() {
        return getSizeRec(root);
    }

    private int getSizeRec(Node<E> current) {
        return current == null ? 0 : (getSizeRec(current.leftBranch) + 1 + getSizeRec(current.rightBranch));
    }

    public void add(E[] elements) {
        for (E element : elements) {
            add(element);
        }
    }

    private E findMinVal(Node<E> node) {
        return node.leftBranch == null ? node.element : findMinVal(node.leftBranch);
    }

    private Node<E> isNodeNull(Node<E> node) {
        if (node.leftBranch == null && node.rightBranch == null) {
            return null;
        }

        if (node.rightBranch == null) {
            return node.leftBranch;
        }

        if (node.leftBranch == null) {
            return node.rightBranch;
        }
        E minVal = findMinVal(node.rightBranch);
        node.element = minVal;
        node.rightBranch = delRec(node.rightBranch, minVal);
        return node;
    }

    private Node<E> delRec(Node<E> current, E value) {
        if (current == null) {
            return null;
        }

        if (value.compareTo(current.element) == 0) {
            return isNodeNull(current);
        }
        if (value.compareTo(current.element) < 0) {
            current.leftBranch = delRec(current.leftBranch, value);
            return current;
        }

        current.rightBranch = delRec(current.rightBranch, value);
        return current;
    }

    public boolean remove(E element) {
        int startSize = getSize();
        root = delRec(root, element);
        int currentSize = getSize();
        return startSize - 1 == currentSize;
    }

    private void print(Node<E> node, int offset) {
        String template = "";
        if (node != root) {
            template = addSpaces(offset);
        }
        if(node != null){
            offset += 2;
            print(node.leftBranch, offset);
            System.out.println(template + node.element);
            print(node.rightBranch, offset);
        }
    }

    private static String addSpaces (int count) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < count; i++) {
            output.append(" ");
        }
        return output.toString();
    }

    public void print() {
        print(root, 0);
    }

    private static class Node<E> {
        private Node<E> leftBranch;
        private Node<E> rightBranch;
        private E element;

        Node(E value) {
            element = value;
            leftBranch = null;
            rightBranch = null;
        }
    }
}
