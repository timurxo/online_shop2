package final_project;

import java.util.ArrayList;
import java.util.Scanner;

// GETS ID AND PASSWORD AND ID FROM USER AND CHECKS IF THEY ARE CORRECT
public class Users {
	
    private ArrayList<User> registeredUsers = new ArrayList<>();
    private Session session;
    
    public Users(Session ses) {
        registeredUsers.add(new User(username, password, name, address, phone_number, "customer"));
        registeredUsers.add(new User(username, password, name, address, phone_number, "supplier"));
        session = ses;
    }
    
    // CHECKS IF THE PASSWORD IS CORRECT
    public Boolean authUser(User user, String password) throws BadPasswordException {
    	// if entered password is the same as the one that has been stored in the system
        if (user.getPass().equals(password)) {
            return true;
        }
        throw new BadPasswordException();
    }
    
    
    // CHECKS IF THE ID IS CORRECT
    public User getUserById(String username) throws NotFoundException {
    	// if there is the same user name in the system
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
    	
    	// take id and password from user
        Scanner sc = new Scanner(System.in); 

        Users userList = session.getUserList();
        
        // ASK IF THE USER HAS AN ACCOUNT
        System.out.println("Do you have an account? (yes/no)");
        String yn = sc.nextLine();
        
   // IF USER ALREADY HAS AN ACCOUNT THEN THE SYSTEM ASKS TO INPUT IT 
   // AND CHECKS IF THE INFORMATION IS CORRECT
        
       if (yn.contentEquals("yes"))
        {
        
        // GET ID
		System.out.println("Enter your ID: ");
        String userId = sc.nextLine(); 
        
        // GET PASSWORD
		System.out.println("Enter your password: ");
        String pass = sc.nextLine(); 
        
        
        User user = null;
        
        
        try {
            user = userList.getUserById(userId);
            userList.authUser(user, pass);
            
            // user not found
        } catch (NotFoundException e) {
        	
        	// ask to enter id again
        	do { 
        		System.out.println("Enter ID again: ");
                String username = sc.nextLine(); 
        	} while (!(user.getId().equals(userId)));
        	
        	// wrong password
        } catch (BadPasswordException e) {
        	
        	// ask to try again
        	do {
            System.out.println("Wrong password. Try again");
            String password = sc.nextLine(); 
        	} while (!(user.getPass().equals(pass)));
            
        if (user == null) {
            System.out.println("Enter info");
        }
        session.setUser(user);
    }
   }
        
        else if (yn.equals("no"))
        {
        	// ASK USER TO CREATE AN ACCOUNT
    		System.out.println("You have to create an account");
    		
            // Ask for ID
            System.out.println("Enter your ID: ");
            String username = sc.nextLine(); 

            // Ask for password
            System.out.println("Enter your password: ");
            String password = sc.nextLine();  
            
            // Ask for name
            System.out.println("Enter your name: ");
            String name = sc.nextLine(); 
            
            // Ask for address
            System.out.println("Enter your address: ");
            String address = sc.nextLine(); 
            
            // Ask for phone number
            System.out.println("Enter your phone number: ");
            String phone_number = sc.nextLine(); 
            
            // Ask for credit card number
            System.out.println("Enter your credit card number: ");
            String credit_card_number = sc.nextLine(); 
            
            
            // When all required information is taken
            System.out.println("An account has been created!");

        }
       
    
    }
        	
        
        
    public void doLogout() {
        session.setUser(null);
    }
}
