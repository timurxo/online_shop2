package final_project;


public class Session {
    private static Session instance = null;
    private User user;
    private String sessionType;
    private Cart cart;
    private Users userList;
    private Session(String session, Cart cartObject) {
        sessionType = session;
        cart = cartObject;
        userList = new Users(this);
    }
    public static Session startCustomerSession(Cart cartObject) {
        instance = new Session("customer", cartObject);
        return instance;
    }
    public static Session startSupplierSession() {
        instance = new Session("supplier", null);
        return instance;
    }
    public static Session getInstance() {
        return instance;
    }
    public void setUser(User newUser) {
        user = newUser;
    }
    public User getUser() {
        return user;
    }
    public Cart getCart() {
        return cart;
    }
    public String getSessionType() {
        return sessionType;
    }
    public Boolean isLoggedIn() {
        return user == null;
    }
    public Users getUserList() {
        return userList;
    }
}
