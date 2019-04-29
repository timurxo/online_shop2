public class Item {

    // GET NAME AND PRICE OF AN ITEM

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
