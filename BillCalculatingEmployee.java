package sample;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class BillCalculatingEmployee extends User {
    BorderPane vb;
    TableView table;
    double price;
    double totalPrice;
    TextField name;
    TextField quantity;
    TextField cName;

    public Scene SetInfo() throws FileNotFoundException {
        Label label = new Label("Bill Calculation");
        label.setTextFill(Color.web("#b5651d"));
        label.setStyle("-fx-font-weight:bold");
        Font f2 = new Font(24);
        label.setFont(f2);

        HBox hb = new HBox();
        hb.setStyle("-fx-background-color:#C0C0C0");
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().add(label);

        Label billLabel = new Label("Bill Calculation");
        billLabel.setStyle("-fx-font-weight:bold");
        billLabel.setFont(new Font(16));
        Rectangle BillCalculation = new Rectangle(200, 130);
        BillCalculation.setFill(new ImagePattern(new Image(new FileInputStream("src/sample/billCalculation.png"))));
        VBox menuBox = new VBox(15);
        menuBox.setPadding(new Insets(50, 20, 0, 20));
        menuBox.setStyle("-fx-background-color: #87CEFA");
        menuBox.getChildren().addAll(BillCalculation, billLabel);
        menuBox.setAlignment(Pos.CENTER);
        HBox hb2 = new HBox();
        Button Logout = new Button("Return");
        Logout.setOnAction(e -> {
            Main.stage.setScene(Main.scene2);
            Main.name.clear();
            Main.pass.clear();
        });
        hb2.setPadding(new Insets(20, 10, 40, 1200));
        Logout.setStyle("-fx-font-weight:bold");
        hb2.getChildren().add(Logout);
        hb2.setStyle("-fx-background-color:#C0C0C0");

        VBox color = new VBox();
        Label a = new Label(".");
        color.getChildren().add(a);
        color.setPadding(new Insets(50, 0, 0, 200));
        color.setStyle("-fx-background-color: #87CEFA");
        a.setTextFill(Color.web("#87CEFA"));

        BillCalculation.setOnMouseClicked(e -> {
            HBox box = display();
            vb.setCenter(box);
        });
        vb = new BorderPane();
        vb.setRight(color);
        vb.setBottom(hb2);
        vb.setLeft(menuBox);
        vb.setTop(hb);

        Scene scene = new Scene(vb);
        //  scene.getStylesheets().add("Cascade1.css");

        return scene;

    }

    public HBox display() {
        VBox vb = new VBox(10);

        Label Pname = new Label("Enter name:");
        Pname.setStyle("-fx-font-weight:bold");
        Pname.setFont(new Font(12));
        name = new TextField();
        name.setPromptText("Enter name of product");

        Label Cname = new Label("Enter name:");
        Cname.setStyle("-fx-font-weight:bold");
        Cname.setFont(new Font(12));
        cName = new TextField();
        cName.setPromptText("Enter name of product");

        Label Qname = new Label("Enter Quantity:");
        Qname.setStyle("-fx-font-weight:bold");
        Qname.setFont(new Font(12));
        quantity = new TextField();
        quantity.setPromptText("Enter name of Quantity");
        Button search = new Button("Search");
        search.setStyle("-fx-font-weight:bold");
        Label change = new Label("TotalBill: " + 0.0);
        search.setOnAction(event -> {
            try {
                if (isAvailable()) {
                    double tax = price * (readTax() / 100);
                    price += tax;
                    TotalBill tb = new TotalBill(name.getText(), Integer.parseInt(quantity.getText()), price, readTax(), Integer.parseInt(quantity.getText()) * price);
                    totalPrice += tb.getTotalPrice();
                    table.getItems().add(tb);
                    change.setText("Total Bill: " + totalPrice);
                    name.clear();
                    quantity.clear();
                    FileWriter fw = new FileWriter("src/sample/history.txt", true);
                    fw.write(tb.toString());
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        vb.setPadding(new Insets(100, 0, 0, 20));
        vb.getChildren().addAll(Cname, cName, Pname, name, Qname, quantity, search);
        HBox hb = new HBox(80);

        Button clear = new Button("Clear All");
        clear.setOnAction(event -> {
            History history = new History(getDate(), cName.getText(), totalPrice);
            FileWriter fw = null;
            try {
                fw = new FileWriter("src/sample/subHistory.txt", true);
                fw.write(history.toString());
                fw.close();
                totalPrice = 0;
                cName.clear();
                FileWriter fw1 = new FileWriter("src/sample/history.txt", true);
                fw1.write("next\n");
                fw1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            table.getItems().clear();
            change.setText("Total Bill: " + 0.0);
        });
        VBox vbTable = new VBox(20);
        vbTable.setPadding(new Insets(40, 0, 0, 0));
        Label TotalBill = new Label("Total Bill: " + 0.0);
        TotalBill.textProperty().bind(change.textProperty());
        TotalBill.setStyle("-fx-font-weight:bold");
        TotalBill.setFont(new Font(16));

        TableView Table = getTable();
        vbTable.getChildren().addAll(Table, TotalBill, clear);

        hb.getChildren().addAll(vb, vbTable);
        return hb;

    }

    public TableView<TotalBill> getTable() {
        table = new TableView();
        // String Icolumn,Iprice,Iquantity,Itax,ItotalPrice;

        TableColumn<TotalBill, String> name = new TableColumn<>("Name");
        name.setMinWidth(100);
        name.setCellValueFactory(new PropertyValueFactory("name"));

        TableColumn<TotalBill, Integer> quantity = new TableColumn<>("Quantity");
        quantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        quantity.setMinWidth(100);

        TableColumn<TotalBill, Double> Price = new TableColumn<>("Price");
        Price.setMinWidth(100);
        Price.setCellValueFactory(new PropertyValueFactory("price"));

        TableColumn<TotalBill, Double> Tax = new TableColumn<>("Tax");
        Tax.setMinWidth(100);
        Tax.setCellValueFactory(new PropertyValueFactory("tax"));

        TableColumn<TotalBill, Double> totalPrice = new TableColumn<>("TotalPrice");
        totalPrice.setMinWidth(100);
        totalPrice.setCellValueFactory(new PropertyValueFactory("totalPrice"));
        table.getColumns().addAll(name, quantity, Price, Tax, totalPrice);


        return table;
    }

    public boolean isAvailable() throws IOException {
        database db = new database();
        ObservableList<Product> list = db.readFromFile();

        for (Product p : list) {
            if (p.getName().equalsIgnoreCase(name.getText()) && p.getQuantity() >= Integer.parseInt(quantity.getText())) {
                price = p.getPrice();
                int remQuantity = p.getQuantity() - Integer.parseInt(quantity.getText());
                p.setQuantity(remQuantity);
                FileWriter fw = new FileWriter("src/sample/output.txt");
                for (Product p1 : list) {
                    fw.write(p1.toString());
                }
                fw.close();
                return true;
            }
        }
        return false;
    }

    public double readTax() throws FileNotFoundException {
        FileReader fr = new FileReader("src/sample/tax.txt");
        Scanner in = new Scanner(fr);
        double tax = 0;
        while (in.hasNext()) {
            tax = Double.parseDouble(in.nextLine());
        }
        return tax;
    }

    public String getDate() {
        String pattern = "dd-MM-yyyy";
        String dateInString = new SimpleDateFormat(pattern).format(new Date());
        return dateInString;
    }
}