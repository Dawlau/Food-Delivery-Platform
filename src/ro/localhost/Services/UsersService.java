package ro.localhost.Services;

import ro.localhost.Models.Cart;
import ro.localhost.Models.Order;
import ro.localhost.Models.User;

import java.util.ArrayList;
import java.util.Locale;

public class UsersService {

    private final static ArrayList<User> users = new ArrayList<User>();

    public static void fetchUsers(){

        csvReader r = csvReader.getInstance();
        String content = r.readFile("csvFiles/Users.csv");

        String[] lines = content.split("\n");

        for(int i = 1; i < lines.length; ++i){
            String[] fields = lines[i].split(",");

            User user = new User(
                    fields[0], // first name
                    fields[1], // last name
                    fields[2], // birthday
                    fields[3], // phone number
                    fields[4], // email
                    new Cart(),
                    new ArrayList<>(),
                    new Order()
            );

            users.add(user);
        }

        ActionTracer.traceAction("Users fetched successfully");
    }

    public static User findByName(String firstName, String lastName){

        ActionTracer.traceAction("Got request to fetch user " + firstName + " " + lastName);

        for(User user : users)
            if(user.getFirstName().toLowerCase().equals(firstName.toLowerCase())
                                                &&
                    user.getLastName().toLowerCase().equals(lastName.toLowerCase())
            ){
                return user;
            }
        return null;
    }

    public static User getFirstUser(){
        return users.get(0);
    }
}
