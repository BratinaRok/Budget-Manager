package budget;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;


public class Account {


    private ArrayList<String> listOfPurchases;
    private ArrayList<Double> itemPrice;
    private ArrayList<Product> products;
    private double balance;
    private Scanner input = new Scanner(System.in);
    private Map<String, ArrayList<Product>> mapProducts = new HashMap<>();


    public Account() {
        this.listOfPurchases = new ArrayList<>();
        this.itemPrice = new ArrayList<>();
        this.balance = 0.00d;


    }


    public void mainMenuPrint() {
        System.out.println(
                "Choose your action:\n" +
                        "1) Add income\n" +
                        "2) Add purchase\n" +
                        "3) Show list of purchases\n" +
                        "4) Balance\n" +
                        "5) Save\n" +
                        "6) Load\n" +
                        "7) Analyze (Sort)\n" +
                        "0) Exit");

    }


    public void startMenu() {

        Scanner input = new Scanner(System.in);

        boolean isOn = true;
        while (isOn) {
            mainMenuPrint();

            int choice = input.nextInt();

            if (choice >= 0 && choice <= 7) {
                System.out.println("");
                switch (choice) {
                    case 0:
                        System.out.println("Bye!");
                        isOn = false;
                        System.exit(1);
                        break;
                    case 1:
                        addIncome();
                        break;
                    case 2:
                        addPurchase();
                        break;
                    case 3:
                        showPurchases();
                        break;
                    case 4:
                        showBalance();
                        break;
                    case 5:
                        saveFile();
                        break;
                    case 6:
                        loadFile();
                        break;
                    case 7:
                        sortMenu();
                        break;

                }
            } else {
                System.out.println("Wrong input = " + choice);
            }
        }
    }

    public void addIncome() {

        double addMoney = 0.00d;
        System.out.println("Enter income:");
        try {
            addMoney = input.nextDouble();
            setBalance(addMoney);
            System.out.println("Income was added!");
        } catch (InputMismatchException e) {
            System.out.println("Wrong input");
            input.next();
        }

        System.out.println("");


    }

    public void addPurchase() {

        boolean isBack = false;
        while (!isBack) {
            System.out.println(
                    "Choose the type of purchase\n" +
                            "1) Food\n" +
                            "2) Clothes\n" +
                            "3) Entertainment\n" +
                            "4) Other\n" +
                            "5) Back");
            int choice = input.nextInt();

            System.out.println("");
            double price = 0.0d;
            if (choice == 5) {
                isBack = true;
                return;
            } else {

                System.out.println("Enter purchase name:");
                String item = input.next();

                item += input.nextLine();


                System.out.println("Enter its price:");
                try {
                    price = input.nextDouble();
                    balanceSubtractFromPurchasePrice(price);

                    if (choice >= 1 && choice <= 4) {
                        Product.Type productType = Product.Type.values()[choice - 1];

                        Product product = new Product(item, price);
                        List<Product> products = mapProducts.computeIfAbsent(productType.toString(), k -> new ArrayList<>());
                        products.add(product);

                        System.out.println("Purchase was added!");
                        System.out.println("");

                    }


                } catch (InputMismatchException e) {
                    System.out.println("Wrong input");
                    isBack = true;
                    System.out.println("");
                    input.next();

                }

            }


        }

    }

    public void showPurchases() {


        double sum = 0.0d;
        DecimalFormat df = new DecimalFormat("0.00");
        if (mapProducts.size() == 0) {
            System.out.println("The purchase list is empty");
            System.out.println("");


        } else {

            int choice = 0;
            while (choice != 6) {
                System.out.println(
                        "Choose the type of purchase\n" +
                                "1) Food\n" +
                                "2) Clothes\n" +
                                "3) Entertainment\n" +
                                "4) Other\n" +
                                "5) All\n" +
                                "6) Back");

                choice = input.nextInt();
                System.out.println("");

                if (choice == 6) {
                    return;
                }
                if (choice == 5) {

                    System.out.println("ALL");
                    int count = 0;
                    while (count <= 3) {
                        Product.Type productType = Product.Type.values()[count];
                        if (mapProducts.get(productType.toString()) == null) {
                            count++;
                        } else {

                            for (int i = 0; i < mapProducts.get(productType.toString()).size(); i++) {
                                System.out.println(mapProducts.get(productType.toString()).get(i).getName() + " $" + df.format(mapProducts.get(productType.toString()).get(i).getPrice()));
                                sum += mapProducts.get(productType.toString()).get(i).getPrice();
                            }
                            count++;
                        }
                    }
                    System.out.println("Total sum : $" + sum);
                    System.out.println("");


                } else {

                    Product.Type productType = Product.Type.values()[choice - 1];

                    System.out.println(productType);
                    if (!mapProducts.containsKey(productType.toString())) {
                        System.out.println("The purchase list is empty");
                        System.out.println("");
                    } else {

                        for (int i = 0; i < mapProducts.get(productType.toString()).size(); i++) {
                            System.out.println(mapProducts.get(productType.toString()).get(i).getName() + " $" + df.format(mapProducts.get(productType.toString()).get(i).getPrice()));
                        }

                        sum = 0;
                        for (int i = 0; i < mapProducts.get(productType.toString()).size(); i++) {

                            sum += mapProducts.get(productType.toString()).get(i).getPrice();

                        }

                        System.out.println("Total sum: $" + df.format(sum));
                        System.out.println("");

                    }
                }

            }

        }
    }

    public void showBalance() {

        System.out.printf("Balance: $%.2f", getBalance());
        System.out.println("");
        System.out.println("");
    }


    public void setBalance(double balanceAmount) {
        this.balance += balanceAmount;
    }

    public void balanceSubtractFromPurchasePrice(double purchasePrice) {
        this.balance -= purchasePrice;
    }

    public double getBalance() {
        if (balance < 0) {
            System.out.println("Balance is negative ");
        }
        return this.balance;
    }


    public void setListOfPurchases(String item) {
        this.listOfPurchases.add(item);
    }

    public ArrayList<String> getListOfPurchases() {
        return listOfPurchases;
    }

    public ArrayList<Double> getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice.add(itemPrice);
    }


    public void saveFile() {

        //File file = new File("C:\\Users\\brati\\OneDrive\\Namizje\\VajaHyperSkills");
        // boolean createNewDirectory = file.mkdir();
//        if (createNewDirectory) {
//            System.out.println("Directory created ");
//        } else {
//            System.out.println("Directory found!");
//        }

        DecimalFormat df = new DecimalFormat("0.00");

        File file1 = new File("C:\\Users\\brati\\OneDrive\\Namizje\\purchases.txt");
        try {
            boolean createFile = file1.createNewFile();

            System.out.println("Purchases were saved!");
        } catch (IOException e) {
            System.out.println("cannot create the file");
        }


        try {
            PrintWriter printWriter = new PrintWriter(new FileOutputStream((file1), false));

            int count = 0;
            while (count <= 3) {
                Product.Type productType = Product.Type.values()[count];

                if (!(mapProducts.get(productType.toString()) == null)) {


                    for (int i = 0; i < mapProducts.get(productType.toString()).size(); i++) {
                        printWriter.println(productType.toString());
                        printWriter.println(mapProducts.get(productType.toString()).get(i).getName());
                        printWriter.println(" $" + df.format(mapProducts.get(productType.toString()).get(i).getPrice()));


                    }

                } else {
                    count++;
                }
                count++;

            }
            printWriter.println("BALANCE");
            printWriter.println(getBalance());
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Error at writing file " + e.getMessage());
        }


    }

    public void loadFile() {
        System.out.println("purchases were loaded");
        System.out.println("");
        File file1 = new File("C:\\Users\\brati\\OneDrive\\Namizje\\purchases.txt");
        int count = 0;

        try {
            Scanner scanner = new Scanner(file1);
            while (scanner.hasNextLine()) {


                String line = scanner.nextLine();

                if (line.equals("FOOD")) {
                    count = 0;
                } else if (line.equals("CLOTHES")) {
                    count = 1;
                } else if (line.equals("ENTERTAINMENT")) {
                    count = 2;
                } else if (line.equals("OTHER")) {
                    count = 3;
                }

                if (line.equals("BALANCE")) {
                    String balance = scanner.nextLine();
                    double endBalance = Double.parseDouble(balance);
                    setBalance(endBalance);

                } else {

                    Product.Type productType = Product.Type.values()[count];

                    String item = scanner.nextLine();
                    String priceWithDollarSign = scanner.nextLine();
                    double price = Double.parseDouble(priceWithDollarSign.replace("$", ""));


                    Product product = new Product(item, price);
                    List<Product> products = mapProducts.computeIfAbsent(productType.toString(), k -> new ArrayList<>());
                    products.add(product);
                }
            }
        } catch (IOException e) {
            System.out.println("error " + e.getMessage());
        }


    }

    public void sortMenu() {


        boolean isOn = true;

        Scanner scanner = new Scanner(System.in);


        while (isOn) {

            System.out.println(
                    "How do you want to sort?\n" +
                            "1) Sort all purchases\n" +
                            "2) Sort by type\n" +
                            "3) Sort certain type\n" +
                            "4) Back");

            int option = scanner.nextInt();
            System.out.println("");

            switch (option) {
                case 1:
                    sortAll();
                    break;

                case 2:
                    sortByType();
                    break;

                case 3:
                    sortByCertainType();
                    break;

                case 4:
                    isOn = false;
                    return;

            }
        }

    }


    public void sortAll() {

        double sum = 0.0d;

        ArrayList<String> productName = new ArrayList<>();
        ArrayList<Double> productPrice = new ArrayList<>();

        DecimalFormat df = new DecimalFormat("0.00");
        if (mapProducts.size() == 0) {
            System.out.println("The purchase list is empty");
            System.out.println("");


        } else {
            System.out.println("ALL");
            int count = 0;
            while (count <= 3) {
                Product.Type productType = Product.Type.values()[count];
                if (mapProducts.get(productType.toString()) == null) {
                    count++;
                } else {

                    for (int i = 0; i < mapProducts.get(productType.toString()).size(); i++) {
                        productName.add(mapProducts.get(productType.toString()).get(i).getName());
                        productPrice.add(Double.parseDouble(df.format(mapProducts.get(productType.toString()).get(i).getPrice())));
                        sum += (Double.parseDouble(df.format(mapProducts.get(productType.toString()).get(i).getPrice())));
                    }


                }
                count++;
            }


            for (int i = 0; i < productPrice.size(); i++) {
                for (int j = i + 1; j < productPrice.size(); j++) {

                    if (productPrice.get(i) < productPrice.get(j)) {
                        double temp = productPrice.get(j);
                        productPrice.set(j, productPrice.get(i));
                        productPrice.set(i, temp);

                        String tempName = productName.get(j);
                        productName.set(j, productName.get(i));
                        productName.set(i, tempName);
                    }

                }
            }

            for (int i = 0; i < productPrice.size(); i++) {
                System.out.println(productName.get(i) + " $" + df.format(productPrice.get(i)));
            }
            System.out.println("Total sum : $" + sum);
            System.out.println("");

        }


    }


    public void sortByType() {

        int count = 0;
        double totalSum = 0.0d;
        Map<Double, String> mapPrices = new TreeMap<Double, String>(Collections.reverseOrder());


        DecimalFormat df = new DecimalFormat("0.00");
        while (count <= 3) {
            Product.Type productType = Product.Type.values()[count];

            if (mapProducts.get(productType.toString()) == null) {
                count++;
            } else {
                double sum = 0.0d;

                for (int i = 0; i < mapProducts.get(productType.toString()).size(); i++) {
                    sum += (Double.parseDouble(df.format(mapProducts.get(productType.toString()).get(i).getPrice())));

                }

                mapPrices.put(sum, productType.toString());
                count++;
                totalSum += sum;
            }

        }

        for (Map.Entry<Double, String> entry : mapPrices.entrySet()) {
            String category = entry.getValue().toLowerCase();
            String categoryUpCase = category.substring(0,1).toUpperCase() + category.substring(1);

            System.out.println( categoryUpCase + " - $" + df.format(entry.getKey()));
        }


        System.out.println("Total sum: " + " $" + totalSum);
        System.out.println("");

    }


    public void sortByCertainType() {

        System.out.println(
                "Choose the type of purchase\n" +
                        "1) Food\n" +
                        "2) Clothes\n" +
                        "3) Entertainment\n" +
                        "4) Other\n");

        int opiton = input.nextInt();

        Product.Type productType = Product.Type.values()[opiton - 1];

        if (mapProducts.get(productType.toString()) == null) {
            System.out.println("The purchase list is empty!");
            System.out.println("");

        } else {

            ArrayList<String> productName = new ArrayList<>();
            ArrayList<Double> productPrice = new ArrayList<>();
            double sum = 0.0d;

            DecimalFormat df = new DecimalFormat("0.00");

                System.out.println(productType.toString());

                for (int i = 0; i < mapProducts.get(productType.toString()).size(); i++) {
                    productName.add(mapProducts.get(productType.toString()).get(i).getName());
                    productPrice.add(Double.parseDouble(df.format(mapProducts.get(productType.toString()).get(i).getPrice())));
                    sum += (Double.parseDouble(df.format(mapProducts.get(productType.toString()).get(i).getPrice())));
                }

            for (int i = 0; i < productPrice.size(); i++) {
                for (int j = i + 1; j < productPrice.size(); j++) {

                    if (productPrice.get(i) < productPrice.get(j)) {
                        double temp = productPrice.get(j);
                        productPrice.set(j, productPrice.get(i));
                        productPrice.set(i, temp);

                        String tempName = productName.get(j);
                        productName.set(j, productName.get(i));
                        productName.set(i, tempName);
                    }

                }
            }

            for (int i = 0; i < productPrice.size(); i++) {
                System.out.println(productName.get(i) + " $" + df.format(productPrice.get(i)));
            }


            System.out.println("Total sum : $" + df.format(sum));
            System.out.println("");
        }


    }
}




