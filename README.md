# Food-Delivery-Platform
Made by Andrei Blahovici, Student at University of Bucharest

## Main classes

- Main : defines the entry point of the app
- App : contains the implementation of the CLI

## Packages

### DataStructures

Contains the following classes:

###### - Pair : implements a generic pair class

### Enums

Contains an Enum called ProductType which defines the values a product type can take (see the Product class)

### Interfaces

Contains the following interfaces:

###### - courierActions : defines the actions a courier can make
````
takeOrder : allows the courier to take an order from the list of orders
callUser : allow the courier to call the user when he has arrived with the order
````
###### - userActions : defines the actions a user can make

````
placeOrder : allows the user to place an order once he has added the items he wants to the cart 
showOrderHistory : allows the user to see his own history of orders
````

### Models

Contains the following classes:

###### - Person : abstract class that defines the base for every employer and client
````
getters
setters
constructors
````
###### - User : extends the Person class and models the attributes and methods of a user
````
addToCart : allows a user to add items to its own cart
removeFromCart : allows a user to remove items from its own cart
listCart : lists the current items in the cart
placeOrder: allows the user to place an order (pass to the service of orders and to the order history)
showOrderHistory: list all the orders the user has made
````
###### - Cart : represents the current items the user wants to purchase
````
listCart : lists the contents of the cart
add : add a Product to the cart
remove : remove Product from the cart
````
###### - Order : models an order from the service of orders
````
toString : to print the order
addProduct : add Product to the order
getters
setters
````
###### - Courier : extends de Person class and implements the interface for a courier
````
takeOrder: allows a courier to take an order from the service of orders
callUser : allows the courier to call the user once he has arrived
````
###### - Shop : implements the model of a shop
````
compareTo : used in the shops service and compares two shops by name (used for alphabetical ordering)
listMenu: list the contents of the menu
````
###### - ShopMenu : represents the menu of a shop
````
listMenu : lists the products available
add : add a new Product to the menu
remove : remove a Product from the menu
findProductByName : allows for lookups in the menu (by name)
````
###### - Product : models the interface of a Product from a menu/order 
````
contains the enum ProductType
equals, hashcode: implemented for usage in a hashmap
toString : nice print of objects
````

### Services

Contains the following classes:

###### - CouriersService : service for courier maintenance
````
list of couriers
fetchCouriers : method that gets the couriers available in the system
getFirstCourier : get the first courier in the list of couriers
findCourierByName : allows for search of a courier by name
````
###### - OrdersService : service for order distribution
````
list of type Pair that keeps track of orders and its respectiv user
addOrder : add an order to the list
getOrder : get the first order in the list and remove it
````
###### - ShopsService : service for shop administration
````
TreeSet of shops (ordered by name)
fetch_ShopsData : method that gets the shops available in the system
listShops : prints the list of shops
findByName : search shop by name
findProductByName : search for a specific product in a shop (by name)
listShopMenu : list the menu of the shop
````
###### - UsersService
````
list of users
fetchUsers : gets the users available in the system
getFirstUser : get the first user in the list
````