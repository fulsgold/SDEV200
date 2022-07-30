package sample;

public class FactoryClass {

    private static FactoryClass instance;

    public static synchronized FactoryClass getInstance() {
        if (instance == null) {
            return new FactoryClass();
        }
        return instance;
    }

    public User getFactoryMethod(String name) {
        if (name.equalsIgnoreCase("Admin")) {
            return new Admin();
        } else if (name.equalsIgnoreCase("BCE")) {
            return new BillCalculatingEmployee();
        } else if (name.equalsIgnoreCase("DEE")) {
            return new DataEntryEmployee();
        }
        return null;
    }
}
