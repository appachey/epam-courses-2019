package ua.nure.bychkov.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {
    private Node head;
    private Node tail;
    private int size;

    public QueueImpl() {
        head = null;
        tail = null;
        size = 0;
    }

    public static void main(String[] args) {
        Queue queue = new QueueImpl();
        System.out.println(queue);
        System.out.println("***Adding elements to queue***");
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        System.out.println(queue);
        System.out.println(queue.size());
        System.out.println("***Return first element***");
        System.out.println(queue.top());
        System.out.println("***Return and remove first element***");
        System.out.println(queue.dequeue());
        System.out.println(queue);
        System.out.println(queue.size());
        System.out.println("***Return and remove first element***");
        System.out.println(queue.dequeue());
        System.out.println(queue.size());
        System.out.println("***Adding Z element to queue***");
        queue.enqueue("Z");
        System.out.println(queue);
        System.out.println(queue.size());
        System.out.println("***Clear Queue***");
        queue.clear();
        System.out.println(queue);
        System.out.println(queue.size());
        System.out.println("***Iterator***");
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        Iterator it = queue.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("***Iterator remove***");
        it = queue.iterator();
        System.out.println(it.next());
        it.remove();
        try {
            it.remove();
        } catch (IllegalStateException e) {
            System.out.println("exception");
        }
        System.out.println(queue);
        System.out.println("***ForEach***");
        for (Object element : queue) {
            System.out.println(element);
        }
    }

    private static class Node {
        private Object element;
        private Node next;
        private Node prev;

        Node() {
            element = null;
            next = null;
            prev = null;
        }
    }

    @Override
    public void enqueue(Object element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        Node newNode = new Node();
        newNode.element = element;
        if (head == null && tail == null) {
            head = newNode;
            tail = newNode;
            size++;
        } else if (head != null && head.next == null) {
            head.next = newNode;
            newNode.prev = head;
            tail = newNode;
            size++;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            size++;
        }
    }

    @Override
    public Object dequeue() {
        if (head != null) {
            Object element = head.element;
            if (head.next == null) {
                tail = null;
                head = null;
                size--;
                return element;
            } else {
                head = head.next;
                head.prev = null;
                size--;
                return element;
            }
        }
        return null;
    }

    @Override
    public Object top() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.element;
    }

    @Override
    public void clear() {
        Node node = head;
        while (node.next != null) {
            Node nextNode = node.next;
            node.element = null;
            node.next = null;
            node.prev = null;
            node = nextNode;
        }
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder output = new StringBuilder("[");
        Node node = head;
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                output.append(node.element).append("]");
            } else {
                output.append(node.element).append(", ");
            }
            node = node.next;
        }
        return output.toString();
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private Node next;
        private Node lastReturned;
        private int nextInd;

        IteratorImpl() {
            next = QueueImpl.this.head;
            lastReturned = null;
            nextInd = 0;
        }

        @Override
        public boolean hasNext() {
            return nextInd != QueueImpl.this.size;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = next;
            next = next.next;
            nextInd++;
            return lastReturned.element;
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
                head = nextElem;
            } else {
                prevElem.next = nextElem;
                lastReturned.prev = null;
            }

            if (nextElem == null) {
                tail = prevElem;
            } else {
                nextElem.prev = prevElem;
                lastReturned.next = null;
            }
            lastReturned.element = null;
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
