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

    private boolean delete(E e, Node<E> root) {
        if (root == null) {
            return false;
        }
        if (e == root.element) {
            if (root.rightBranch == null && root.leftBranch == null) {
                root = null;
            } else if (root.rightBranch == null) {
                root = root.leftBranch;
            } else if (root.leftBranch == null) {
                root = root.rightBranch;
            } else

                root.element = minValue(root.leftBranch);
            return delete(root.element, root.leftBranch);
            // Delete the inorder successor

        } else if (e.compareTo(root.element) < 0) {
            return delete(e, root.leftBranch);
        } else {
            return delete(e, root.rightBranch);
        }
        //return true;
    }

    private E minValue(Node<E> root) {
        E minv = root.element;
        while (root.rightBranch != null) {
            minv = root.rightBranch.element;
            root = root.rightBranch;
        }
        return minv;
    }

    public boolean remove(E element) {
        return delete(element, root);
    }

//    public boolean remove(E element) {
//        Node<E> current = root;
//        Node<E> parent = root;
//        boolean isLeftNode = false;
//        while (current.element != element) {
//            parent = current;
//            if (element.compareTo(current.element) > 0) {
//                isLeftNode = false;
//                current = current.rightBranch;
//            } else {
//                isLeftNode = true;
//                current = current.leftBranch;
//            }
//            if (current == null) {
//                return false;
//            }
//        }
//
//        if (current.leftBranch == null && current.rightBranch == null) {
//            if (current == root) {
//                root = null;
//            }
//            if (isLeftNode) {
//                parent.leftBranch = null;
//            } else {
//                parent.rightBranch = null;
//            }
//        } else if (current.rightBranch == null) {
//            if (current == root) {
//                root = current.leftBranch;
//            }
//            if (isLeftNode) {
//                parent.leftBranch = current.leftBranch;
//            } else {
//                parent.rightBranch = current.leftBranch;
//            }
//        } else if (current.leftBranch == null) {
//            if (current == root) {
//                root = current.rightBranch;
//            }
//            if (isLeftNode) {
//                parent.leftBranch = current.rightBranch;
//            } else {
//                parent.rightBranch = current.rightBranch;
//            }
//        } else {
//            Node<E> minNode = findMinNode(current);
//            if (current == root) {
//                root = minNode;
//            }
//            if (isLeftNode) {
//                parent.leftBranch = minNode;
//            } else {
//                parent.rightBranch = minNode;
//            }
//            minNode.leftBranch = current.leftBranch;
//        }
//        return true;
//    }

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

    private String addSpaces (int count) {
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
