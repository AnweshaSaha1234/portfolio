# Bank Management System (Pure Java OOP)

A simple console-based project demonstrating core Java OOP concepts.
No database, no external libraries — just plain Java, running entirely
in memory.

## OOP Concepts Demonstrated

| Concept        | Where |
|-----------------|-------|
| Encapsulation   | `Account.java` — private/protected fields with public getters and controlled methods |
| Abstraction     | `Account.java` is `abstract`, with abstract methods `withdraw()` and `getAccountType()` |
| Inheritance     | `SavingsAccount` and `CurrentAccount` both `extends Account` |
| Polymorphism    | Each subclass overrides `withdraw()` differently; `Bank` stores a `List<Account>` and calls the correct version automatically at runtime |
| Composition     | `Bank.java` "has-a" list of `Account` objects |

## Project Structure

```
BankManagementSystem/
├── Account.java          (abstract base class)
├── SavingsAccount.java   (enforces minimum balance)
├── CurrentAccount.java   (allows overdraft)
├── Bank.java             (manages accounts in memory)
├── BankApp.java          (main - console menu)
├── README.md
└── .gitignore
```

## How to Compile & Run

No setup, database, or external dependencies are needed. Install JDK 14 or newer (JDK 17 recommended).

**From the project's root folder:**

```bash
javac -d bin *.java
java -cp bin BankApp
```

## Using the App

You'll see a console menu:

```
1. Open a new account
2. Deposit money
3. Withdraw money
4. View a single account
5. View all accounts
6. Close an account
7. Exit
```

Try this flow to see polymorphism in action:
1. Open a **Savings** account with ₹1000 and try withdrawing ₹600 —
   it will be blocked (minimum balance rule).
2. Open a **Current** account with ₹1000 and try withdrawing ₹1500 —
   it will succeed (overdraft allowed up to ₹1000).

Both calls go through the exact same line of code in `BankApp.java`
(`acc.withdraw(amount)`), but produce different behavior because the
object's actual type decides which `withdraw()` runs.

## Notes

- All data is stored in memory (an `ArrayList` inside `Bank`), so it
  resets every time you restart the program. That's intentional —
  this project is meant to demonstrate OOP, not persistence.
- To extend this project, you could add a `FixedDepositAccount` class,
  transfer-between-accounts logic, or transaction history — the class
  hierarchy makes it easy to add new account types without touching
  `BankApp.java` or `Bank.java`.
