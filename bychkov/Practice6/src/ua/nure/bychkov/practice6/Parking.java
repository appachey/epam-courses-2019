package ua.nure.bychkov.practice6;

public class Parking {
    public boolean[] places;

    public Parking(int placesCount) {
        places = new boolean[placesCount];
    }

    public boolean arrive (int k) {
        if (k >= places.length || k < 0) {
            throw new IllegalStateException();
        }
        if (!places[k]) {
            places[k] = true;
            return true;
        } else {
            for (int i = k + 1; (i % places.length) != k; i++) {
                if (!places[i % places.length]) {
                    places[i % places.length] = true;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean depart(int k) {
        if (k >= places.length || k < 0) {
            throw new IllegalStateException();
        }
        if (places[k]) {
            places[k] = false;
            return true;
        }
        return false;
    }
    public void print() {
        StringBuilder output = new StringBuilder();
        for (boolean b : places) {
            if (b) {
                output.append(1);
            } else {
                output.append(0);
            }
        }
        System.out.println(output);
    }
}
