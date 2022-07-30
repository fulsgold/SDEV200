package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class database {

    public ObservableList<Product> readFromFile() throws FileNotFoundException {
        ObservableList<Product> list = FXCollections.observableArrayList();
        FileReader fr = new FileReader("src/sample/output.txt");
        Scanner input = new Scanner(fr);

        while (input.hasNext()) {
            String line = input.nextLine();
            String[] array = line.split(",");
            list.add(new Product(array[0], Integer.parseInt(array[1]), Integer.parseInt(array[2])));
        }
        return list;
    }

    public ObservableList<History> readHistory() throws IOException {
        ObservableList<History> list = FXCollections.observableArrayList();
        FileReader fr = new FileReader("src/sample/subHistory.txt");
        Scanner in = new Scanner(fr);

        while (in.hasNext()) {
            String line = in.nextLine();
            String[] array = line.split(",");
            list.add(new History(array[0], array[2], Double.parseDouble(array[3])));
        }
        fr.close();
        return list;
    }

    public static void read() throws FileNotFoundException {
        ArrayList<String> list = new ArrayList<>();
        FileReader fr = new FileReader("src/sample/history.txt");
        Scanner in = new Scanner(fr);

        String line = "";
        String[] array = null;
        while (in.hasNext()) {
            line += in.next();
        }
        array = line.split("next");
        String[] array1 = array[0].split(":");
        // System.out.println(array1[0]);
        // System.out.println(array1[1]);
    }
}
