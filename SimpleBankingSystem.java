import java.util.Scanner;
import java.util.HashMap;

class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String password;

    public Account(String accountNumber, String accountHolderName, double initialDeposit, String password) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.password = password;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

public class SimpleBankingSystem {
    private static HashMap<String, Account> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\nWelcome to Galactic Bank! Press 1, 2, 3, 4 and 5 to do any one of the task");
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Funds");
            System.out.println("3. Withdraw Funds");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    depositFunds();
                    break;
                case 3:
                    withdrawFunds();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    System.out.println("Thank you for using Galactic Bank!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter account holder name: ");
        String accountHolderName = scanner.nextLine();
        System.out.print("Enter initial deposit: ");
        double initialDeposit = scanner.nextDouble();
        scanner.nextLine();  // Consume newline
        System.out.print("Create a password: ");
        String password = scanner.nextLine();

        Account newAccount = new Account(accountNumber, accountHolderName, initialDeposit, password);
        accounts.put(accountNumber, newAccount);
        System.out.println("Account created successfully!");
    }

    private static void depositFunds() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();  // Consume newline
            account.deposit(amount);
            System.out.println("Deposit successful! Current balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdrawFunds() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            if (account.authenticate(password)) {
                System.out.print("Enter amount to withdraw: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();  // Consume newline
                if (account.withdraw(amount)) {
                    System.out.println("Withdrawal successful! Current balance: " + account.getBalance());
                } else {
                    System.out.println("Insufficient funds.");
                }
            } else {
                System.out.println("Incorrect password.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void checkBalance() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            if (account.authenticate(password)) {
                System.out.println("Current balance: " + account.getBalance());
            } else {
                System.out.println("Incorrect password.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }
}