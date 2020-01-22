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

    public void printVector() {
        for (int i = 0; i < this.capacity; i++) {
            System.out.println(this.refs[i]);
        }
    }

    public Object getElem (int position) {
        if (position <= this.capacity) {
            return this.refs[position];
        }
        return null;
    }

    public boolean deleteElem(int i) {
        if (i <= this.capacity) {
            if (i == this.capacity) {
                this.refs[i] = null;
                this.capacity--;
            } else {
                for (int j = i; j < this.capacity; j++) {
                    this.refs[j] = this.refs[j + 1];
                }
                this.refs[this.capacity] = null;
                this.capacity--;
            }
        }
        return false;
    }
}
