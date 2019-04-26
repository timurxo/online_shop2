package final_project;

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
