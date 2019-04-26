package final_project;

public class Item {
    private String name;
    private Integer price;
    public Item(String productName, Integer productPrice) {
        name = productName;
        price = productPrice;
    }
    public String getName() {
        return name;
    }
    public Integer getPrice() {
        return price;
    }
}
