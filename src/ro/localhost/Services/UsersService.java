package ro.localhost.Services;

import ro.localhost.Models.Cart;
import ro.localhost.Models.Order;
import ro.localhost.Models.User;

import java.util.ArrayList;

public class UsersService {

    private final static ArrayList<User> users = new ArrayList<User>();

    public static void fetchUsers(){

        User user = new User(
            "Andrei",
                "Blahovici",
                "25-11-2000",
                "0752411523",
                "blahoviciandrei1@gmail.com",
                new Cart(),
                new ArrayList<Order>(),
                new Order()
        );

        users.add(user);
    }

    public static User getFirstUser(){
        return users.get(0);
    }
}
