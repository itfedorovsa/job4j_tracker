package ru.job4j.pojo;

public class Shop {

    public static int indexOfNull(Product[] products) {
        int rsl = -1;
        for (int index = 0; index < products.length; index++) {
            Product product = products[index];
            if (products[index] == null) {
               rsl = index;
               break;
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        Product[] products = new Product[5];
        products[0] = new Product("Milk", 10);
        products[1] = new Product("Bread", 4);
        products[2] = new Product("Egg", 19);
        for (int i = 0; i < products.length; i++) {
            Product product = products[i];
            if (product != null) {
                System.out.println(product.getName());
            }
        }
    }
}
