package ua.nure.bychkov.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {
    private Node firstElem;
    private Node lastElem;
    private int size;

    public ListImpl() {
        firstElem = null;
        lastElem = null;
        size = 0;
    }

    public static void main(String[] args) {
        List list = new ListImpl();
        System.out.println("***addLast***");
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");
        System.out.println(list);
        System.out.println("***addFirst***");
        list.addFirst("D");
        System.out.println(list);
        System.out.println(list.size());
        list.removeFirst();
        list.removeLast();
        System.out.println("***Remove First and Last***");
        System.out.println(list);
        System.out.println(list.size());
        System.out.println("***Get First***");
        System.out.println(list.getFirst());
        System.out.println("***Get Last***");
        System.out.println(list.getLast());
        System.out.println("***Search Element B***");
        System.out.println(list.search("B"));
        System.out.println("***Search Element Z***");
        System.out.println(list.search("Z"));
        System.out.println("***Remove Element B***");
        list.remove("B");
        System.out.println(list.size());
        System.out.println(list);
        System.out.println("***Clear List***");
        list.addLast("b");
        list.addLast("c");
        list.addLast("d");
        System.out.println(list);
        System.out.println(list.size());
        list.clear();
        System.out.println("***After Clear***");
        System.out.println(list.size());
        System.out.println(list);
        System.out.println("***Iterator***");
        list.addLast("a");
        list.addLast("b");
        list.addLast("c");
        list.addLast("d");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("***Remove***");
        it = list.iterator();
        System.out.println(it.next());
        it.remove();
        System.out.println(list);
        try {
            it.remove();
        } catch (IllegalStateException e) {
            System.out.println("Exception");
        }
    }

    private static class Node {
        private Object val;
        private Node prev;
        private Node next;

        public Node() {
            val = null;
            prev = null;
            next = null;
        }
    }

    @Override
    public void addFirst(Object element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        Node currentFirst = firstElem;
        Node newNode = new Node();
        newNode.next = currentFirst;
        newNode.val = element;
        firstElem = newNode;
        if (currentFirst == null) {
            lastElem = newNode;
        } else {
            currentFirst.prev = newNode;
        }
        size++;
    }

    @Override
    public void addLast(Object element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        Node currentLast = lastElem;
        Node newNode = new Node();
        newNode.prev = currentLast;
        newNode.val = element;
        lastElem = newNode;
        if (currentLast == null) {
            firstElem = newNode;
        } else {
            currentLast.next = newNode;
        }
        size++;
    }

    @Override
    public void removeFirst() {
        firstElem = firstElem.next;
        firstElem.prev = null;
        size--;
    }

    @Override
    public void removeLast() {
        lastElem = lastElem.prev;
        lastElem.next = null;
        size--;
    }

    @Override
    public Object getFirst() {
        return firstElem.val;
    }

    @Override
    public Object getLast() {
        return lastElem.val;
    }

    @Override
    public Object search(Object element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        Node elem = firstElem;
        for (int i = 0; i < size; i++) {
            if (element.equals(elem.val)) {
                return elem.val;
            }
            elem = elem.next;
        }
        return null;
    }

    @Override
    public boolean remove(Object element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        Node elem = firstElem;
        for (int i = 0; i < size; i++) {
            if (element.equals(elem.val)) {
                Node prevElem = elem.prev;
                Node nextElem = elem.next;
                if (prevElem == null) {
                    firstElem = nextElem;
                } else {
                    prevElem.next = nextElem;
                    elem.prev = null;
                }

                if (nextElem == null) {
                    lastElem = prevElem;
                } else {
                    nextElem.prev = prevElem;
                    elem.next = null;
                }
                elem.val = null;
                size--;
                return true;
            }
            elem = elem.next;
        }
        return false;
    }

    @Override
    public void clear() {
        Node elem = firstElem;
        while (elem.next != null) {
            Node nextElem = elem.next;
            elem.val = null;
            elem.next = null;
            elem.prev = null;
            elem = nextElem;
        }
        firstElem = null;
        lastElem = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    @Override
    public String toString() {
        Node elem = firstElem;
        StringBuilder output = new StringBuilder("[");
        if (size == 0) {
            return "[]";
        }
        if (elem.next == null) {
            output.append(elem.val).append("]");
            return output.toString();
        }
        while (elem.next != null) {
            output.append(elem.val).append(", ");
            elem = elem.next;
        }
        output.append(elem.val).append("]");
        return output.toString();
    }

    private class IteratorImpl implements Iterator<Object> {
        private Node next;
        private Node lastReturned;
        private int nextInd;

        IteratorImpl() {
            next = firstElem;
            lastReturned = null;
            nextInd = 0;
        }

        @Override
        public boolean hasNext() {
            return nextInd < size;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = next;
            next = next.next;
            nextInd++;
            return lastReturned.val;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            Node lastNext = lastReturned.next;

            Node prevElem = lastReturned.prev;
            Node nextElem = lastReturned.next;
            if (prevElem == null) {
                firstElem = nextElem;
            } else {
                prevElem.next = nextElem;
                lastReturned.prev = null;
            }

            if (nextElem == null) {
                lastElem = prevElem;
            } else {
                nextElem.prev = prevElem;
                lastReturned.next = null;
            }
            lastReturned.val = null;
            size--;
            if (next == lastReturned) {
                next = lastNext;
            } else {
                nextInd--;
            }
            lastReturned = null;
        }
    }
}
