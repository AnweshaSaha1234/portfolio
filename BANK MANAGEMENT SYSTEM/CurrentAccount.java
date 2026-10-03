/**
 * CurrentAccount.java
 *
 * INHERITS from Account. Allows overdraft up to a fixed limit instead
 * of enforcing a minimum balance. A different override of withdraw()
 * shows POLYMORPHISM alongside SavingsAccount.
 */
public class CurrentAccount extends Account {

    private static final double OVERDRAFT_LIMIT = 1000.0;

    public CurrentAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else if (balance - amount < -OVERDRAFT_LIMIT) {
            System.out.printf("Cannot withdraw. Overdraft limit of %.2f exceeded.%n", OVERDRAFT_LIMIT);
        } else {
            balance -= amount;
            System.out.printf("Withdrew %.2f. New balance: %.2f%n", amount, balance);
        }
    }

    @Override
    public String getAccountType() {
        return "Current";
    }
}
