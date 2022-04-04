package budget;

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
                        "0) Exit");

    }


    public void startMenu() {

        Scanner input = new Scanner(System.in);

        boolean isOn = true;
        while (isOn) {
            mainMenuPrint();

            int choice = input.nextInt();

            if (choice >= 0 && choice <= 4) {
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


                    System.out.println("\nEnter purchase name:");
                    String item = input.nextLine();
                    input.nextLine();

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

        if (mapProducts.size() == 0) {
            System.out.println("The purchase list is empty");
            System.out.println("");


        } else {

            System.out.println(
                    "Choose the type of purchase\n" +
                            "1) Food\n" +
                            "2) Clothes\n" +
                            "3) Entertainment\n" +
                            "4) Other\n" +
                            "5) All\n" +
                            "6) Back\n");

            int choice = input.nextInt();
            if (choice == 5) {
                System.out.println("ALL");
                int count = 0;
                while (count <= 3) {
                    Product.Type productType = Product.Type.values()[count];
                    if (mapProducts.get(productType.toString()) == null) {
                        count++;
                    } else {
                        for (int i = 0; i < mapProducts.get(productType.toString()).size(); i++) {
                            System.out.println(mapProducts.get(productType.toString()).get(i).getName() + " $" + mapProducts.get(productType.toString()).get(i).getPrice());

                        }
                        count++;
                    }
                }
            } else {

                Product.Type productType = Product.Type.values()[choice - 1];

                System.out.println(productType);
                if (!mapProducts.containsKey(productType.toString())) {
                    System.out.println("The purchase list is empty");
                    System.out.println("");
                } else {

                    for (int i = 0; i < mapProducts.get(productType.toString()).size(); i++) {
                        System.out.println(mapProducts.get(productType.toString()).get(i).getName() + " $" + mapProducts.get(productType.toString()).get(i).getPrice());
                    }


                    for (int i = 0; i < mapProducts.get(productType.toString()).size(); i++) {
                        sum += mapProducts.get(productType.toString()).get(i).getPrice();
                    }

                    System.out.println("Total sum: $" + sum);
                    System.out.println("");

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
}

