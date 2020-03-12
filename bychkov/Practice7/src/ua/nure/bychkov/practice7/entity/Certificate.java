package ua.nure.bychkov.practice7.entity;

public class Certificate {
    private int number;
    private String expDate;
    private String organization;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "number=" + number +
                ", expDate='" + expDate + '\'' +
                ", organization='" + organization + '\'' +
                '}';
    }
}
