

package com.mycompany.banks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class BANKS {
     private static Map<String, BANKS> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private List<String> transactions;
    
    public  BANKS(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
        transactions.add("Account created with initial balance: R" + initialBalance);
    }

    public void Deposit(double amount){
        if(amount>0){
            balance += amount;
            transactions.add("Deposited: R"+ amount);
            System.out.println("Successfully deposited R"+ amount+".New balance: R"+balance);
            
        }else{
            System.out.println("Deposite amount must be positive");
        }
    }
    public void withdraw(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                transactions.add("Withdrew: R" + amount);
                System.out.println("Successfully withdrew R" + amount + ". New balance: R" + balance);
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }

    public void transfer(BANKS targetAccount, double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                targetAccount.Deposit(amount);
                transactions.add("Transferred: R" + amount + " to account " + targetAccount.getAccountNumber());
                System.out.println("Successfully transferred R" + amount + " to account " + targetAccount.getAccountNumber() + ". New balance: R" + balance);
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("Transfer amount must be positive.");
        }
    }
    public double getBalance(){
    return balance;
}
public List<String>getTransactions(){
    return transactions;
}
 public String getAccountNumber() {
        return accountNumber;
    }
   
    private static void createAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account number already exists.");
            return;
        }
        System.out.print("Enter account holder's name: ");
        String accountHolderName = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double initialBalance = Double.parseDouble(scanner.nextLine());
        BANKS account = new BANKS(accountNumber, accountHolderName, initialBalance);
        accounts.put(accountNumber, account);
        System.out.println("Account created for " + accountHolderName + " with balance R" + initialBalance);
    }

    private static BANKS findAccount(String accountNumber) {
        BANKS account = accounts.get(accountNumber);
        if (account == null) {
            System.out.println("Account not found.");
        }
        return account;
    }

    private static void Deposit() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        BANKS account = findAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = Double.parseDouble(scanner.nextLine());
            account.Deposit(amount);
        }
    }

    private static void Withdraw() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        BANKS account = findAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = Double.parseDouble(scanner.nextLine());
            account.withdraw(amount);
        }
    }

    private static void Transfer() {
        System.out.print("Enter source account number: ");
        String sourceAccountNumber = scanner.nextLine();
        System.out.print("Enter target account number: ");
        String targetAccountNumber = scanner.nextLine();
        BANKS sourceAccount = findAccount(sourceAccountNumber);
        BANKS targetAccount = findAccount(targetAccountNumber);
        if (sourceAccount != null && targetAccount != null) {
            System.out.print("Enter amount to transfer: ");
            double amount = Double.parseDouble(scanner.nextLine());
            sourceAccount.transfer(targetAccount, amount);
        }
    }

    private static void viewBalance() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        BANKS account = findAccount(accountNumber);
        if (account != null) {
            System.out.println("Current balance: $" + account.getBalance());
        }
    }

    private static void viewTransactions() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        BANKS account = findAccount(accountNumber);
        if (account != null) {
            System.out.println("Transaction history:");
            for (String transaction : account.getTransactions()) {
                System.out.println(transaction);
            }
        }
    }


    public static void main(String[] args) {
        while (true) {
            System.out.println("\nBanking System Menu:");
            System.out.println("1. Create new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. Transfer money");
            System.out.println("5. View balance");
            System.out.println("6. View transaction history");
            System.out.println("7. Exit");

            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    createAccount();
                    break;
                case "2":
                    Deposit();
                    break;
                case "3":
                    Withdraw();
                    break;
                case "4":
                    Transfer();
                    break;
                case "5":
                    viewBalance();
                    break;
                case "6":
                    viewTransactions();
                    break;
                case "7":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

}
    
