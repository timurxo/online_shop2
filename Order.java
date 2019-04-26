package final_project;

import java.util.ArrayList;

public class Order {
    private String userId;
    private ArrayList<Item> items;
    private Integer total;
    private String status;
    public Order(String user, ArrayList<Item> cartItems, Integer cost) {
        userId = user;
        items = cartItems;
        total = cost;
        status = "Processing";
    }

    public String getUserId() {
        return userId;
    }
    public ArrayList<Item> getItems() {
        return items;
    }
    public Integer getTotal() {
        return total;
    }
    public String getStatus() {
        return status;
    }
}
