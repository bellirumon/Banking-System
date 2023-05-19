import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        int ID;

        Bank HBL = Bank.getInstance();

        System.out.println("-----Welcome to " + HBL.bankName + "-----");

        System.out.print("Enter your ID: ");
        ID = sc.nextInt();

        HBL.checkID(ID);

    }
}

