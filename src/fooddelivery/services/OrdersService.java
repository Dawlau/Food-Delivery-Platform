package fooddelivery.services;

import fooddelivery.models.Order;
import fooddelivery.dataStructures.Pair;
import fooddelivery.models.Product;
import fooddelivery.models.User;

import java.util.ArrayList;

public class OrdersService {

    private final static ArrayList<Pair<User, Order>> orders = new ArrayList<>();

    public static void addOrder(User user, Order order){
        orders.add(new Pair<>(user, order));
    }

    public static Pair<User, Order> getOrder(){

        ActionTracer.traceAction("Got request to fetch the next order in the queue of orders");

        if(orders.size() == 0) {
            System.out.println("No orders for now");
        }
        else {
            Pair<User, Order> order = orders.get(0);
            orders.remove(0);
            return order;
        }
        return null;
    }

    public static void addProduct(Order order, Product product){

        ArrayList<Product> products = order.getProducts();
        products.add(product);
        order.setProducts(products);
    }
}
