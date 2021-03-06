package final_project;

import java.util.ArrayList;
import java.util.Scanner;

public class Users {
    private ArrayList<User> registeredUsers = new ArrayList<>();
    private Session session;
    public Users(Session ses) {
        registeredUsers.add(new User("test", "pass", "1234", "123 main st", 12315411, "customer"));
        registeredUsers.add(new User("testSup1", "pass", "1234", "123 main st", 12315411, "supplier"));
        registeredUsers.add(new User("mfirlej", "asdf", "1235677", "123 main road", 4356456, "customer"));

        session = ses;
    }
    public Boolean authUser(User user, String password) throws BadPasswordException {
        if (user.getPass().equals(password)) {
            return true;
        }
        throw new BadPasswordException();
    }
    public User getUserById(String username) throws NotFoundException {
        for (User user: registeredUsers) {
            if (user.getId().equals(username)) {
                return user;
            }
        }
        throw new NotFoundException();
    }
    public void addUser(User user) {
        registeredUsers.add(user);
    }
    public void doLogin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("In doLogin!");
        Users userList = session.getUserList();
        System.out.println("Made it past userlist.");

        System.out.println("Do you have an account with the OSS? (y/n)");
        String accountStatus = sc.next();
        System.out.println("Your input: " + accountStatus);
        if(accountStatus.equals("yes") || accountStatus.equals("y")) {
            System.out.println("Please enter your username: ");
            String userId = sc.next();
            System.out.println("Please enter your password: ");
            String pass = sc.next();
            User user = null;
            try {
                user = userList.getUserById(userId);
                userList.authUser(user, pass);
                System.out.println("Successfully logged in! Welcome to the OSS.");
            } catch (NotFoundException e) {
                sc.nextLine();
                while (!(user.getUserId().equals())) {
                    System.out.println("The username did not match. Please try again: ");
                    userId = sc.nextLine();
                }                
            } catch (BadPasswordException e) {
                sc.nextLine();
                while (!(user.getPass().equals(pass))) {
                    System.out.println("The password did not match. Please try again: ");
                    pass = sc.nextLine();
                }
                System.out.println("Successfully logged in! Welcome to the OSS.");
            }
            if (user == null) {
                System.out.println("I don't know how you got here, but you deserve a gold star for the effort.");
            }
            session.setUser(user);
        } else {
            System.out.println("Please create your account. Choose your username: ");
            String newUsername = sc.nextLine();
            System.out.println("Create a password for your account: ");
            String newPassword = sc.nextLine();
            System.out.println("Please tell us your telephone number: ");
            int newPhoneWhoDis = sc.nextLine();
            System.out.println("Please tell us your address: ");
            String newAddress = sc.nextLine();
            System.out.println("Give us your credit card number: ");
            int newCreditCard = sc.nextLine();
            String newType = "customer";
            createUser(newUsername, newPassword, newPhoneWhoDis, newAddress, newCreditCard, newType);
            System.out.println("You have successfully created your account! Congrats! Now please proceed to login.");
            doLogin();       
        }

    }
    public void doLogout() {
        session.setUser(null);
    }
    public void createUser(String newUsername, String newPassword, int newPhoneWhoDis, String newAddress, int newCreditCard, String newType) {
        Session.getInstance().getUserList().addUser(new User(newUsername, newPassword, newPhoneWhoDis, newAddress, newCreditCard, newType));
    }
}
