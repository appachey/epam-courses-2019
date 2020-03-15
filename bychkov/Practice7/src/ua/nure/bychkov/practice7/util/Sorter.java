package ua.nure.bychkov.practice7.util;

import ua.nure.bychkov.practice7.entity.Manufacturer;
import ua.nure.bychkov.practice7.entity.Medicine;
import ua.nure.bychkov.practice7.entity.Medicines;
import ua.nure.bychkov.practice7.entity.Version;

import java.util.Collections;
import java.util.Comparator;

public class Sorter {
    public static final Comparator<Medicine> SORT_MEDICINES_BY_MEDICINE_NAME = Comparator.comparing(Medicine::getName);

    public static final Comparator<Version> SORT_VERSIONS_BY_VERSION_TYPE = Comparator.comparing(Version::getType);

    public static final Comparator<Manufacturer> SORT_MANUFACTURERS_BY_PACKAGE_PRICE = Comparator.comparingDouble(o -> o.getPack().getPrice());

    public static final void sortMedicinesByMedicineName(Medicines medicines) {
        Collections.sort(medicines.getMedicines(), SORT_MEDICINES_BY_MEDICINE_NAME);
    }

    public static final void sortVersionsByVersionType(Medicines medicines) {
        for (Medicine medicine : medicines.getMedicines()) {
            Collections.sort(medicine.getVersions(), SORT_VERSIONS_BY_VERSION_TYPE);
        }
    }

    public static final void sortManufacturersByPackagePrice(Medicines medicines) {
        for (Medicine medicine : medicines.getMedicines()) {
            for (Version version : medicine.getVersions()) {
                Collections.sort(version.getManufacturers(), SORT_MANUFACTURERS_BY_PACKAGE_PRICE);
            }
        }
    }
}
