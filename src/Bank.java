import java.util.Scanner;

public class Bank {

    //SINGLETON
    private static Bank singleInstance = null;

    private Bank() {

    }

    public static Bank getInstance() {

        if (singleInstance == null) {
            singleInstance = new Bank();
        }

        return singleInstance;

    }


    //ATTRIBUTES
    Scanner sc = new Scanner(System.in);
    String bankName = "HBL";

    int init_AdminID = 100;
    int init_UserID = 900;

    int init_AdminAccountNum = 1100;
    int init_UserAccountNum = 9900;

    public Admin admin1 = new Admin("ADMIN", init_AdminID, "password123",
            init_AdminAccountNum, 9756, this, 457910, 150000);

    public User user1 = new User("USER1", init_UserID, true, "pass111",
            init_UserAccountNum, 2882, this, 100000, 57000);

    public User user2 = new User("USER2", ++init_UserID, true, "pass222",
            ++init_UserAccountNum, 3740, this, 250500, 129340);

    public User user3 = new User("USER3", ++init_UserID, false, "",
            ++init_UserAccountNum, 9881, this, 89550, 0);

    Admin[] adminArray;
    User[] userArray;

    int adminArraySize = init_AdminID - 99;
    int userArraySize = init_UserID - 899;

    Admin adminIndex;
    User userIndex;

    //BEHAVIOURS
    private void makeArray() {

        adminArray = new Admin[adminArraySize];
        adminArray[0] = admin1;

        userArray = new User[userArraySize];
        userArray[0] = user1;
        userArray[1] = user2;
        userArray[2] = user3;

    }

    public void checkID(int ID) throws Exception {

        makeArray();

        boolean AdminIDFound;
        boolean UserIDFound;

        AdminIDFound = checkForAdmin(ID);
        UserIDFound = checkForUser(ID);

        if (AdminIDFound){
        }
        else if (UserIDFound){
        }
        else
            System.out.println("Not a valid ID!");

    }

    private boolean checkForAdmin(int ID) throws Exception {

        boolean wasFound = false;
        boolean accountWasFound;
        String password;
        boolean passwordCorrect;

        for (Admin adminIndex: adminArray) {

            if (ID == adminIndex.ID) {
                wasFound = true;
                accountWasFound = checkAccount(adminIndex);

                if (accountWasFound) {
                    password = getPassword();
                    passwordCorrect = checkPassword(password, adminIndex);

                    if (passwordCorrect) {
                        this.adminIndex = adminIndex;
                        adminIndex.showAdminActionsList();
                        adminIndex.getAction();
                    }
                    else {
                        System.out.println("Incorrect password!");
                    }

                }
                else {
                    throw new Exception("Admin account not found! Admin must have an account!");
                }

            }
        }

        return wasFound;
    }

    private boolean checkForUser(int ID) {

        boolean WasFound = false;
        boolean accountWasFound;
        String password;
        boolean passwordCorrect;

        for (User userIndex: userArray) {

            if (ID == userIndex.ID) {
                WasFound = true;
                accountWasFound = checkAccount(userIndex);

                if (accountWasFound) {
                    password = getPassword();
                    passwordCorrect = checkPassword(password, userIndex);

                    if (passwordCorrect) {
                        this.userIndex = userIndex;
                        userIndex.showUserActionsList();
                        userIndex.getAction();
                    }
                    else {
                        System.out.println("Incorrect password!");
                    }
                }
                else {
                    System.out.print("You do not have an account with " + bankName +
                            "\nWould you like to create an account? Yes | No ");

                    String response;
                    response = sc.nextLine();

                    if (response.equalsIgnoreCase("Yes")) {
                        this.userIndex = userIndex;
                        userIndex.createAccount();
                        userIndex.showUserActionsList();
                        userIndex.getAction();
                    }
                    else {
                        System.out.println("Thank you for using our services!");
                    }
                                        
                }

            }
        }

        return WasFound;
    }

    private boolean checkAccount(Admin adminIndex) {
        if (adminIndex.hasAccount) {
            //System.out.println("Has account");
            return true;
        }
        else {
            //System.out.println("Does not have an account");
            return false;
        }
    }

    private boolean checkAccount(User userIndex) {
        if (userIndex.hasAccount) {
            //System.out.println("Has account");
            return true;
        }
        else {
            //System.out.println("Does not account");
            return false;
        }
    }

    private String getPassword() {
        String password;
        System.out.print("Enter password: ");
        password = sc.nextLine();
        return password;
    }

    private boolean checkPassword(String password, Admin adminIndex) {
        if (password.equals(adminIndex.password)) {
            System.out.println("Welcome " + adminIndex.name);
            return true;
        }
        else {
            return false;
        }
    }

    private boolean checkPassword(String password, User userIndex) {
        if (password.equals(userIndex.password)) {
            System.out.println("Welcome " + userIndex.name);
            return true;
        }
        else {
            return false;
        }
    }

}
