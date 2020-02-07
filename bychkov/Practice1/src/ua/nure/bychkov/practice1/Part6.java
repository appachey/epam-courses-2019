package ua.nure.bychkov.practice1;

public class Part6 {
    public static void main(String[] args) {
        int dim = Math.abs(Integer.parseInt(args[0]));
        int[] primes = new int[dim];
        int primesCounter = 0;
        for (int i = 0; primesCounter < primes.length; i++){
            if (isPrime(i)){
                primes[primesCounter++] = i;
            }
        }
        for (int i = 0; i < primes.length; i++){
            System.out.print(primes[i] + " ");
        }
        System.out.println();
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
