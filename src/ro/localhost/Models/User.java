package ro.localhost.Models;
import ro.localhost.Interfaces.userActions;
import ro.localhost.Services.ActionTracer;
import ro.localhost.Services.OrdersService;

import javax.swing.*;
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

        ActionTracer.traceAction(this.firstName + " " + this.lastName + " added a " + product.getName() + " to the cart");
    }

    public void removeFromCart(Product product){

        cart.remove(product);

        ActionTracer.traceAction(this.firstName + " " + this.lastName + " removed a " + product.getName() + " from the cart");
    }

    public void listCart(){
        cart.listCart();

        ActionTracer.traceAction(this.firstName + " " + this.lastName + " listed his cart contents");
    }

    @Override
    public void placeOrder(Shop shop, Courier courier) {

        currentOrder = new Order(new SimpleDateFormat("dd-MM-yyyy k:m:s").format(new Date()), shop.getName(), courier.getFirstName(), courier.getLastName(), new ArrayList<>());

        HashMap<Product, Integer> products = cart.getProducts();
        for(Product product : products.keySet())
            for(int i = 0; i < products.get(product); ++i)
                currentOrder.addProduct(product);

            cart = new Cart();

        OrdersService.addOrder(this, currentOrder);
        orderHistory.add(new Order(currentOrder));

        ActionTracer.traceAction(this.firstName + " " + this.lastName + " placed an order");
    }

    @Override
    public void showOrderHistory() {

        System.out.println("Istoricul de comenzi:");
        for(Order order: orderHistory)
            System.out.println(order);

        ActionTracer.traceAction(this.firstName + " " + this.lastName + " showed his order history");
    }

    public Product findInCart(String productName){

        ActionTracer.traceAction("Request from " + this.firstName + " " + this.lastName + "'s account to check if " + productName + " is in the cart");

        return cart.checkInCart(productName);
    }
}
