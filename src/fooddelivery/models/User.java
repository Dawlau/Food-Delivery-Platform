package fooddelivery.models;
import fooddelivery.services.ActionTracer;
import fooddelivery.services.OrdersService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class User extends Person {

    private Cart cart;
    private ArrayList<Order> orderHistory;
    private Order currentOrder;

    public User(String firstName, String lastName, String birthday, String phoneNumber, String email, Cart cart, ArrayList<Order> orderHistory, Order currentOrder) {
        super(firstName, lastName, birthday, phoneNumber, email);
        this.setCart(cart);
        this.setOrderHistory(orderHistory);
        this.setCurrentOrder(currentOrder);
    }

    public Cart getCart() {
        return cart;
    }

    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCart(Cart cart) {
        this.cart = new Cart(cart);
    }

    public void setOrderHistory(ArrayList<Order> orderHistory){
        this.orderHistory = new ArrayList<>();
        for(Order order : orderHistory) {
            this.orderHistory.add(new Order(order));
        }
    }

    public void setCurrentOrder(Order currentOrder){
        this.currentOrder = new Order(currentOrder);
    }
}
