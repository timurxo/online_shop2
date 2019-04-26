package final_project;

public class EntryPoint {
    private Session session;
    public void main(String[] args) {
        // get session type (customer or supplier)
        session = Session.startCustomerSession(new Cart());

        // get task
        String task = "login";

        if (session.getSessionType().equals("customer")) {
            handleCustomer(task);
        } else {
            handleSupplier(task);
        }
    }
    public void handleCustomer(String task) {
        switch (task) {
            case "login":
                session.getUserList().doLogin();
                break;
            case "logout":
                session.getUserList().doLogout();
                break;
            case "shop":
                session.getCart().addItemsToCart();
                break;
            case "checkout":
                try {
                    session.getCart().checkout();
                } catch (UnauthorizedException e) {
                    // prob wanna handle this
                }
                break;
            case "createAccount":
                // ya gotta implement this
                break;
            default:
                // no matching action
        }
    }
    public void handleSupplier(String task) {
        // handle
    }
}
