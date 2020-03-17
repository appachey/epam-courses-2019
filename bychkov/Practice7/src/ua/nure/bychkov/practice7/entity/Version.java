package ua.nure.bychkov.practice7.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Version entity.
 *
 * @author Bychkov Sergey.
 */
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
            manufacturers = new ArrayList<>();
            return manufacturers;
        }
        return manufacturers;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Version:").append("\n");
        result.append("\tType: " + type).append("\n");
        for (Manufacturer manufacturer : manufacturers) {
            result.append(manufacturer).append("\n");
        }
        return result.toString();
    }
}
