# Food delivery platform

This is a prototype that can be used for further development of a Food delivery platform, written in Java.

## Features

This is a CLI-based application and it integrates most of the functionalities a food delivery platform would need and is also compatible with MYSQL databases.

### Login

When you run the app you are prompted with a login dialog that will require you to enter you username and it will be checked against the database data.
There are 2 types of users: admin and logged user and both have different types of action it can make.

### Admin functionalities

The admin is responsible for the good functioning of the app so he can perform all kinds of CRUD operations on the database.

### Logged user functionalities

The logged user can perform all kinds of operations such as shopping through the menus of all the available shops, add items to their own cart and perform orders. This is the list of the available options:

1) List shops
2) Select shop (by name)
3) Print the current shop
4) Show menu of current shop
5) Add item to cart(by name)
6) List the content of the cart
7) Remove item from cart (by name)
8) Place order
9) Show order history

### Backend specifications

The backend uses a MYSQL database and has an implementation for all the CRUD operations needed for all available models. In case the database is deleted, the backend allows the database to be easily repopulated by recreating the tables and populating them using the CSV files that can be found in the csvFiles directory. Also, backend presents a logging functionality for all the actions made in the application by any user. These can be found in the ActionsTrace.csv file.


## Final notes

I hope you find this prototype useful. If you find any bugs, feel free to submit them to blahoviciandrei1@gmail.com
