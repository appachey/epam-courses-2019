package ua.epam.lections.lection2.hometask2;

public class Demo {
    public static void main(String[] args) {
        Vector vect = new Vector();
        vect.addRef(new Vector());
        vect.addRef(new Vector());
        vect.addRef(new Vector());
        vect.addRef(new Vector());
        vect.addRef(new Vector());

        //vect.clear();
        vect.printVector();
        vect.deleteElem(5);
        System.out.println();
        vect.printVector();
    }
}
