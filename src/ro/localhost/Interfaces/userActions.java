package ro.localhost.Interfaces;

import ro.localhost.Models.Courier;
import ro.localhost.Models.Shop;

public interface userActions {

    void placeOrder(Shop shop, Courier courier);
    void showOrderHistory();
}
