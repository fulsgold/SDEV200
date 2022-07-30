package sample;


public class Invoice {


    private String name;
    private int quantity;
    private double price;
    private double totalPrice;
    private double tax;

    public Invoice(double totalPrice, double tax) {
        //this.products = new ArrayList<>();
        this.totalPrice = totalPrice;
        this.tax = tax;
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
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

    @Override
    public String toString() {
        return "TotalBill{" +

                ", totalPrice=" + totalPrice +
                ", tax=" + tax +
                '}';
    }
}
