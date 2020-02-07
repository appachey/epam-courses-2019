package ua.nure.bychkov.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {
    private Node head;
    private Node tail;
    private int size;

    public StackImpl() {
        head = null;
        tail = null;
        size = 0;
    }

    public static void main(String[] args) {
        Stack stack = new StackImpl();
        System.out.println("***Pushing elements***");
        stack.push("a");
        stack.push("b");
        stack.push("c");
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack);
        System.out.println(stack.size());
        System.out.println("***Pushing elements***");
        stack.push("a");
        stack.push("b");
        stack.push("c");
        System.out.println(stack);
        System.out.println(stack.size());
        System.out.println("***Return first element of stack***");
        System.out.println(stack.top());
        System.out.println("***Clear Stack***");
        stack.clear();
        System.out.println(stack);
        System.out.println(stack.size());
        System.out.println("***Iterator***");
        stack.push("a");
        stack.push("b");
        stack.push("c");
        Iterator it = stack.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("***Iterator remove***");
        it = stack.iterator();
        System.out.println(it.next());
        it.remove();
        try {
            it.remove();
        } catch (IllegalStateException e) {
            System.out.println("exception");
        }
        System.out.println(stack);
        System.out.println("***ForEach***");
        for (Object elem : stack) {
            System.out.println(elem);
        }
    }

    private static class Node {
        private Object value;
        private Node next;
        private Node previous;

        Node() {
            value = null;
            next = null;
            previous = null;
        }
    }

    @Override
    public void push(Object element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        Node currentTop = head;
        Node elem = new Node();
        elem.next = currentTop;
        elem.value = element;
        head = elem;
        if (currentTop == null) {
            tail = elem;
        } else {
            currentTop.previous = elem;
        }
        size++;
    }

    @Override
    public Object pop() {
        if (head != null) {
            Object element = head.value;
            if (head.next == null) {
                tail = null;
                head = null;
                size--;
                return element;
            } else {
                head = head.next;
                head.previous = null;
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
        return head.value;
    }

    @Override
    public void clear() {
        Node node = head;
        while (node.next != null) {
            Node nextNode = node.next;
            node.value = null;
            node.next = null;
            node.previous = null;
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
        Node node = tail;
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                output.append(node.value).append("]");
            } else {
                output.append(node.value).append(", ");
            }
            node = node.previous;
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
            next = StackImpl.this.head;
            lastReturned = null;
            nextInd = 0;
        }

        @Override
        public boolean hasNext() {
            return nextInd != StackImpl.this.size;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = next;
            next = next.next;
            nextInd++;
            return lastReturned.value;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            Node lastNext = lastReturned.next;

            Node prevElem = lastReturned.previous;
            Node nextElem = lastReturned.next;
            if (prevElem == null) {
                head = nextElem;
            } else {
                prevElem.next = nextElem;
                lastReturned.previous = null;
            }

            if (nextElem == null) {
                tail = prevElem;
            } else {
                nextElem.previous = prevElem;
                lastReturned.next = null;
            }
            lastReturned.value = null;
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
