public class User {
    private String id;
    private String pass;
    private String address;
    private String phone;
    private Integer card;
    private String type;

    public User(String username, String password, String tel, String add, Integer cc, String typ) {
        id = username;
        pass = password;
    }
    public String getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }
    public Integer getCard() {
        return card;
    }
    public String getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }
    public String getType() {
        return type;
    }
}
