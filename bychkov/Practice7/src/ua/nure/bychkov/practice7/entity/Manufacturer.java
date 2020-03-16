package ua.nure.bychkov.practice7.entity;

/**
 * Implements the Manufacturer entity.
 *
 * @author Bychkov Sergey.
 */
public class Manufacturer {
    private int dosage;
    private String dosgeUnit;
    private Certificate certificate;
    private Package pack;

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public String getDosgeUnit() {
        return dosgeUnit;
    }

    public void setDosgeUnit(String dosgeUnit) {
        this.dosgeUnit = dosgeUnit;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public Package getPack() {
        return pack;
    }

    public void setPack(Package pack) {
        this.pack = pack;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("\tManufacturer:").append("\n");
        result.append(certificate).append("\n")
                .append(pack).append("\n")
                .append("\t\tDosage: " + dosage).append(" " + dosgeUnit);
        return result.toString();
    }
}
