package ua.nure.bychkov.practice6.part3;

public class Part3 {
    public static void main(String[] args) {
        Parking parking = new Parking(4);
        parking.print();
        System.out.println("~~~~~~~~~");
        parking.arrive(3);
        parking.arrive(3);
        parking.arrive(1);
        parking.arrive(3);
        parking.arrive(3);
        parking.arrive(3);
        System.out.println(parking.arrive(3));
        parking.depart(2);
        parking.print();
    }
}
