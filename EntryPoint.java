package final_project;

/*
customer cases:
1a. login - needs to be setup with input handling, function in Users class
1b. create - needs to be setup
2. logout - need to be setup with input handling, function in Users class
3. select items - should be mostly same, function in Cart class. Has some of the input handling that someone else wrote
4. place order - there's a checkout function in Cart class. interacts with the bank lol
5. view order - should just need a listOrders and getOrder function
supplier cases
1. process order
2. confirm
3. inventory/stock tracking
*/

import java.util.Scanner;

public class EntryPoint {
    private static Session session;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the OSS. \nAre you a customer or supplier? Type in the number 1 for customer, or 2 for supplier.");
        int custSup = sc.nextInt();
        if (custSup == 1) {
            System.out.println("You have chosen Customer. Welcome.");
            session = Session.startCustomerSession(new Cart());
        } else if (custSup == 2) {
            System.out.println("You have chosen Supplier. Welcome.");
            session = Session.startSupplierSession();
        } else {
            System.out.println("Invalid input. Please try again.");
        }

        // get task
        String task = "login";

        if (session.getSessionType().equals("customer")) {
            handleCustomer(task);
        } else {
            handleSupplier(task);
        }
    }
    public static void handleCustomer(String task) {
        switch (task) {
            case "login":
                session.getUserList().doLogin();
                break;
            case "logout":
                session.getUserList().doLogout();
                break;
            case "shop":
                session.getCart().addItemsToCart();
                break;
            case "checkout":
                try {
                    session.getCart().checkout();
                } catch (UnauthorizedException e) {
                    // prob wanna handle this
                }
                break;
            case "createAccount":
                // ya gotta implement this
                break;
            default:
                // no matching action
        }
    }
    public static void handleSupplier(String task) {
        // handle
    }
}
