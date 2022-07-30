package sample;

public class TotalBill {
    private String name;
    private int quantity;
    private double price;
    private double tax;
    private double totalPrice;

    public TotalBill(String name, int quantity, double price, double tax, double totalPrice) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.tax = tax;
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String toString() {
        return name + "," + quantity + "," + price + "," + tax + "," + totalPrice + ":\n";
    }
}
