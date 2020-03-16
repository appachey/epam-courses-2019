package ua.nure.bychkov.practice7.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Root container. Implements the Medicines entity.
 *
 * @author Bychkov Sergey.
 */
public class Medicines {
    private List<Medicine> medicinesList;

    public List<Medicine> getMedicines() {
        if (medicinesList == null) {
            medicinesList = new ArrayList<>();
            return medicinesList;
        }
        return medicinesList;
    }

    @Override
    public String toString() {
        if (medicinesList == null || medicinesList.isEmpty()) {
            return "No medicines found!";
        }
        StringBuilder result = new StringBuilder();
        for (Medicine medicine : medicinesList) {
            result.append(medicine).append("\n");
        }
        return result.toString();
    }
}
