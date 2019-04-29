import java.util.ArrayList;
import java.util.Scanner;

// CATALOG

// select items
// place order

public class Cart {
    private ArrayList<Item> availableProducts;
    private ArrayList<Item> cartItems;


    public Cart() {
        availableProducts.add(new Item("TV", 1700));
        availableProducts.add(new Item("PS4", 399));
        availableProducts.add(new Item("Xbox", 299));
        availableProducts.add(new Item("Witcher 3", 59));
        availableProducts.add(new Item("Red Dead Redemption", 49));
        availableProducts.add(new Item("Black Ops", 59));
        availableProducts.add(new Item("Pre-Build Desktop computer", 2000));
        availableProducts.add(new Item("Keyboard", 35));
        availableProducts.add(new Item("Mouse", 25));
        availableProducts.add(new Item("1440p Monitor", 300));
    }


    // INTERACTION WITH BANK REGARDING VERIFICATION OF OPERATION
    public Order checkout() throws UnauthorizedException {

        User user = Session.getInstance().getUser();
        Bank bank = new Bank();
        Boolean verified = bank.verifyCard(user.getCard(), getCartTotal());

        // IF NOT VERIFIED BY BANK
        if (!verified) {
            throw new UnauthorizedException();
        }

        // IF VERIFIED BY BANK -> create order
        else
            {
            Order order = new Order(user.getId(), cartItems, getCartTotal());
            return order;
        }
    }



    // USER SELECTS ITEMS AND ADDS THEM TO CART
    public void addItemsToCart() {
        Scanner sc = new Scanner(System.in);

        for (int i=0; i < availableProducts.size(); i++) {
            System.out.println(i + ". " + availableProducts.get(i).getName() + " - $" + availableProducts.get(i).getPrice());
        }


        // Ask to make the first choice
        System.out.println("Select an item by entering it's number: ");
        Integer choice = sc.nextInt();
        cartItems.add(availableProducts.get(choice));



        // keep asking for selection of other items
        while (true) {
            System.out.println("Do you need something else? (yes/no)");

            String yn = sc.nextLine();

            if (yn.equals("yes") || yn.equals("y")) {
                System.out.println("Select an item by entering it's number ");
                choice = sc.nextInt();
                cartItems.add(availableProducts.get(choice));

            } else if (yn.equals("no")) {
                System.out.println("You've chosen: " + cartItems);
                break;
            }
        }
    }



    public ArrayList<Item> getCart() {
        return cartItems;
    }


    public Integer getCartTotal() {

        Integer total = 0;

           for (Item cartItem : cartItems) {
               total += cartItem.getPrice();
           }
           return total;

    }
}
