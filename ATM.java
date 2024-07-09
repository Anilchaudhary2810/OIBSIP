import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    private final String[] validAccounts = {"19", "1", "25", "16", "56"};
    private final int[] verifiedPin = {19, 1, 25, 16, 56};
    private final String[] verifiedUsers = {"Anil", "Ayush", "Vinod", "Pranam", "Prince"};

    private boolean isLoggedIn = false;
    private int userIndex;
    private ArrayList<Double> initialAmount = new ArrayList<>();
    private ArrayList<String> transactions = new ArrayList<>();

    private Scanner scanner = new Scanner(System.in);

    public ATM() {
        login();
    }

    private void login() {
        System.out.println("=================================");
        System.out.println("          ATM Login          ");
        System.out.println("=================================");
        System.out.print("Enter User ID: ");
        String userID = scanner.nextLine();

        System.out.print("Enter User Pin: ");
        int userPin = scanner.nextInt();

        for (int i = 0; i < verifiedUsers.length; i++) {
            if (verifiedUsers[i].equalsIgnoreCase(userID)) {
                if (verifiedPin[i] == userPin) {
                    userIndex = i;
                    System.out.println("Successfully Logged In");
                    isLoggedIn = true;
                    break;
                }
            }
        }
        if (!isLoggedIn) {
            System.out.println("Incorrect User ID OR User Pin");
            return;
        }

        System.out.print("Enter initial Balance of your Account: ");
        double amount = scanner.nextDouble();
        initialAmount.add(amount);
    }

    public void transactionHistory() {
        System.out.println("=================================");
        System.out.println("      Transaction History      ");
        System.out.println("=================================");
        if (transactions.isEmpty()) {
            System.out.println("No Transaction History Found");
            return;
        }
        System.out.println("Your current Balance is: " + initialAmount.get(userIndex));
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println((i + 1) + ". " + transactions.get(i));
        }
    }

    public void withdraw(double amount) {
        if (amount > initialAmount.get(userIndex)) {
            System.out.println("You have insufficient Balance");
        } else {
            initialAmount.set(userIndex, initialAmount.get(userIndex) - amount);
            System.out.println("You have withdrawn Rs." + amount + " successfully");
            transactions.add("Withdrawn amount: " + amount);
        }
    }

    public void deposit(double amount) {
        initialAmount.set(userIndex, initialAmount.get(userIndex) + amount);
        System.out.println("You have deposited Rs." + amount + " successfully");
        transactions.add("Deposited amount: " + amount);
    }

    public void transfer(double amount, String accountNO) {
        boolean isValidAccount = false;
        for (int i = 0; i < validAccounts.length; i++) {
            if (validAccounts[i].equalsIgnoreCase(accountNO)) {
                isValidAccount = true;
                initialAmount.set(userIndex, initialAmount.get(userIndex) - amount);
                System.out.println("Successfully Transferred RS." + amount + " to account number " + accountNO);
                transactions.add("Transferred RS." + amount + " to account number " + accountNO);
                break;
            }
        }
        if (!isValidAccount) {
            System.out.println("Enter valid Account Number");
        }
    }

    public void run() {
        int choice;
        do {
            System.out.println("=================================");
            System.out.println("          ATM Menu          ");
            System.out.println("=================================");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    transactionHistory();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter recipient user ID: ");
                    String recipientId = scanner.nextLine();
                    transfer(transferAmount, recipientId);
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 5);
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.run();
    }
}