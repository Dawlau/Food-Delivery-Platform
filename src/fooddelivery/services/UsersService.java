package fooddelivery.services;

import fooddelivery.enums.ProductType;
import fooddelivery.models.*;
import fooddelivery.repository.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class UsersService {

    private static ArrayList<User> users = new ArrayList<User>();

    public static void fetchUsers_CSV(){

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

        RepositoryDatabaseManager repositoryDatabaseManager = RepositoryDatabaseManager.getInstance();
        repositoryDatabaseManager.addUsers(users);

        ActionTracer.traceAction("Users fetched successfully");
    }

    public static void fetchUsers(){

        RepositoryDatabaseManager repositoryDatabaseManager = RepositoryDatabaseManager.getInstance();
        users = repositoryDatabaseManager.loadUsers();
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

        RepositoryOrders repositoryOrders = RepositoryOrders.getInstance();
        repositoryOrders.insert(user.getCurrentOrder(), user.getId());

        ActionTracer.traceAction(user.getFirstName() + " " + user.getLastName() + " placed an order");
    }

    public static void showOrderHistory(User user) {

        System.out.println("Orders history:");

        RepositoryOrders repositoryOrders = RepositoryOrders.getInstance();

        ResultSet allOrders = repositoryOrders.selectByUserId(user.getId());

        try{

            ArrayList<Order> orders = new ArrayList<>();
            while(allOrders.next()){

                RepositoryOrders_Products repositoryOrders_products = RepositoryOrders_Products.getInstance();
                ResultSet prods = repositoryOrders_products.selectByOrderId(allOrders.getInt("id"));

                ArrayList<Product> products = new ArrayList<>();

                while(prods.next()){

                    RepositoryProducts repositoryProducts = RepositoryProducts.getInstance();
                    ResultSet p = repositoryProducts.selectById(prods.getInt("productId"));
                    p.next();

                    Product product = new Product(
                            p.getDouble("price"),
                            p.getString("name"),
                            p.getString("description"),
                            (p.getString("type").equals("DRINK") ? ProductType.DRINK : ProductType.FOOD)
                    );

                    products.add(product);
                }

                Order order = new Order(
                        allOrders.getString("date"),
                        allOrders.getString("shopName"),
                        allOrders.getString("courierFirstName"),
                        allOrders.getString("courierLastName"),
                        products
                );

                orders.add(order);
            }

            for(Order order : orders){
                System.out.println(order);
            }

        }catch(SQLException e){
            e.printStackTrace();
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
