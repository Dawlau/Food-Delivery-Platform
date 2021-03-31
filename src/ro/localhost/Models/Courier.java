package ro.localhost.Models;
import ro.localhost.DataStructures.Pair;
import ro.localhost.Interfaces.courierActions;
import ro.localhost.Services.OrdersService;

public class Courier extends Person implements courierActions{

    public Courier(){
        super();
    }

    public Courier(String firstName, String lastName, String birthday, String phoneNumber, String email) {
        super(firstName, lastName, birthday, phoneNumber, email);
    }

    public Courier(Courier courier){
        super(courier.firstName, courier.lastName, courier.birthday, courier.phoneNumber, courier.email);
    }

    @Override
    public void takeOrder() {

        Pair<User, Order> order = OrdersService.getOrder();
        callUser(order.getFirst());

        System.out.println("Your order has been delivered");
    }

    @Override
    public void callUser(User user) {
        System.out.println("Call " + user.getFirstName() + " " + user.getLastName() + " on " + user.getPhoneNumber());
    }
}
