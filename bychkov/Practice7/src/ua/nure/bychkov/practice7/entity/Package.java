package ua.nure.bychkov.practice7.entity;

/**
 * Implements the Package entity.
 *
 * @author Bychkov Sergey.
 */
public class Package {
    private String type;
    private int count;
    private double price;
    private String currency;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "\t\tPackage: [" +
                "type: " + type +
                ", count: " + count +
                ", price: " + price + " "
                + currency +
                ']';
    }
}
