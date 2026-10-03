import java.util.ArrayList;
import java.util.List;

/**
 * Bank.java
 *
 * Manages a collection of Account objects in memory (no database).
 * Because the list is typed as List<Account>, it can hold a mix of
 * SavingsAccount and CurrentAccount objects — POLYMORPHISM lets us
 * treat them uniformly while each still behaves according to its
 * own overridden methods.
 */
public class Bank {

    private final List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        accounts.add(account);
        System.out.println("Account created successfully: " + account.getAccountNumber());
    }

    public Account findAccount(String accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                return acc;
            }
        }
        return null;
    }

    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        for (Account acc : accounts) {
            acc.displayInfo(); // calls the correct overridden behavior automatically
        }
    }

    public boolean removeAccount(String accountNumber) {
        Account acc = findAccount(accountNumber);
        if (acc != null) {
            accounts.remove(acc);
            return true;
        }
        return false;
    }
}
