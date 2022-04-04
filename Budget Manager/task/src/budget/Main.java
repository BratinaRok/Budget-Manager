package budget;

import java.io.File;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        Locale locale = new Locale("en_US");
        Locale.setDefault(locale);


        Account rokBudget = new Account();
        rokBudget.startMenu();

        
    }


}












