package ro.localhost.Services;

import ro.localhost.Models.Courier;

import java.util.ArrayList;

public class CouriersService {

    private static ArrayList<Courier> couriers = new ArrayList<>();

    public static void fetchCouriers(){

        Courier courier = new Courier(
                "Cosmin",
                "Iacob",
                "15-05-2000",
                "0751242345",
                "iacobcosmin@gmail.com"
        );

        couriers.add(courier);
    }

    public static Courier getFirstCourier(){
        return couriers.get(0);
    }

    public static Courier findCourierByName(String courierFirstName, String courierLastName){

        for(Courier courier : couriers)
            if(courier.getLastName().equals(courierLastName) && courier.getFirstName().equals(courierFirstName))
                return courier;
            return null;
    }
}
