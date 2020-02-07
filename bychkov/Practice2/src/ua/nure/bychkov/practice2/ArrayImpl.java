package ua.nure.bychkov.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {
    private Object[] elements;
    private int size;
    private int nextIndex;

    public ArrayImpl() {
        elements = new Object[5];
        size = 0;
        nextIndex = 0;
    }

    public ArrayImpl(int capacity) {
        elements = new Object[capacity];
        size = 0;
        nextIndex = 0;
    }

    public static void main(String[] args) {
        Array arrList = new ArrayImpl(10);
        System.out.println(arrList);
        System.out.println("***Adding  3 elements***");
        arrList.add("A");
        arrList.add("B");
        arrList.add("C");
        System.out.println(arrList);
        System.out.println("***Seting element onto 2 position***");
        arrList.set(2, "D");
        System.out.println(arrList);
        System.out.println("***Adding one element***");
        arrList.add("E");
        System.out.println(arrList);
        System.out.println("***ArrayList size***");
        System.out.println(arrList.size());
        System.out.println("***Getting 3d element***");
        System.out.println(arrList.get(3));
        System.out.println("***Removing 3d element***");
        arrList.remove(3);
        System.out.println(arrList);
        System.out.println("***ArrayList size***");
        System.out.println(arrList.size());
        System.out.println("***IndexOf D element***");
        System.out.println(arrList.indexOf("D"));
        System.out.println("***IndexOf W element***");
        System.out.println(arrList.indexOf("W"));
        System.out.println("***Iterator***");
        Iterator it = arrList.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("***Iterator remove***");
        it = arrList.iterator();
        System.out.println(it.next());
        it.remove();
        try {
            it.remove();
        } catch (IllegalStateException e) {
            System.out.println("exception");
        }
        System.out.println(arrList);
        System.out.println("***ForEach***");
        for (Object element : arrList) {
            System.out.println(element);
        }
    }

    @Override
    public void add(Object element) {
        if (nextIndex == elements.length) {
            Object[] temp = new Object[elements.length + 5];
            System.arraycopy(elements, 0, temp, 0, elements.length);
            elements = temp;
            elements[nextIndex] = element;
            nextIndex++;
            size++;
        } else {
            elements[nextIndex] = element;
            nextIndex++;
            size++;
        }
    }

    @Override
    public void set(int index, Object element) {
        if (index >= nextIndex || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        elements[index] = element;
    }

    @Override
    public Object get(int index) {
        if (index >= nextIndex || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return elements[index];
    }

    @Override
    public int indexOf(Object element) {
        if (element == null) {
            throw new NoSuchElementException();
        }
        for (int i = 0; i < elements.length; i++) {
            if (element.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        if (index >= nextIndex || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index == 0) {
            Object[] temp = new Object[size];
            System.arraycopy(elements, 1, temp, 0, size - 1);
            elements = temp;
            size--;
            nextIndex--;
        } else {
            Object[] temp = new Object[size];
            System.arraycopy(elements, 0, temp, 0, index);
            System.arraycopy(elements, index + 1, temp, index, size - (index + 1));
            elements = temp;
            size--;
            nextIndex--;
        }
    }

    @Override
    public void clear() {
        elements = null;
        elements = new Object[5];
        size = 0;
        nextIndex = 0;
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
        if (size == 0) {
            return "[]";
        }
        StringBuilder output = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (elements[i] != null) {
                if (i == size - 1) {
                    output.append(elements[i]).append("]");
                } else {
                    output.append(elements[i]).append(", ");
                }
            }
        }
        return output.toString();
    }

    private class IteratorImpl implements Iterator<Object> {
        private int cursor;
        private int lastReturned;

        IteratorImpl() {
            cursor = 0;
            lastReturned = -1;
        }

        @Override
        public boolean hasNext() {
            return cursor != ArrayImpl.this.size;
        }

        @Override
        public Object next() {
            int ind = cursor;
            if (ind >= ArrayImpl.this.size) {
                throw new NoSuchElementException();
            }
            Object[] data = ArrayImpl.this.elements;
            cursor++;
            lastReturned = ind;
            return data[lastReturned];
        }

        @Override
        public void remove() {
            if (lastReturned < 0) {
                throw new IllegalStateException();
            }
            ArrayImpl.this.remove(lastReturned);
            cursor = lastReturned;
            lastReturned = -1;
        }
    }
}
