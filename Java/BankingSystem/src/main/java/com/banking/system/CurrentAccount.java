package com.banking.system;

import com.banking.system.resources.BankAccount;
import com.banking.system.resources.Statement;
import com.banking.system.resources.Transaction;
import com.banking.system.utils.Utils;

/**
 * <h1>Current Account</h1>
 * This class implements all the methods needed for the bank account.
 *
 * <p> Student ID : 2018117 </p>
 * <p> UoW ID : w1714893 </p>
 *
 * @author Shiromi Thevarajan
 * @version 1.0
 * @since 2022-01-01
 */

public class CurrentAccount implements BankAccount {

    private int balance;
    private final String accountHolder;
    private final int accountNumber;
    private final Statement statement;

    public CurrentAccount(String accountHolder, int accountNumber, int balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.statement = new Statement(accountHolder, accountNumber);
    }

    @Override
    public synchronized String getAccountHolder() {
        return accountHolder;
    }

    @Override
    public synchronized int getAccountNumber() {
        return accountNumber;
    }

    @Override
    public synchronized int getBalance() {
        return balance;
    }

    /**
     * The method for depositing money in to the current account.
     *
     * @param t This object contains details about the amount to be deposited.
     */
    @Override
    public synchronized void deposit(Transaction t) {
        if (t.getAmount() > 0) {
            this.balance += t.getAmount();
            this.statement.addTransaction(t.getCID(), t.getAmount(), this.balance);
            notifyAll();
        } else {
            System.err.println(Utils.dateAndTime + " ERROR - The deposit amount is invalid.");
        }
    }

    /**
     * The method for withdrawing money from the current account.
     *
     * @param t This object contains details about the amount to be withdrawn.
     */
    @Override
    public synchronized void withdrawal(Transaction t) {
        if (t.getAmount() > 0) {
            if (isOverdrawn()) {
                System.err.println(Utils.dateAndTime + " ERROR - The account number " + getAccountNumber() + " of " +
                        getAccountHolder() + "is overdrawn.");
            }

            // Waiting until the necessary funds become available to complete the transaction.
            while (this.balance < t.getAmount()) {
                try {
                    System.out.println(Utils.dateAndTime + " INFO - The account doesn't sufficient funds for the withdrawal. Waiting" +
                            " until the funds are available. " + t);
                    wait();
                } catch (InterruptedException e) {
                    System.err.println(Utils.dateAndTime + " ERROR - The InterruptedException has been thrown while waiting for the " +
                            "funds. " + e.getMessage() + ".");
                }
            }

            this.balance -= t.getAmount();
            this.statement.addTransaction(t.getCID(), t.getAmount(), this.balance);
            notifyAll();

        } else {
            System.err.println(Utils.dateAndTime + " ERROR - The withdrawal amount is invalid.");
        }
    }

    @Override
    public synchronized boolean isOverdrawn() {
        return this.balance < 0;
    }

    @Override
    public synchronized void printStatement() {
        this.statement.print();
    }
}
