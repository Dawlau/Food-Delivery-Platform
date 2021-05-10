package ro.localhost.Services;

import ro.localhost.Models.Order;
import ro.localhost.Models.User;

public class TraceOrdersService {

    private static TraceOrdersService singleton = null;

    private TraceOrdersService() {}

    public static TraceOrdersService getInstance(){

        if(singleton == null)
            singleton = new TraceOrdersService();
        return singleton;
    }

    public void traceOrder(User user, Order order){

        String text = user.getFirstName() + " " + user.getLastName() + " placed an order," + order.getDate();

        csvReader r = csvReader.getInstance();

        String content = r.readFile("csvFiles/OrdersTrace.csv");
        content += text + "\n";

        csvWriter w = csvWriter.getInstance();
        w.writeToFile(content, "csvFiles/OrdersTrace.csv");
    }
}
