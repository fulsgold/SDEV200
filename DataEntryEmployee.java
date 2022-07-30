package sample;

import javafx.collections.*;
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

public class DataEntryEmployee extends User {

    TextField Iname;
    TextField IQuantity;
    TextField IPrice;
    TableView<Product> table;
    BorderPane vb;

    public Scene SetInfo() throws FileNotFoundException {
        Label label = new Label("Data Entry");
        label.setTextFill(Color.web("#008080"));
        label.setStyle("-fx-font-weight:bold");
        Font f2 = new Font(24);
        label.setFont(f2);
        Label menu = new Label("Menu");
        Font f = new Font(26);
        menu.setFont(f);
        menu.setStyle("-fx-font-weight:bold");
        menu.setTextFill(Color.web("#008080"));
        HBox labelBox = new HBox();
        labelBox.setAlignment(Pos.CENTER);
        labelBox.getChildren().add(label);
        labelBox.setStyle("-fx-background-color:#C0C0C0");

        vb = new BorderPane();

        VBox vb2 = new VBox(10);
        Rectangle rec = new Rectangle(200, 130);
        rec.setFill(Color.WHITE);
        FileInputStream input = new FileInputStream("src/sample/icons8-add-48.png");
        rec.setFill(new ImagePattern(new Image(input)));
        rec.setStyle("-fx-background-color:#FFFFFF");

        Label add = new Label("Add Product");
        add.setStyle("-fx-font-weight:bold");
        Font f3 = new Font(14);
        add.setFont(f3);

        Rectangle rec1 = new Rectangle(200, 130);
        rec1.setStyle("-fx-background-color:#FFFFFF");
        rec1.setFill(Color.WHITE);
        FileInputStream input1 = new FileInputStream("src/sample/icons8-minus-64.png");
        rec1.setFill(new ImagePattern(new Image(input1)));

        Label remove = new Label("Remove Product");
        remove.setStyle("-fx-font-weight:bold");
        Font f4 = new Font(14);
        remove.setFont(f4);


        Rectangle rec2 = new Rectangle(200, 130);
        rec2.setStyle("-fx-background-color:#FFFFFF");
        rec2.setFill(Color.WHITE);
        FileInputStream input2 = new FileInputStream("src/sample/icons8-database-view-64.png");
        rec2.setFill(new ImagePattern(new Image(input2)));

        Label view = new Label("View Product");
        view.setStyle("-fx-font-weight:bold");
        Font f5 = new Font(14);
        view.setFont(f4);

        HBox hb = new HBox();
        Button Logout = new Button("Return");
        Logout.setOnAction(e -> {
            Main.stage.setScene(Main.scene2);
            Main.name.clear();
            Main.pass.clear();
        });
        hb.setPadding(new Insets(20, 10, 40, 1200));
        Logout.setStyle("-fx-font-weight:bold");
        hb.getChildren().add(Logout);
        hb.setStyle("-fx-background-color:#C0C0C0");

        VBox color = new VBox();
        Label asad = new Label(".");
        asad.setStyle("-fx-background-color:#C0C0C0");
        color.setStyle("-fx-background-color:#C0C0C0");
        color.getChildren().add(asad);
        color.setPadding(new Insets(0, 50, 0, 0));

        vb2.setPadding(new Insets(50, 0, 0, 20));
        vb2.getChildren().addAll(menu, rec, add, rec1, remove, rec2, view);

        vb.setTop(labelBox);
        vb.setLeft(vb2);
        vb.setBottom(hb);
        vb.setRight(color);
        vb2.setStyle("-fx-background-color: #87CEFA");
        Scene scene = new Scene(vb);

        rec.setOnMouseClicked(e -> {
            VBox box = null;
            try {
                box = getAddTable();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            vb.setCenter(box);
        });

        rec1.setOnMouseClicked(event -> {
            VBox box = null;
            try {
                box = getRemoveTable();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            vb.setCenter(box);
        });

        rec2.setOnMouseClicked(event -> {
            VBox box = null;
            try {
                box = getViewTable();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            vb.setCenter(box);
        });
        return scene;

    }

    public VBox getAddTable() throws FileNotFoundException {
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

        Iname = new TextField();
        Iname.setPromptText("Name");
        Iname.setMinWidth(60);

        IQuantity = new TextField();
        IQuantity.setPromptText("Quantity");
        // IRoll.setMinWidth(60);

        IPrice = new TextField();
        IPrice.setPromptText("Price");
        // ICgpa.setMinWidth(60);

        Button add = new Button("Add");
        add.setOnAction(e -> {
            try {
                addItem();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        HBox hb = new HBox(10);
        hb.setPadding(new Insets(10, 10, 10, 10));

        hb.getChildren().addAll(Iname, IQuantity, IPrice, add);

        VBox box = new VBox(10);
        Button back = new Button("back");
        back.setOnAction(event -> {
            vb.setCenter(null);
        });
        box.getChildren().addAll(back, table, hb);
        //box.setPadding(new Insets(50,820,0,100));

        return box;
    }

    public ObservableList<Product> getList() throws FileNotFoundException {
        //ArrayList<Product> arrayList = new ArrayList<>();
        ObservableList<Product> list = FXCollections.observableArrayList();
        database db = new database();
        list = db.readFromFile();
        //list.add(new Product("Lays",10,20));
        //list.add(new Product("KitKat",15,140));

        return list;
    }

    public void addItem() throws IOException {
        Product p = new Product();
        p.setName(Iname.getText());
        p.setQuantity(Integer.parseInt(IQuantity.getText()));
        p.setPrice(Integer.parseInt(IPrice.getText()));
        FileWriter fw = new FileWriter("src/sample/output.txt", true);
        fw.write(p.toString());
        fw.close();
        table.getItems().add(p);
        Iname.clear();
        IQuantity.clear();
        IPrice.clear();
    }

    public void deleteItem() throws IOException {
        ObservableList<Product> selectedProduct, allProduct;
        allProduct = table.getItems();
        selectedProduct = table.getSelectionModel().getSelectedItems();
        System.out.println(allProduct);
        System.out.println(selectedProduct);
        selectedProduct.forEach(allProduct::remove);
        FileWriter fw = new FileWriter("src/sample/output.txt");
        for (Product p : allProduct) {
            fw.write(p.toString());
        }
        fw.close();
    }

    public VBox getRemoveTable() throws FileNotFoundException {
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

        Button remove = new Button("Remove");
        remove.setOnAction(e -> {
            try {
                deleteItem();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        VBox box = new VBox(10);
        Button back = new Button("back");
        back.setOnAction(event -> {
            vb.setCenter(null);
        });
        box.getChildren().addAll(back, table, remove);
        //box.setPadding(new Insets(50,820,0,100));

        return box;
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

}
