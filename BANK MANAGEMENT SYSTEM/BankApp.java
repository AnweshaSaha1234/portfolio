import java.util.Scanner;

/**
 * BankApp.java
 *
 * Entry point. A simple console menu that lets the user create
 * accounts, deposit, withdraw, view, and close accounts — all held
 * in memory via the Bank class. No file or database storage is used,
 * so data resets each time the program restarts.
 */
public class BankApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Bank bank = new Bank();

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("===== Welcome to the Bank Management System =====");

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> viewAccount();
                case 5 -> bank.displayAllAccounts();
                case 6 -> closeAccount();
                case 7 -> {
                    running = false;
                    System.out.println("Thank you for banking with us. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n---------------------------------");
        System.out.println("1. Open a new account");
        System.out.println("2. Deposit money");
        System.out.println("3. Withdraw money");
        System.out.println("4. View a single account");
        System.out.println("5. View all accounts");
        System.out.println("6. Close an account");
        System.out.println("7. Exit");
        System.out.println("---------------------------------");
    }

    private static void createAccount() {
        System.out.println("\n-- Open New Account --");
        String accNo = readString("Account Number: ");
        String name = readString("Holder Name: ");
        double balance = readDouble("Initial Deposit: ");

        System.out.println("Account Type -> 1) Savings  2) Current");
        int type = readInt("Choice: ");

        Account account = (type == 2)
                ? new CurrentAccount(accNo, name, balance)
                : new SavingsAccount(accNo, name, balance);

        bank.addAccount(account);
    }

    private static void deposit() {
        Account acc = findAccountOrWarn();
        if (acc == null) return;

        double amount = readDouble("Amount to deposit: ");
        acc.deposit(amount);
    }

    private static void withdraw() {
        Account acc = findAccountOrWarn();
        if (acc == null) return;

        double amount = readDouble("Amount to withdraw: ");
        acc.withdraw(amount); // runs SavingsAccount's or CurrentAccount's own rules
    }

    private static void viewAccount() {
        Account acc = findAccountOrWarn();
        if (acc == null) return;

        acc.displayInfo();
    }

    private static void closeAccount() {
        String accNo = readString("\nEnter Account Number to close: ");
        boolean removed = bank.removeAccount(accNo);
        System.out.println(removed ? "Account closed successfully." : "Account not found.");
    }

    private static Account findAccountOrWarn() {
        String accNo = readString("\nEnter Account Number: ");
        Account acc = bank.findAccount(accNo);
        if (acc == null) {
            System.out.println("Account not found.");
        }
        return acc;
    }

    // ----- Small input helpers -----

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static double readDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }
}
