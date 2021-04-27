package ro.localhost.Models;
import ro.localhost.Interfaces.userActions;
import ro.localhost.Services.OrdersService;
import ro.localhost.Services.csvReader;
import ro.localhost.Services.csvWriter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class User extends Person implements userActions{

    private Cart cart;
    private ArrayList<Order> orderHistory;
    private Order currentOrder;

    public User(String firstName, String lastName, String birthday, String phoneNumber, String email, Cart cart, ArrayList<Order> orderHistory, Order currentOrder) {
        super(firstName, lastName, birthday, phoneNumber, email);
        this.setCart(cart);
        this.setOrderHistory(orderHistory);
        this.setCurrentOrder(currentOrder);
    }

    public void setCart(Cart cart) {
        this.cart = new Cart(cart);
    }

    public void setOrderHistory(ArrayList<Order> orderHistory){
        this.orderHistory = new ArrayList<>();
        for(Order order : orderHistory)
            this.orderHistory.add(new Order(order));
    }

    public void setCurrentOrder(Order currentOrder){
        this.currentOrder = new Order(currentOrder);
    }

    public void addToCart(Product product){
        cart.add(product);
    }

    public void removeFromCart(Product product){
        cart.remove(product);
    }

    public void listCart(){
        cart.listCart();
    }


    private void traceOrder(Order order){

        String text = this.firstName + " " + this.lastName + " placed and order," + order.getDate();

        csvReader r = csvReader.getInstance();

        String content = r.readFile("csvFiles/OrdersTrace.csv");
        content += text + "\n";

        csvWriter w = csvWriter.getInstance();
        w.writeToFile(content, "csvFiles/OrdersTrace.csv");
    }

    @Override
    public void placeOrder(Shop shop, Courier courier) {

        currentOrder = new Order(new SimpleDateFormat("dd-MM-yyyy k:m:s").format(new Date()), shop.getName(), courier.getFirstName(), courier.getLastName(), new ArrayList<>());

        HashMap<Product, Integer> products = cart.getProducts();
        for(Product product : products.keySet())
            for(int i = 0; i < products.get(product); ++i)
                currentOrder.addProduct(product);

            cart = new Cart();

            traceOrder(currentOrder);

        OrdersService.addOrder(this, currentOrder);
        orderHistory.add(new Order(currentOrder));
    }

    @Override
    public void showOrderHistory() {

        System.out.println("Istoricul de comenzi:");
        for(Order order: orderHistory)
            System.out.println(order);
    }
}
