package budget;

import java.util.Scanner;

public class Product {

    enum Type {
        FOOD,
        CLOTHES,
        ENTERTAINMENT,
        OTHER;

    }

    private String name;
    private double price;
    private Scanner input = new Scanner(System.in);


    public Product(String name, double price) {
        this.name = name;
        this.price = price;


    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }


}
