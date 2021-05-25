package fooddelivery.services;

import fooddelivery.models.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class UsersService {

    private final static ArrayList<User> users = new ArrayList<User>();

    public static void fetchUsers(){

        CsvReader r = CsvReader.getInstance();
        String content = r.readFile("csvFiles/Users.csv");

        String[] lines = content.split("\n");

        for(int i = 1; i < lines.length; ++i){
            String[] fields = lines[i].split(",");

            User user = new User(
                    fields[0], // first name
                    fields[1], // last name
                    fields[2], // birthday
                    fields[3], // phone number
                    fields[4], // email
                    new Cart(),
                    new ArrayList<>(),
                    new Order()
            );

            users.add(user);
        }

        ActionTracer.traceAction("Users fetched successfully");
    }

    public static User findByName(String firstName, String lastName){

        ActionTracer.traceAction("Got request to fetch user " + firstName + " " + lastName);

        for(User user : users) {
            if (user.getFirstName().toLowerCase().equals(firstName.toLowerCase())
                    &&
                    user.getLastName().toLowerCase().equals(lastName.toLowerCase())
            ) {
                return user;
            }
        }
        return null;
    }

    public static User getFirstUser(){
        return users.get(0);
    }

    public static void removeFromCart(User user, Product product){

        HashMap<Product, Integer> products = user.getCart().getProducts();

        if(products.containsKey(product)){
            products.put(product, products.get(product) - 1);

            if(products.get(product) == 0) {
                products.remove(product);
            }

            user.getCart().setProducts(products);
        }
        else{
            System.out.println("The product " + product + " does not exist in the cart");
        }

        ActionTracer.traceAction(user.getFirstName() + " " + user.getLastName() + " removed a " + product.getName() + " from the cart");
    }

    public static void listCart(User user){

        HashMap<Product, Integer> products = user.getCart().getProducts();

        for(Product product : products.keySet()) {
            for (int i = 0; i < products.get(product); ++i) {
                System.out.println(product);
            }
        }

        ActionTracer.traceAction(user.getFirstName() + " " + user.getLastName() + " listed his cart contents");
    }

    public static void placeOrder(User user, Shop shop, Courier courier) {

        user.setCurrentOrder(new Order(new SimpleDateFormat("dd-MM-yyyy k:m:s").format(new Date()), shop.getName(), courier.getFirstName(), courier.getLastName(), new ArrayList<>()));

        HashMap<Product, Integer> products = user.getCart().getProducts();
        for(Product product : products.keySet()) {
            for (int i = 0; i < products.get(product); ++i) {
                OrdersService.addProduct(user.getCurrentOrder(), product);
            }
        }

        user.setCart(new Cart());

        OrdersService.addOrder(user, user.getCurrentOrder());
        user.getOrderHistory().add(new Order(user.getCurrentOrder()));

        ActionTracer.traceAction(user.getFirstName() + " " + user.getLastName() + " placed an order");
    }

    public static void showOrderHistory(User user) {

        System.out.println("Orders history:");
        for(Order order: user.getOrderHistory()) {
            System.out.println(order);
        }

        ActionTracer.traceAction(user.getFirstName() + " " + user.getLastName() + " showed his order history");
    }

    public static Product findInCart(User user, String productName){

        ActionTracer.traceAction("Request from " + user.getFirstName() + " " + user.getLastName() + "'s account to check if " + productName + " is in the cart");

        HashMap<Product, Integer> products = user.getCart().getProducts();
        for(Product product : products.keySet()) {
            if (product.getName().toLowerCase().equals(productName.toLowerCase())) {
                return new Product(product);
            }
        }
        return null;
    }

    public static void addToCart(User user, Product product){

        HashMap<Product, Integer> products = user.getCart().getProducts();

        if(products.containsKey(product)) {
            products.put(product, products.get(product) + 1);
        }
        else {
            products.put(product, 1);
        }

        user.getCart().setProducts(products);

        ActionTracer.traceAction(user.getFirstName() + " " + user.getLastName() + " added a " + product.getName() + " to the cart");
    }
}
