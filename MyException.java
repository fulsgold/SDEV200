public class MyException extends Exception {

    public MyException(String message) {
        super(message);
    }

    public MyException() {
    }


    public void TaxException(String strTax) throws MyException {
        if (!isDigit(strTax)) {
            throw new MyException("Exception occured!");
        }
    }

    public void LoginException(String strName, String strPass) throws MyException {
        if (strName.isEmpty() || strPass.isEmpty()) {
            throw new MyException();
        }
    }

    public void AddProductException(String name, String quantity, String price) throws MyException {
        if (name.isEmpty() || quantity.isEmpty() || price.isEmpty()) {
            throw new MyException("Exception occured! Fields are empty");
        } else if (!isCharacter(name) || !isDigit(quantity) || !isDigit(price)) {
            throw new MyException("Exception occured");
        }
    }

    public void BillCalculationException(String cName, String pName, String quantity) throws MyException {
        if (cName.isEmpty() || pName.isEmpty() || quantity.isEmpty()) {
            throw new MyException("Exception occured! Fields are empty");
        } else if (!isCharacter(cName) || !isCharacter(pName) || !isDigit(quantity)) {
            throw new MyException("Exception occured!");
        }
    }

    public boolean isDigit(String str) {
        try {
            int tax = Integer.parseInt(str);
            return tax >= 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCharacter(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}