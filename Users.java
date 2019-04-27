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
                System.out.println("In user not found exception. ");
                // user not found do some looping or something here
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
            //do create account
        }

    }
    public void doLogout() {
        session.setUser(null);
    }
}
