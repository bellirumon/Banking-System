public interface ATM {
    boolean getTransactionCode(Admin adminIndex);
    boolean getTransactionCode(User userIndex);
    void lockAdminAccount();
    void lockUserAccount();
    void getAmount(Admin adminIndex, int amount);
    void getAmount(User userIndex, int amount);
    void giveAmount(Admin adminIndex, int amount);
    void giveAmount(User userIndex, int amount);
    void printReceipt(Admin adminIndex, int amount);
    void printReceipt(User userIndex, int amount);
}
