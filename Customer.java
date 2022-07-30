package sample;

import javafx.scene.control.Button;

public class Customer {

    private int id;
    private String name;
    private String date;
    private double totalBill;
    private Button button;

    public Customer(int id, String name, String date, double totalBill) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.totalBill = totalBill;
    }

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public Button getButton() {
        return button;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }
}
