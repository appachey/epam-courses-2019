package ua.nure.bychkov.practice6.part5;

public class Tree <E extends Comparable<E>> {
    private Node<E> root = null;

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
        else {
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

    private void print(Node<E> node, int offset) {
        String template = "%d\n";
        if (node != root) {
            template = "%" + offset + "d\n";
        }
        if(node != null){
            print(node.leftBranch, offset += 2 + node.element.toString().length());
            System.out.printf(template, node.element);
            print(node.rightBranch, offset);
        }
    }


    public void print() {
        print(root, root.element.toString().length());
    }

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
