package fooddelivery.services;

import fooddelivery.dataStructures.Pair;
import fooddelivery.models.Courier;
import fooddelivery.models.Order;
import fooddelivery.models.User;

import java.util.ArrayList;

public class CouriersService {

    private final static ArrayList<Courier> couriers = new ArrayList<>();

    public static void fetchCouriers(){

        Courier courier = new Courier(
                "Claudiu",
                "Costeniuc",
                "23.01.2001",
                "0751242345",
                "claudiucosteniuc@gmail.com"
        );

        couriers.add(courier);
    }

    public static Courier getFirstCourier(){
        return couriers.get(0);
    }

    public static Courier findCourierByName(String courierFirstName, String courierLastName){
        ActionTracer.traceAction("Got request to fetch courier " + courierFirstName + " " + courierLastName);
        for(Courier courier : couriers) {
            if (courier.getLastName().equals(courierLastName) && courier.getFirstName().equals(courierFirstName)) {
                return courier;
            }
        }

        return null;
    }

    public static void takeOrder(Courier courier) {

        Pair<User, Order> order = OrdersService.getOrder();

        if(order == null) return ;

        ActionTracer.traceAction("Courier " + courier.getFirstName() + " " + courier.getLastName() + " took " + order.getFirst().getFirstName() + " " + order.getFirst().getLastName() + "'s order");

        callUser(courier, order.getFirst());

        ActionTracer.traceAction("Courier " + courier.getFirstName() + " " + courier.getLastName() + " delivered " + order.getFirst().getFirstName() + " " + order.getFirst().getLastName() + "'s order");

        System.out.println("Your order has been delivered");
    }

    public static void callUser(Courier courier, User user) {
        System.out.println("Call " + user.getFirstName() + " " + user.getLastName() + " on " + user.getPhoneNumber());

        ActionTracer.traceAction("Courier " + courier.getFirstName() + " " + courier.getLastName() + " called " + user.getFirstName() + " " + user.getLastName());
    }
}
