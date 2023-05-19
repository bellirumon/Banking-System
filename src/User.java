import java.util.Scanner;

public class User extends Person{

    Bank bank;
    int tempAccountNum;
    int tempTransactionCode;

    public User(String name, int ID, boolean hasAccount, String password, int accountNum,
                int transactionCode, Bank bank, int cashOwned, int cashInATM) {

        super(bank);

        this.name =  name;
        this.ID = ID;
        this.hasAccount = hasAccount;
        if (this.hasAccount) {
            this.password = password;
            this.tempAccountNum = 0;
            this.accountNum = accountNum;
            this.transactionCode = transactionCode;
            this.cashInATM = cashInATM;
        }
        else {
            this.password = null;
            this.tempAccountNum = accountNum;
            this.accountNum = 0;
            this.tempTransactionCode = transactionCode;
            this.transactionCode = 0;
            this.cashInATM = 0;
        }
        this.bank = bank;
        this.cashOwned = cashOwned;
    }


    //ATTRIBUTES
    Scanner sc = new Scanner(System.in);

    //BEHAVIOURS
    public void showUserActionsList(){
        System.out.print("What would you like to do?" +
                "\n1. Withdraw Money" +
                "\n2. Deposit Money" +
                "\n3. Change Password" +
                "\n4. Delete Account" +
                "\nEnter 1 | 2 | 3 | 4 to choose: ");
    }

    public void getAction() {
        int action;
        action = sc.nextInt();

        switch (action) {
            case 1:
                bank.userIndex.withdrawMoney(bank.userIndex);
                break;
            case 2:
                bank.userIndex.depositMoney(bank.userIndex);
                break;
            case 3:
                bank.userIndex.changePassword(bank.userIndex);
                break;
            case 4:
                bank.userIndex.deleteAccount();
                break;
            default:
                System.out.print("Invalid Action! Enter a valid number!");
                getAction();
                break;
        }
    }

    public void createAccount() {
        this.hasAccount = true;
        this.accountNum = tempAccountNum;
        this.transactionCode = tempTransactionCode;
        System.out.print("Set a password for your account: ");
        this.password = setPassword();
    }

    private void deleteAccount() {
        String password;
        String response;

        System.out.print("Enter your password to delete your account: ");
        sc.nextLine();
        password = sc.nextLine();

        if (password.equals(bank.userIndex.password)) {
            System.out.print("Are you sure you want to delete your account?" +
                    " Your deposited money will be returned to you! Yes | No ");
            response = sc.nextLine();

            if (response.equalsIgnoreCase("yes")) {
                bank.userIndex.cashOwned += bank.userIndex.cashInATM;
                bank.userIndex.cashInATM = 0;
                bank.userIndex = null;
                System.out.println("Your account has been successfully deleted!");
            }
        }
        else {
            System.out.println("Incorrect password!");
        }
    }

    public String toString(User userIndex) {
        return "ID: " + userIndex.ID +
                "\nName: " + userIndex.name +
                "\nPassword: " + userIndex.password +
                "\nAccount Number: " + userIndex.accountNum +
                "\nTransaction Code: " + userIndex.transactionCode +
                "\nCash in ATM: " + userIndex.cashInATM;
    }
}
