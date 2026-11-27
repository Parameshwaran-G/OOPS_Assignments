
import java.util.Scanner;

public class BankingSystem {

    static BankAccount getAccount(Scanner sc, int accountType) {

        if (accountType != 1 && accountType != 2) {
            throw new IllegalArgumentException("Invalid account type");
        }

        int accountNumber = sc.nextInt();
        double balance = sc.nextDouble();

        if (accountType == 1) {
            double interestRate = sc.nextDouble();
            return new SavingsAccount(accountNumber, balance, interestRate);
        }

        double overDraftLimit = sc.nextDouble();

        return new CurrentAccount(accountNumber, balance, overDraftLimit);
    }

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {

            BankAccount account;

            int accountType = sc.nextInt();

            account = getAccount(sc, accountType);

            System.out.printf("%.2f", account.calculateFinalBalance());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}

abstract class BankAccount {

    private int accountNumber;
    private double balance;

    public BankAccount(int accountNumber, double balance) {

        if (accountNumber < 1 || accountNumber > 1000000) {
            throw new IllegalArgumentException("Invalid account number");
        }

        if (!Double.isFinite(balance) || balance <= 0.0 || balance > 10000000.0) {
            throw new IllegalArgumentException("Invalid balance");
        }

        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public abstract double calculateFinalBalance();

}

class SavingsAccount extends BankAccount {

    private double interestRate;

    public SavingsAccount(int accountNumber, double balance, double interestRate) {

        super(accountNumber, balance);
        if (interestRate < 0.0 || interestRate > 20.0) {
            throw new IllegalArgumentException("Invalid interest rate");
        }
        this.interestRate = interestRate;
    }

    @Override
    public double calculateFinalBalance() {
        return getBalance() + (getBalance() * interestRate / 100);
    }
}

class CurrentAccount extends BankAccount {

    private double overDraftLimit;

    public CurrentAccount(int accountNumber, double balance, double overDraftLimit) {

        super(accountNumber, balance);
        if (overDraftLimit < 0.0 || overDraftLimit > 500000.0) {
            throw new IllegalArgumentException("Invalid overdraft limit");
        }

        this.overDraftLimit = overDraftLimit;
    }

    @Override
    public double calculateFinalBalance() {
        return getBalance() + overDraftLimit;
    }

}
