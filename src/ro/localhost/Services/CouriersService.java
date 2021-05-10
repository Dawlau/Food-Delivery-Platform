package ro.localhost.Services;

import ro.localhost.Models.Courier;

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
        for(Courier courier : couriers)
            if(courier.getLastName().equals(courierLastName) && courier.getFirstName().equals(courierFirstName))
                return courier;
            return null;
    }
}
