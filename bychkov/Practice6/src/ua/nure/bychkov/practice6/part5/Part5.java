package ua.nure.bychkov.practice6.part5;

public class Part5 {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        Integer[] arr = {3, 1, 2, 0, 5};
        tree.add(arr);
        System.out.println(tree.add(6));
        System.out.println(tree.add(6));
        tree.print();
        System.out.println("~~~~~~~~~~~~~");
        System.out.println(tree.remove(5));
        System.out.println(tree.remove(5));
        tree.print();
    }
}
