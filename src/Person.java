import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Person implements ATM{

    //ATTRIBUTES
    Scanner sc = new Scanner(System.in);

    String name;
    int ID;

    boolean hasAccount;
    int accountNum;
    String password;
    int transactionCode;

    int cashOwned;
    int cashInATM;

    Bank bank;

    //BEHAVIOURS
    public Person(Bank bank) {
        this.bank = bank;
    }

    protected void withdrawMoney(User userIndex) {
        int amount;
        boolean correctTransactionCode;

        amount = inputAmount();
        while (amount == 1) {
            sc.nextLine();
            amount = inputAmount();
        }

        if (amount % 500 == 0 && amount > 0) {
            correctTransactionCode = getTransactionCode(userIndex);
            if (correctTransactionCode) {
                getAmount(userIndex, amount);
            }
        }
        else {
            withdrawMoney(userIndex);
        }
    }

    protected void depositMoney(User userIndex) {
        int amount;
        String response;

        amount = inputAmount();
        while (amount == 1) {
            sc.nextLine();
            amount = inputAmount();
        }

        if (amount % 500 == 0 && amount > 0 ) {
            if (amount <= userIndex.cashOwned) {
                System.out.println("Rs." + amount + " has been deposited to your account!");
                userIndex.cashOwned -= amount;
                userIndex.cashInATM += amount;
            }
            else {
                System.out.println("You do not have enough money to make this deposit!");
                System.out.println("Would you like to try again? Yes | No");
                response = sc.nextLine();
                if (response.equalsIgnoreCase("yes")) {
                    depositMoney(userIndex);
                }
                else {
                    System.out.println("Thank you for using our services!");
                }
            }
        }
        else {
            depositMoney(userIndex);
        }
    }

    protected void changePassword(User userIndex) {
        String password;

        System.out.print("Enter your current password: ");
        password = sc.nextLine();

        if (password.equals(userIndex.password)) {
            System.out.print("Enter your new password: ");
            userIndex.password = userIndex.setPassword();
            System.out.println("Your password has been updated!");
        }
        else {
            System.out.println("Incorrect password!");
        }
    }

    protected void withdrawMoney(Admin adminIndex) {
        int amount;
        boolean correctTransactionCode;

        amount = inputAmount();
        while (amount == 1) {
            sc.nextLine();
            amount = inputAmount();
        }

        if (amount % 500 == 0 && amount > 0) {
            correctTransactionCode = getTransactionCode(adminIndex);
            if (correctTransactionCode) {
                getAmount(adminIndex, amount);
            }
        }
        else {
            withdrawMoney(adminIndex);
        }

    }

    protected void depositMoney(Admin adminIndex) {
        int amount;
        String response;

        amount = inputAmount();
        while (amount == 1) {
            sc.nextLine();
            amount = inputAmount();
        }

        if (amount % 500 == 0 && amount > 0 ) {
            if (amount <= adminIndex.cashOwned) {
                System.out.println("Rs." + amount + " has been deposited to your account!");
                adminIndex.cashOwned -= amount;
                adminIndex.cashInATM += amount;
            }
            else {
                System.out.println("You do not have enough money to make this deposit!");
                System.out.println("Would you like to try again? Yes | No");
                response = sc.nextLine();
                if (response.equalsIgnoreCase("yes")) {
                    depositMoney(adminIndex);
                }
                else {
                    System.out.println("Thank you for using our services!");
                }
            }
        }
        else {
            depositMoney(adminIndex);
        }
    }

    protected void changePassword(Admin adminIndex) {
        String password;

        System.out.print("Enter your current password: ");
        sc.nextLine();
        password = sc.nextLine();

        if (password.equals(adminIndex.password)) {
            System.out.print("Enter your new password: ");
            adminIndex.password = adminIndex.setPassword();
            System.out.println("Your password has been updated!");
        }
        else {
            System.out.println("Incorrect password!");
        }
    }

    private int inputAmount() {
        try{
            System.out.print("Enter amount in multiples of 500: ");
            return sc.nextInt();
        } catch (InputMismatchException e) {
            return 1;
        }
    }

    protected String setPassword() {
        String password;
        password = sc.nextLine();
        return password;
    }



    @Override
    public boolean getTransactionCode(User userIndex) {
        System.out.print("Enter your transaction code: ");
        int code;
        code = sc.nextInt();

        if (code == userIndex.transactionCode) {
            return true;
        }
        else {
            System.out.println("Incorrect transaction code!");
            System.out.print("Enter your transaction code (1 try left): ");
            code = sc.nextInt();

            if (code == userIndex.transactionCode) {
                return true;
            }
            else {
                System.out.println("Incorrect transaction code!");
                lockUserAccount();
                return false;
            }
        }
    }

    @Override
    public boolean getTransactionCode(Admin adminIndex) {
        System.out.print("Enter your transaction code: ");
        int code;
        code = sc.nextInt();

        if (code == adminIndex.transactionCode) {
            return true;
        }
        else {
            System.out.println("Incorrect transaction code!");
            System.out.print("Enter your transaction code (1 try left): ");
            code = sc.nextInt();

            if (code == adminIndex.transactionCode) {
                return true;
            }
            else {
                System.out.println("Incorrect transaction code!");
                lockAdminAccount();
                return false;
            }
        }
    }

    @Override
    public void lockAdminAccount() {
        System.out.println("Your account has been temporarily suspended!");
    }

    @Override
    public void lockUserAccount() {
        System.out.println("Your account has been temporarily locked!");
    }

    @Override
    public void getAmount(Admin adminIndex, int amount) {
        if (amount <= adminIndex.cashInATM) {
            giveAmount(adminIndex, amount);
        }
        else {
            System.out.println("You do not have Rs." + amount + " in your account!");
        }
    }

    @Override
    public void getAmount(User userIndex, int amount) {
        if (amount <= userIndex.cashInATM) {
            giveAmount(userIndex, amount);
        }
        else {
            System.out.println("You do not have Rs." + amount + " in your account!");
        }
    }

    @Override
    public void giveAmount(User userIndex, int amount) {
        userIndex.cashInATM -= amount;
        userIndex.cashOwned += amount;

        System.out.println("You have received Rs. " + amount + " from the ATM");
        System.out.println("You now have Rs." + userIndex.cashOwned);
        System.out.print("Would you like to get a Receipt? Yes | No ");
        String response;
        sc.nextLine();
        response = sc.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            printReceipt(userIndex, amount);
        }
        else {
            System.out.println("Thank you for using our services!");
        }
    }

    @Override
    public void giveAmount(Admin adminIndex, int amount) {
        adminIndex.cashInATM -= amount;
        adminIndex.cashOwned += amount;

        System.out.println("You have received Rs. " + amount + " from the ATM");
        System.out.println("You now have Rs." + adminIndex.cashOwned);
        System.out.print("Would you like to get a Receipt? Yes | No ");
        String response;
        sc.nextLine();
        response = sc.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            printReceipt(adminIndex, amount);
        }
        else {
            System.out.println("Thank you for using our services!");
        }
    }

    @Override
    public void printReceipt(User userIndex, int amount) {
        System.out.println("----------" + bank.bankName + "----------" +
                "\nUser ID: " + userIndex.ID +
                "\nAccount Number: " + userIndex.accountNum +
                "\nAmount in bank before transaction: " + (userIndex.cashInATM + amount) +
                "\nCurrent transaction: " + amount +
                "\nAmount left in bank: " + userIndex.cashInATM +
                "\nThank you for using our services");
    }

    @Override
    public void printReceipt(Admin adminIndex, int amount) {
        System.out.println("----------" + bank.bankName + "----------" +
                "\nUser ID: " + adminIndex.ID +
                "\nAccount Number: " + adminIndex.accountNum +
                "\nAmount in bank before transaction: " + (adminIndex.cashInATM + amount) +
                "\nCurrent transaction: " + amount +
                "\nAmount left in bank: " + adminIndex.cashInATM +
                "\nThank you for using our services");
    }

}
