public class Bank {

    public Boolean verifyCard(Integer card, Integer price) {
        return price < 10000;
    }

    public void placeOrder(Integer card, Integer price) throws BankRejectionException {
        if (price > 10000) {
            throw new BankRejectionException();
        }
    }
}
