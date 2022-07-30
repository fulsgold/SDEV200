package sample;

public class History {
    private String date;
    private int id;
    private String CName;
    private double totalBill;
    private static int count = 1;

    public History(String date, String CName, double totalBill) {
        this.date = date;
        this.id = count++;
        this.CName = CName;
        this.totalBill = totalBill;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCName(String cName) {
        this.CName = cName;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }


    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public String getCName() {
        return CName;
    }

    public double getTotalBill() {
        return totalBill;
    }


    public String toString() {
        return date + "," + id + "," + CName + "," + totalBill + "\n";
    }
}