package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    static Scene scene2;
    static Stage stage;
    static TextField name;
    static PasswordField pass;
    Button button;
    Button goLogin;
    Button choseButton;
    Rectangle Arrow;

    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;
        stage.setTitle("Billing System");
        GridPane grid = new GridPane();

        FileInputStream fileInputStream1 = new FileInputStream("src/sample/logoutt.png");
        //FileInputStream f2 = new FileInputStream("src/sample/shop-vector-icon.webp");
        //Image img = new Image(f2);
        Image image2 = new Image(fileInputStream1);
        ImageView imageView = new ImageView(image2);
        //ImageView imageView2 = new ImageView(img); //here
        goLogin = new Button();
        goLogin.setGraphic(imageView);

        FileInputStream input1 = new FileInputStream("src/sample/left.png");
        Image image1 = new Image(input1);
        Arrow = new Rectangle(20, 20);
        Arrow.setFill(new ImagePattern(image1));
        //  Arrow.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(Arrow, 0, 0);


        goLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                database db = new database();
                try {
                    database.read();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Label label = new Label("Billing System App");
                Font f = new Font(20);
                label.setTextFill(Color.web("#008080"));
                label.setStyle("-fx-font-weight: bold");
                label.setAlignment(Pos.CENTER);
                label.setFont(f);
                GridPane grid2 = new GridPane();
                GridPane.setConstraints(label, 1, 0);


                grid2.setAlignment(Pos.TOP_CENTER);
                grid2.setPadding(new Insets(10, 10, 10, 10));
                grid2.setVgap(10);
                grid2.setHgap(10);

                Label welcome = new Label("Welcome");
                label.setTextFill(Color.web("#008080"));
                welcome.setStyle("-fx-font-weight: bold");
                GridPane.setConstraints(welcome, 1, 2);
                // welcome.setStyle("-fx-font-weight:bold");

                Label Phrase = new Label("Select an option below: ");
                Phrase.setStyle("-fx-font-weight: bold");
                // Phrase.setStyle("-fx-font-weight:bold");
                GridPane.setConstraints(Phrase, 1, 4);


                //remove later
                Label n = new Label("User Name");
                Font f1 = new Font(16);
                n.setFont(f1);
                n.setStyle("-fx-font-weight:bold");
                n.setTextFill(Color.web("#800000"));
                GridPane.setConstraints(n, 0, 8);

                /*
                name = new TextField();
                name.setPromptText("Enter your user Name");
                GridPane.setConstraints(name, 1, 8);
                */

                /*
                //omitt as well
                Label password = new Label("Password");
                Font f2 = new Font(16);
                password.setFont(f2);
                password.setTextFill(Color.web("#800000"));
                password.setStyle("-fx-font-weight:bold");
                GridPane.setConstraints(password, 0, 10);
                */

                /*
                //omit
                pass = new PasswordField();
                pass.setPromptText("Enter Password");
                GridPane.setConstraints(pass, 1, 10);
                 */

                button = new Button("Admin");
                button.setOnAction(e -> {
                    Admin admin = new Admin();
                    Scene AdminScene = null;
                    try {
                        AdminScene = admin.SetInfo();
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                    stage.setScene(AdminScene);
                    stage.setMaximized(false);
                });
                Button button2 = new Button("Data entry");
                button2.setOnAction(e -> {
                    DataEntryEmployee DEE = new DataEntryEmployee();
                    Scene DEEscene = null;
                    try {
                        DEEscene = DEE.SetInfo();
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                    stage.setScene(DEEscene);
                    stage.setMaximized(false);
                });
                Button button3 = new Button("Bill Calculation");
                button3.setOnAction(e -> {
                    BillCalculatingEmployee BCE = new BillCalculatingEmployee();
                    Scene BCEScene = null;
                    try {
                        BCEScene = BCE.SetInfo();
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                    stage.setScene(BCEScene);
                    stage.setMaximized(false);
                });


                GridPane.setConstraints(button, 1, 12);
                GridPane.setConstraints(button2, 1, 13);
                GridPane.setConstraints(button3, 1, 14);

                button.setStyle("-fx-font-weight:bold");
                button.setTextFill(Color.web("#000000"));
                scene2 = new Scene(grid2, 600, 600);
                // scene2.getStylesheets().add("Cascade1.css");
                grid2.getChildren().addAll(Arrow, label, welcome, Phrase, button, button2, button3);
                grid2.setStyle("-fx-background-color: #87CEFA");
                stage.setScene(scene2);
            }
        });

        VBox vBox = new VBox(100);
        choseButton = new Button("Select Image ");
        vBox.getChildren().addAll(goLogin);
        vBox.setPadding(new Insets(470, 0, 50, 90));
        Rectangle rec = new Rectangle(270, 220);
        rec.setFill(Color.web("#FFFFFF"));




        choseButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(stage);
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                Image image = new Image(fileInputStream);
                rec.setFill(new ImagePattern(image));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });


        grid.getChildren().addAll(vBox, rec);
        grid.setStyle("-fx-background-color: #87CEFA");
        grid.setPadding(new Insets(0, 100, 330, 175));
        Scene scene = new Scene(grid, 600, 600);
        stage.setResizable(true);
        Arrow.setOnMouseClicked(e -> {
            stage.setScene(scene);
        });
        stage.setScene(scene);
        stage.show();

    }
}