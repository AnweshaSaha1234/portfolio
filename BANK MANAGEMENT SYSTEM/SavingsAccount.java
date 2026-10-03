/**
 * SavingsAccount.java
 *
 * INHERITS from Account. Enforces a minimum balance rule on withdrawal.
 * Overriding withdraw() and getAccountType() demonstrates POLYMORPHISM:
 * the same method call behaves differently depending on the object type.
 */
public class SavingsAccount extends Account {

    private static final double MIN_BALANCE = 500.0;

    public SavingsAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else if (balance - amount < MIN_BALANCE) {
            System.out.printf("Cannot withdraw. Minimum balance of %.2f must be maintained.%n", MIN_BALANCE);
        } else {
            balance -= amount;
            System.out.printf("Withdrew %.2f. New balance: %.2f%n", amount, balance);
        }
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }
}
