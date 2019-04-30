package final_project;

import java.util.ArrayList;
import java.util.Scanner;

public class Cart
{
	private ArrayList<Item> availableProducts = new ArrayList<>();
    private ArrayList<Item> cartItems = new ArrayList<>();;


    public Cart() 
    {
   			this.availableProducts.add(new Item("TV", 1700, 3));
   			this.availableProducts.add(new Item("PS4", 399, 10));
   			this.availableProducts.add(new Item("Xbox", 299, 3));
   			this.availableProducts.add(new Item("Witcher 3", 59, 20));
   			this.availableProducts.add(new Item("Red Dead Redemption", 49, 1));
   			this.availableProducts.add(new Item("Black Ops", 59, 200));
   			this.availableProducts.add(new Item("Pre-Build Desktop computer", 2000, 4));
   			this.availableProducts.add(new Item("Keyboard", 35, 16));
   			this.availableProducts.add(new Item("Mouse", 25, 10));
   			this.availableProducts.add(new Item("1440p Monitor", 300, 25));
    		
    }


    public Order checkout() throws UnauthorizedException 
    {
    	System.out.println("\nPlease Wait......redirecting to checkout");
        User user = Session.getInstance().getUser();
        Bank bank = new Bank();
        Boolean verified = bank.verifyCard(user.getCard(), getCartTotal());

        // if information is incorrect
        if (!verified) {
            throw new UnauthorizedException();
        } 
        else // if everything is verified
        {
        	System.out.println("Thank you for shopping at OSS you have sucessfully checked out!");
            Order order = new Order(user.getId(), cartItems, getCartTotal());
            return order;
        }
    }
    
    public void addItemsToCart() 
    {
        Scanner sc = new Scanner(System.in);

        for (int i=0; i < availableProducts.size(); i++) {
            System.out.println(i + ". " + availableProducts.get(i).getName() + " - $" + availableProducts.get(i).getPrice() + " (amount: " + availableProducts.get(i).getQuantity() + ")\n");
        }


        // Ask to make the first choice
        System.out.println("Select an item by entering it's number: ");
        Integer choice = sc.nextInt();
        cartItems.add(availableProducts.get(choice));



        // keep asking for selection of other items
        while (true) 
        {
        	sc.nextLine();
            System.out.println("Do you need something else? (yes/no)");

            String yn = sc.nextLine();

            if (yn.equals("yes") || yn.equals("y")) {
                System.out.println("Select an item by entering it's number: ");
                choice = sc.nextInt();
                cartItems.add(availableProducts.get(choice));
            } else if (yn.equals("no")) {


                System.out.println("You've chosen: ");
                for (Item cartItem : cartItems) {
                    System.out.println(cartItem.getName());
                }
                
                break;
            }
        }
        
        sc.close();
    }
    
    

    public ArrayList<Item> getCart() {
        return cartItems;
    }
    
    public Integer getCartTotal() {
        
        
        // verifyCard(int card, int price)
        // 
        Integer total = 0;
        
	    // if the amount on card is exceeded
     //   if (verifyCard(88, 445))
        
        for (Item cartItem : cartItems) {
            total += cartItem.getPrice();
        }
        return total;
    }
    
    

}
