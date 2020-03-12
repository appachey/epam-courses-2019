package ua.nure.bychkov.practice7.entity;

import java.util.ArrayList;
import java.util.List;

public class Medicines {
    private List<Medicine> medicines;

    public List<Medicine> getMedicines() {
        if (medicines == null) {
            return medicines = new ArrayList<>();
        }
        return medicines;
    }

    @Override
    public String toString() {
        if (medicines == null || medicines.isEmpty()) {
            return "No medicines found!";
        }
        StringBuilder result = new StringBuilder();
        for (Medicine medicine : medicines) {
            result.append(medicine).append("\n");
        }
        return result.toString();
    }
}
