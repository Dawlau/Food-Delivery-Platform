package fooddelivery.models;
import fooddelivery.services.ActionTracer;
import fooddelivery.services.OrdersService;
import fooddelivery.dataStructures.Pair;

public class Courier extends Person {

    public Courier(){
        super();
    }

    public Courier(String firstName, String lastName, String birthday, String phoneNumber, String email) {
        super(firstName, lastName, birthday, phoneNumber, email);
    }

    public Courier(Courier courier){
        super(courier.firstName, courier.lastName, courier.birthday, courier.phoneNumber, courier.email);
    }
}
