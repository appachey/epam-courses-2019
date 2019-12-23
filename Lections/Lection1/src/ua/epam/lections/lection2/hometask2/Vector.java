package ua.epam.lections.lection2.hometask2;

public class Vector {
    private Object[] refs;
    private int capacity;

    public Vector() {
        this.refs = new Object[10];
        this.capacity = 0;
    }

    public void addRef(Object elem) {
        if (this.capacity == refs.length) {
            Object[] newRefs = new Object[refs.length + 10];
            System.arraycopy(this.refs, 0, newRefs, 0, this.refs.length);
            this.refs = newRefs;
            this.refs[this.capacity] = elem;
            this.capacity++;
        } else {
            this.refs[this.capacity] = elem;
            this.capacity++;
        }
    }

    public void clear() {
        this.refs = new Object[10];
        this.capacity = 0;
    }


}
