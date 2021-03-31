package ro.localhost;

import ro.localhost.Models.Courier;
import ro.localhost.Models.Product;
import ro.localhost.Models.Shop;
import ro.localhost.Models.User;
import ro.localhost.Services.CouriersService;
import ro.localhost.Services.ShopsService;
import ro.localhost.Services.UsersService;

import java.util.Scanner;

public class App {

    private static void menu(){

        System.out.println("Hello, what do you want to do? (select number)");
        System.out.println("0) Exit");
        System.out.println("1) List shops");
        System.out.println("2) Select shop (by name)");
        System.out.println("3) Print the current shop");
        System.out.println("4) Show menu of current shop");
        System.out.println("5) Add item to cart(by name)");
        System.out.println("6) List the content of the cart");
        System.out.println("7) Remove item from cart (by name)");
        System.out.println("8) Place order");
        System.out.println("9) Show order history");
        System.out.println();
    }

    private static void fetchData(){

        ShopsService.fetch_ShopsData();
        UsersService.fetchUsers();
        CouriersService.fetchCouriers();
    }

    private static void placeOrder(Courier courier, User user, Shop shop){

        user.placeOrder(shop, courier);
        courier.takeOrder();
    }

    public static void run(){

        fetchData();

        Scanner scanner = new Scanner(System.in);

        Shop currentShop = null;
        User user = UsersService.getFirstUser();
        Courier courier = CouriersService.getFirstCourier();

        while (true) {
            menu();
            System.out.print("Enter code: ");
            int code = scanner.nextInt();

            if(code == 0){
                System.out.println("Bye!");
                break ;
            }
            else if(code == 1){
                ShopsService.listShops();
            }
            else if(code == 2){
                System.out.print("Enter shop name: ");
                String shopName = scanner.next();
                Shop shop = ShopsService.findByName(shopName);
                currentShop = shop;

                if(shop == null) {
                    System.out.println("Shop does not exist");
                }
                else {
                    System.out.println("Shop has been selected");
                    System.out.println(shop.getName());
                }
            }
            else if(code == 3){
                if(currentShop == null)
                    System.out.println("No shop selected");
                else
                    System.out.println(currentShop.getName());
            }
            else if(code == 4){
                if(currentShop == null)
                    System.out.println("No shop selected");
                else
                    ShopsService.listShopMenu(currentShop);
            }
            else if(code == 5){

                if(currentShop == null) {
                    System.out.println("No shop selected");
                    continue;
                }

                System.out.print("Enter product name: ");
                String productName = scanner.next();

                Product product = ShopsService.findProductByName(currentShop, productName);

                if(product == null){
                    System.out.println("Item does not exist");
                }
                else{ // add to cart
                    user.addToCart(product);
                }
            }
            else if(code == 6){
                user.listCart();
            }
            else if(code == 7){

                if(currentShop == null) {
                    System.out.println("No shop selected");
                    continue;
                }

                System.out.print("Enter product name: ");
                String productName = scanner.next();

                Product product = ShopsService.findProductByName(currentShop, productName);

                if(product == null){
                    System.out.println("Item does not exist");
                }
                else{
                    user.removeFromCart(product);
                }
            }
            else if(code == 8){

                if(currentShop == null) {
                    System.out.println("No shop selected");
                    continue ;
                }
                placeOrder(courier, user, currentShop);
            }
            else if(code == 9){
                user.showOrderHistory();
            }

            System.out.println();
            System.out.println("When you are done with this press ENTER");

            scanner.nextLine();

            String input = scanner.nextLine();
            while(!input.equals("")){ // check if the user pressed enter
                System.out.println("What are you doing? :D");
                input = scanner.nextLine();
            }
        }

    }
}
