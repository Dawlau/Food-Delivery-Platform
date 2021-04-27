package ro.localhost.Services;

import ro.localhost.Models.Order;
import ro.localhost.DataStructures.Pair;
import ro.localhost.Models.User;

import java.util.ArrayList;

public class OrdersService {

    private final static ArrayList<Pair<User, Order>> orders = new ArrayList<>();

    public static void addOrder(User user, Order order){
        orders.add(new Pair<>(user, order));
    }

    public static Pair<User, Order> getOrder(){

        if(orders.size() == 0)
            System.out.println("No orders for now");
        else {
            Pair<User, Order> order = orders.get(0);
            orders.remove(0);
            return order;
        }
        return null;
    }
}
