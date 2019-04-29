/*
customer cases:
1a. login - done
1b. create - done
2. logout - done
3. select items - done
4. place order - done

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
        boolean begin = true;
        while(begin) {
            System.out.println("Welcome to the OSS. \nAre you a customer or supplier? Type in the number 1 for customer, or 2 for supplier.");
            int custSup = sc.nextInt();
            if (custSup == 1) {
                System.out.println("You have chosen Customer. Welcome.");
                session = Session.startCustomerSession(new Cart());
                begin = false;
            } else if (custSup == 2) {
                System.out.println("You have chosen Supplier. Welcome.");
                session = Session.startSupplierSession();
                begin = false;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }

        String task = "";
        boolean taskChoice = true;
        while (taskChoice) {
            System.out.println("Which task would you like to perform? \n1. login\n2. logout\n3. shop\n4. checkout");
            int pickTask = sc.nextInt();
            switch (pickTask) {
                case 1:
                    task = "login";
                    taskChoice = false;
                    break;
                case 2:
                    task = "logout";
                    taskChoice = false;
                    break;
                case 3:
                    task = "shop";
                    taskChoice = false;
                    break;
                case 4:
                    task = "checkout";
                    taskChoice = false;
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
            }
        }
        //String task = "login";
        System.out.println("Outside while loop with task.");

        if (session.getSessionType().equals("customer")) {
            System.out.println("In customer task.");
            handleCustomer(task);
        } else {
            System.out.println("In supplier task.");
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
            default:
                // no matching action
        }
    }
    public static void handleSupplier(String task) {
        switch (task) {
            case "login":
                session.getUserList().doLogin();
                break;
            case "logout":
                session.getUserList().doLogout();
                break;
            case "inventory":
                //session.getInventory();
                break;
            case "orders":
                //session.getOrders();
                break;
            default:
                //yes
        }
    }
}
