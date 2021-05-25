package fooddelivery;

import fooddelivery.dataStructures.Pair;
import fooddelivery.enums.ProductType;
import fooddelivery.models.*;
import fooddelivery.repository.RepositoryOrders;
import fooddelivery.repository.RepositoryProducts;
import fooddelivery.repository.RepositoryShops;
import fooddelivery.repository.RepositoryUsers;
import fooddelivery.services.ShopsService;
import fooddelivery.services.UsersService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class AdminApp {

    private static void menu(){

        System.out.println("What do you want to do? (select number)");
        System.out.println("0) Exit");
        System.out.println("1) Create user");
        System.out.println("2) Create shop");
        System.out.println("3) Create product");
        System.out.println("4) Create order");
        System.out.println("5) Show users");
        System.out.println("6) Show shops");
        System.out.println("7) Show products");
        System.out.println("8) Show orders");
        System.out.println("9) Update user");
        System.out.println("10) Update shop");
        System.out.println("11) Update product");
        System.out.println("12) Update order");
        System.out.println("13) Delete user");
        System.out.println("14) Delete shop");
        System.out.println("15) Delete product");
        System.out.println("16) Delete order");
        System.out.println();
    }

    private static User readUser(Scanner scanner){

        scanner.nextLine();
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter birthday: ");
        String birthday = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        return new User(firstName, lastName, birthday, phoneNumber, email, new Cart(), new ArrayList<>(), new Order());
    }

    private static Shop readShop(Scanner scanner){

        scanner.nextLine();

        System.out.println("Enter name: ");
        String shopName = scanner.nextLine();

        System.out.println("Enter phoneNumber: ");
        String phoneNumber = scanner.nextLine();

        System.out.println("Enter address: ");
        String address = scanner.nextLine();

        System.out.println("Enter rating: ");
        double rating = scanner.nextDouble();

        return new Shop(shopName, new ShopMenu(), phoneNumber, address, rating);
    }

    private static void createUser(Scanner scanner){

        RepositoryUsers repositoryUsers = RepositoryUsers.getInstance();
        repositoryUsers.insert(readUser(scanner));
    }

    private static void showUsers(){

        RepositoryUsers repositoryUsers = RepositoryUsers.getInstance();
        ResultSet users = repositoryUsers.selectAll();

        try{

            while(users.next()){
                System.out.println(users.getInt("id") + " " + users.getString("firstName") + " " + users.getString("lastName") + " " + users.getString("birthday") + " " + users.getString("phoneNumber") + " " + users.getString("email"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private static void createShop(Scanner scanner){

        RepositoryShops repositoryShops = RepositoryShops.getInstance();
        repositoryShops.insert(readShop(scanner));
    }

    private static Pair<Order, Integer> readOrder(Scanner scanner){

        scanner.nextLine();
        System.out.print("Enter date: ");
        String date = scanner.nextLine();

        System.out.print("Enter shop name: ");
        String shopName = scanner.nextLine();

        System.out.print("Enter courier first name: ");
        String courierFirstName = scanner.nextLine();

        System.out.print("Enter courier last name: ");
        String courierLastName = scanner.nextLine();

        System.out.print("Enter user id: ");
        int userId = scanner.nextInt();

        return new Pair<>(new Order(date, shopName, courierFirstName, courierLastName, new ArrayList<>()), userId);
    }

    private static void createOrder(Scanner scanner){

        RepositoryOrders repositoryOrders = RepositoryOrders.getInstance();

        Pair <Order, Integer> result = readOrder(scanner);
        repositoryOrders.insert(result.getFirst(), result.getSecond());
    }

    private static void showProducts(){

        RepositoryProducts repositoryProducts = RepositoryProducts.getInstance();

        ResultSet products = repositoryProducts.selectAll();

        try{

            while(products.next()){
                System.out.println(products.getInt("id") + " " + products.getString("name") + " " + products.getString("type"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private static Product readProduct(Scanner scanner){

        System.out.print("Enter price: ");
        double price = scanner.nextDouble();

        scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter type: ");
        String type = scanner.nextLine();

        ProductType pType = (type.toLowerCase().equals("drink") ? ProductType.DRINK : ProductType.FOOD);

        return new Product(price, name, description, pType);
    }

    private static void createProduct(Scanner scanner){

        RepositoryProducts repositoryProducts = RepositoryProducts.getInstance();
        repositoryProducts.insert(readProduct(scanner));
    }

    private static void showShops(){

        RepositoryShops repositoryShops = RepositoryShops.getInstance();
        ResultSet shops = repositoryShops.selectALl();

        try{

            while(shops.next()){
                System.out.println(shops.getInt("id") + " " + shops.getString("name") + " " + shops.getString("phoneNumber") + " " + shops.getString("address") + " " + shops.getDouble("rating"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private static void showOrders(){

        RepositoryOrders repositoryOrders = RepositoryOrders.getInstance();
        ResultSet orders = repositoryOrders.selectAll();

        try{

            while(orders.next()){
                System.out.println(orders.getInt("id") + " " + orders.getString("date") + " " + orders.getString("shopName") + " " + orders.getString("courierFirstName") + " " + orders.getString("courierLastName") + " " + orders.getInt("userId"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private static void updateUser(Scanner scanner){

        RepositoryUsers repositoryUsers = RepositoryUsers.getInstance();

        System.out.print("Enter user id: ");
        int userId = scanner.nextInt();
        User user = readUser(scanner);

        repositoryUsers.update(userId, user);
    }

    private static void updateShop(Scanner scanner){

        RepositoryShops repositoryShops = RepositoryShops.getInstance();

        System.out.print("Enter shop id: ");
        int shopId = scanner.nextInt();
        Shop shop = readShop(scanner);

        repositoryShops.update(shopId, shop);
    }

    private static void updateProduct(Scanner scanner){

        RepositoryProducts repositoryProducts = RepositoryProducts.getInstance();

        System.out.print("Enter product id: ");
        int productId = scanner.nextInt();
        Product product = readProduct(scanner);

        repositoryProducts.update(productId, product);
    }

    private static void updateOrder(Scanner scanner){

        RepositoryOrders repositoryOrders = RepositoryOrders.getInstance();

        System.out.print("Enter order id: ");
        int orderId = scanner.nextInt();
        Pair<Order, Integer> order_uid = readOrder(scanner);

        Order order = order_uid.getFirst();
        Integer userId = order_uid.getSecond();
        order.setId(orderId);

        repositoryOrders.update(order, userId);
    }

    private static void deleteUser(Scanner scanner){

        RepositoryUsers repositoryUsers = RepositoryUsers.getInstance();

        System.out.print("Enter user id: ");
        int userId = scanner.nextInt();

        repositoryUsers.delete(userId);
    }

    private static void deleteShop(Scanner scanner){

        RepositoryShops repositoryShops = RepositoryShops.getInstance();

        System.out.print("Enter shop id: ");
        int shopId = scanner.nextInt();

        repositoryShops.delete(shopId);
    }

    private static void deleteProduct(Scanner scanner){

        RepositoryProducts repositoryProducts = RepositoryProducts.getInstance();

        System.out.print("Enter product id: ");
        int productId = scanner.nextInt();

        repositoryProducts.delete(productId);
    }

    private static void deleteOrder(Scanner scanner){

        RepositoryOrders repositoryOrders = RepositoryOrders.getInstance();

        System.out.print("Enter order id: ");
        int orderId = scanner.nextInt();

        repositoryOrders.delete(orderId);
    }

    public static void run(){

        while(true){

            menu();
            System.out.print("Enter code: ");

            Scanner scanner = new Scanner(System.in);

            int code;

            try{
                code = scanner.nextInt();
            }catch(Exception e){
                System.out.println("Invalid input");
                continue;
            }

            if(code == 0){
                System.out.println("Bye!");
                break ;
            }
            else if(code == 1){
                createUser(scanner);
            }
            else if(code == 2){
                createShop(scanner);
            }
            else if(code == 3){
                createProduct(scanner);
            }
            else if(code == 4){
                createOrder(scanner);
            }
            else if(code == 5){
                showUsers();
            }
            else if(code == 6){
                showShops();
            }
            else if(code == 7){
                showProducts();
            }
            else if(code == 8){
                showOrders();
            }
            else if(code == 9){
                updateUser(scanner);
            }
            else if(code == 10){
                updateShop(scanner);
            }
            else if(code == 11){
                updateProduct(scanner);
            }
            else if(code == 12){
                updateOrder(scanner);
            }
            else if(code == 13){
                deleteUser(scanner);
            }
            else if(code == 14){
                deleteShop(scanner);
            }
            else if(code == 15){
                deleteProduct(scanner);
            }
            else if(code == 16){
                deleteOrder(scanner);
            }
            else{
                System.out.println("Invalid code");
            }

            System.out.println();
            System.out.println("When you are done with this press ENTER");

            scanner.nextLine();

            String input = scanner.nextLine();
            while(!input.equals("")){ // check if the user pressed enter
                System.out.println("What are you doing? :D");
                input = scanner.nextLine();
            }
        }
    }
}
