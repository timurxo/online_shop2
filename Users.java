package final_project;

import java.util.ArrayList;

public class Users {
    private ArrayList<User> registeredUsers = new ArrayList<>();
    private Session session;
    public Users(Session ses) {
        registeredUsers.add(new User("test", "pass", "1234", "123 main st", 12315411, "customer"));
        registeredUsers.add(new User("testSup1", "pass", "1234", "123 main st", 12315411, "supplier"));
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
        Users userList = session.getUserList();
        String userId = "test";
        String pass = "pass";
        User user = null;
        try {
            user = userList.getUserById(userId);
            userList.authUser(user, pass);
        } catch (NotFoundException e) {
            // user not found do some looping or something here
        } catch (BadPasswordException e) {
            // bad password. probably wanna handle this
        }
        if (user == null) {
            // you fucked up
        }
        session.setUser(user);
    }
    public void doLogout() {
        session.setUser(null);
    }
}
