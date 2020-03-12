package ua.nure.bychkov.practice7.entity;

import java.util.ArrayList;
import java.util.List;

public class Version {
    private String type;
    private List<Manufacturer> manufacturers;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Manufacturer> getManufacturers() {
        if (manufacturers == null) {
            return manufacturers = new ArrayList<>();
        }
        return manufacturers;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(type).append("\n");
        for (Manufacturer manufacturer : manufacturers) {
            result.append(manufacturer).append("\n");
        }
        return result.toString();
    }
}
