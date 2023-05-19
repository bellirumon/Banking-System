import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Admin extends Person{

    Bank bank;

    public Admin(String name, int ID, String password,
                 int accountNum, int transactionCode, Bank bank, int cashOwned, int cashInATM) {

        super(bank);

        this.name =  name;
        this.ID = ID;
        this.hasAccount = true;
        this.password = password;
        this.accountNum = accountNum;
        this.transactionCode = transactionCode;
        this.bank = bank;
        this.cashOwned = cashOwned;
        this.cashInATM = cashInATM;

    }

    //ATTRIBUTES
    Scanner sc = new Scanner(System.in);

    //BEHAVIOURS
    public void showAdminActionsList(){
        System.out.print("What would you like to do?" +
                "\n1. Withdraw Money" +
                "\n2. Deposit Money" +
                "\n3. Change Password" +
                "\n4. Upload User Data " +
                "\n5. Request Account Deletion" +
                "\nEnter 1 | 2 | 3 | 4 | 5 to choose: ");
    }

    public void getAction() {
        int action;
        action = sc.nextInt();

        switch (action) {
            case 1:
                bank.adminIndex.withdrawMoney(bank.adminIndex);
                break;
            case 2:
                bank.adminIndex.depositMoney(bank.adminIndex);
                break;
            case 3:
                bank.adminIndex.changePassword(bank.adminIndex);
                break;
            case 4:
                bank.adminIndex.uploadUserData();
                break;
            case 5:
                bank.adminIndex.requestAccountDeletion();
                break;
            default:
                System.out.print("Invalid Action! Enter a valid number!");
                getAction();
                break;
        }
    }

    private void uploadUserData() {

        try{

            FileWriter writer = new FileWriter("BANK_UserData.txt", false);
            BufferedWriter bw = new BufferedWriter(writer);

            bw.write("----------" + bank.bankName + " - User Data----------");
            bw.newLine();
            bw.newLine();

            for (User userIndex: bank.userArray) {
                if (userIndex.hasAccount) {
                    bw.write(userIndex.toString(userIndex));
                    bw.newLine();
                    bw.newLine();
                }
            }

            bw.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void requestAccountDeletion() {
        if (bank.adminArraySize > 1) {
            String password;
            String response;

            System.out.print("Enter your password to delete your account: ");
            sc.nextLine();
            password = sc.nextLine();

            if (password.equals(bank.adminIndex.password)) {
                System.out.print("Are you sure you want to delete your account?" +
                        " Your deposited money will be returned to you! Yes | No ");
                response = sc.nextLine();

                if (response.equalsIgnoreCase("yes")) {
                    bank.adminIndex.cashOwned += bank.adminIndex.cashInATM;
                    bank.adminIndex.cashInATM = 0;
                    bank.adminIndex = null;
                    System.out.println("Your account has been successfully deleted!");
                }

            }
            else {
                System.out.println("Incorrect password!");
            }
        }
        else {
            System.out.println("Your account deletion request has been denied " +
                    "since the bank currently has only one admin!");
        }
    }

}
