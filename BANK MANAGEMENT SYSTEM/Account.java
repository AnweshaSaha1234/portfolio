/**
 * Account.java
 *
 * An ABSTRACT class representing a generic bank account.
 * It demonstrates ABSTRACTION - it defines what every account must be
 * able to do (withdraw, display info) without fully deciding how
 * every subtype behaves. It also holds shared state/behavior so
 * subclasses don't repeat themselves (INHERITANCE).
 */
public abstract class Account {

    // Encapsulation: fields are private/protected, accessed via methods
    private final String accountNumber;
    private final String holderName;
    protected double balance;

    public Account(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    // ----- Getters (Encapsulation) -----

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    // A concrete method shared by all account types
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
        System.out.printf("Deposited %.2f. New balance: %.2f%n", amount, balance);
    }

    // ABSTRACT METHOD: every subclass must define withdrawal rules itself,
    // since a Savings account and a Current account behave differently
    // (e.g. minimum balance, overdraft limit). This is POLYMORPHISM in action.
    public abstract void withdraw(double amount);

    // Another abstract method — each account type describes itself differently
    public abstract String getAccountType();

    // A shared method that uses the abstract method, so behavior changes
    // automatically depending on the actual object type at runtime.
    public void displayInfo() {
        System.out.printf("[%s] Acc No: %s | Holder: %s | Balance: %.2f%n",
                getAccountType(), accountNumber, holderName, balance);
    }
}
