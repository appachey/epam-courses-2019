package ua.epam.lections.lection1.hometask1;

public class Part6 {
    public static void main(String[] args) {
        int dim = Math.abs(Integer.parseInt(args[0]));
        int[] primes = new int[dim];
        for (int i = 0, j = 0; j < primes.length; i++){
            if (isPrime(i)){
                primes[j] = i;
                j++;
            }
        }
        for (int i = 0; i < primes.length; i++){
            if ((i % 25) == 0){
                System.out.println();
            }
            System.out.printf("%4d ", primes[i]);
        }
    }
    public static boolean isPrime (int x){
        if (x < 2){
            return false;
        }
        for (int i = 2; i <= Math.sqrt(x); i++){
            if ((x % i) == 0){
                return false;
            }
        }
        return true;
    }
}
