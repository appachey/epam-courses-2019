package ua.nure.bychkov.practice6;

public class Part3 {
    public static void main(String[] args) {
        Parking parking = new Parking(6);
        parking.print();
        System.out.println("~~~~~~~~~");
        parking.arrive(3);
        parking.arrive(3);
        parking.arrive(5);
        parking.arrive(3);
        parking.arrive(3);
        parking.arrive(3);
        System.out.println(parking.arrive(3));
        parking.depart(5);
        parking.print();
    }
}
