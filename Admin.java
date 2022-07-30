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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class    Admin extends User {

    private static Admin instance;
    BorderPane vb;
    TextField taxField;
    Button setButton;
    TableView table;

    public static synchronized Admin getInstance() {
        if (instance == null) {
            return new Admin();
        }
        return instance;
    }

    public Scene SetInfo() throws FileNotFoundException {
        Label label = new Label("Admin");
        label.setTextFill(Color.web("#008080"));
        label.setStyle("-fx-font-weight:bold");
        label.setFont(new Font(24));
        HBox hb = new HBox();
        hb.getChildren().add(label);
        hb.setAlignment(Pos.CENTER);
        hb.setStyle("-fx-background-color:#C0C0C0");

        vb = new BorderPane();

        Label menu = new Label("Menu");
        menu.setFont(new Font(24));
        menu.setStyle("-fx-font-weight:bold");
        menu.setTextFill(Color.web("#006080"));
        HBox menuPadding = new HBox();
        menuPadding.setAlignment(Pos.CENTER);
        menuPadding.getChildren().add(menu);

        VBox vb2 = new VBox(20);

        Rectangle tax = new Rectangle(200, 130);
        tax.setFill(new ImagePattern(new Image(new FileInputStream("src/sample/tax.png"))));
        Label setTax = new Label("Set Tax");
        setTax.setFont(new Font(16));
        setTax.setStyle("-fx-font-weight: bold");
        setTax.setTextFill(Color.web("#006080"));

        Rectangle history = new Rectangle(130, 130);
        history.setFill(new ImagePattern(new Image(new FileInputStream("src/sample/history.png"))));
        Label viewHistory = new Label("Billing History");
        viewHistory.setFont(new Font(16));
        viewHistory.setStyle("-fx-font-weight: bold");
        viewHistory.setTextFill(Color.web("#006080"));

        Rectangle view = new Rectangle(130, 130);
        view.setFill(new ImagePattern(new Image(new FileInputStream("src/sample/view.png"))));
        Label viewLabel = new Label("View Products");
        viewLabel.setTextFill(Color.web("#006080"));
        viewLabel.setStyle("-fx-font-weight: bold");
        viewLabel.setFont(new Font(16));

        vb2.setPadding(new Insets(50, 20, 0, 20));
        vb2.setStyle("-fx-background-color: #87CEFA");
        vb2.getChildren().addAll(menuPadding, tax, setTax, history, viewHistory, view, viewLabel);

        Button logout = new Button("Return");
        logout.setOnAction(event -> {
            Main.stage.setScene(Main.scene2);
            Main.pass.clear();
            Main.name.clear();
        });
        logout.setStyle("-fx-font-weight: bold");
        HBox logoutBox = new HBox();
        logoutBox.setPadding(new Insets(40, 10, 20, 1300));
        logoutBox.setStyle("-fx-background-color:#C0C0C0");
        logoutBox.getChildren().add(logout);

        VBox color = new VBox();
        Label a = new Label(".");
        color.getChildren().add(a);
        color.setPadding(new Insets(50, 0, 0, 200));
        color.setStyle("-fx-background-color: #87CEFA");
        a.setTextFill(Color.web("#87CEFA"));

        tax.setOnMouseClicked(event -> {
            try {
                HBox hBox = setTax();
                vb.setCenter(hBox);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        view.setOnMouseClicked(event -> {
            try {
                VBox box = getViewTable();
                vb.setCenter(box);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        history.setOnMouseClicked(event -> {
            try {
                HBox boxHistory = History();
                vb.setCenter(boxHistory);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        vb.setTop(hb);
        vb.setLeft(vb2);
        vb.setBottom(logoutBox);
        vb.setRight(color);
        Scene scene = new Scene(vb);
        return scene;

    }

    public HBox setTax() throws FileNotFoundException {

        Label title = new Label("Enter the tax amount");
        title.setStyle("-fx-font-weight:bold");
        title.setTextFill(Color.BLACK);
        title.setFont(new Font(20));
        taxField = new TextField();
        taxField.setPromptText("Enter tax here");

        setButton = new Button("Set");
        setButton.setStyle("-fx-font-weight:bold");
        setButton.setOnAction(event -> {
            try {
                FileWriter fw = new FileWriter("src/sample/tax.txt");
                fw.write(taxField.getText());
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            taxField.clear();
        });
        VBox vb = new VBox(10);
        vb.getChildren().addAll(title, taxField, setButton);
        vb.setPadding(new Insets(280, 200, 280, 200));

        HBox hb = new HBox(10);
        Button back = new Button("Back");
        back.setOnAction(event -> {
            this.vb.setCenter(null);
        });
        hb.getChildren().addAll(back, vb);
        return hb;
    }

    public HBox History() throws IOException {

        Label title = new Label("History");

        title.setStyle("-fx-font-weight:bold");
        title.setTextFill(Color.BLACK);
        title.setFont(new Font(30));

        TableView table = new TableView();
        TableColumn<History, String> date = new TableColumn<>("Date");
        date.setMinWidth(100);
        date.setCellValueFactory(new PropertyValueFactory("date"));

        TableColumn<History, Integer> Id = new TableColumn<>("Id");
        Id.setMinWidth(100);
        Id.setCellValueFactory(new PropertyValueFactory("id"));

        TableColumn<History, String> cName = new TableColumn<>("Cname");
        cName.setMinWidth(100);
        cName.setCellValueFactory(new PropertyValueFactory("CName"));

        TableColumn<History, Double> TotalBill = new TableColumn<>("TotalBill");
        TotalBill.setMinWidth(100);
        TotalBill.setCellValueFactory(new PropertyValueFactory("totalBill"));


        table.getColumns().addAll(date, Id, cName, TotalBill);

        database db = new database();
        ObservableList<History> list = db.readHistory();
        table.setItems(list);

        Button clear = new Button("Clear History");
        clear.setOnAction(e -> {

            table.getItems().clear();
            try {
                FileWriter fw = new FileWriter("src/sample/history.txt");
                fw.write("");
                fw.close();

                FileWriter fw1 = new FileWriter("src/sample/subHistory.txt");
                fw1.write("");
                fw1.close();
            } catch (IOException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        VBox vb = new VBox(80);
        vb.setPadding(new Insets(40, 180, 0, 180));
        vb.getChildren().addAll(title, table, clear);
        HBox hb = new HBox();
        Button back = new Button("Back");
        back.setOnMouseClicked(e -> this.vb.setCenter(null));
        hb.getChildren().addAll(back, vb);


        return hb;


    }

    public VBox getViewTable() throws FileNotFoundException {
        table = new TableView<>();

        TableColumn<Product, String> name = new TableColumn<>("Name");
        name.setMinWidth(200);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Integer> quantity = new TableColumn<>("Quantity");
        quantity.setMinWidth(150);
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Product, Double> price = new TableColumn<>("Price");
        price.setMinWidth(150);
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.setItems(getList());
        table.getColumns().addAll(name, quantity, price);

        VBox box = new VBox(10);
        Button back = new Button("back");
        back.setOnAction(event -> {
            vb.setCenter(null);
        });
        box.getChildren().addAll(back, table);
        //box.setPadding(new Insets(50,820,0,100));

        return box;
    }

    public ObservableList<Product> getList() throws FileNotFoundException {
        database db = new database();
        ObservableList<Product> list = db.readFromFile();
        return list;
    }
}