package ua.nure.bychkov.practice7.util;

import ua.nure.bychkov.practice7.entity.Medicine;
import ua.nure.bychkov.practice7.entity.Medicines;

import java.util.Collections;
import java.util.Comparator;

public class Sorter {
    public static final Comparator<Medicine> SORT_MEDICINES_BY_MEDICINE_NAME = Comparator.comparing(Medicine::getName);

    public static final void SortMedicinesByMedicineName(Medicines medicines) {
        Collections.sort(medicines.getMedicines(), SORT_MEDICINES_BY_MEDICINE_NAME);
    }
}
