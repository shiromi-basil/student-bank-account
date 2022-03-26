package com.banking.system;

import com.banking.system.resources.Transaction;
import com.banking.system.utils.Utils;

/**
 * <h1>Grandmother</h1>
 * This class represents the student's grandmother.
 *
 * <p> Student ID : 2018117 </p>
 * <p> UoW ID : w1714893 </p>
 *
 * @author Shiromi Thevarajan
 * @version 1.0
 * @since 2022-01-01
 */

public class Grandmother extends Thread {
    private final CurrentAccount currentAccount;
    private final String grandmotherName;

    public Grandmother(ThreadGroup group, CurrentAccount currentAccount,
                       String grandmotherName) {
        super(group, grandmotherName);
        this.currentAccount = currentAccount;
        this.grandmotherName = grandmotherName;
    }

    @Override
    public void run() {
        System.out.println("------------- Grandmother's Bank Transactions Started -------------");

        Transaction christmasGift = new Transaction(getName(), 5000);
        currentAccount.deposit(christmasGift);
        System.out.println(Utils.dateAndTime + " {Grandmother}   - The deposit transaction was successful.    " + christmasGift);

        // Sleep for random amount of time between each transaction.
        try {
            sleep((int) (Math.random() * 100));
        } catch (InterruptedException e) {
            System.err.println(Utils.dateAndTime + " ERROR - The InterruptedException has been thrown while sleeping between " +
                    "each transaction. " + e.getMessage() + ".");
        }

        Transaction birthdayGift = new Transaction(getName(), 10000);
        currentAccount.deposit(birthdayGift);
        System.out.println(Utils.dateAndTime + " {Grandmother}   - The deposit transaction was successful.    " + birthdayGift);

        System.out.println("------------- Grandmother's Bank Transactions Ended -------------");
    }

    public String getGrandmotherName() {
        return grandmotherName;
    }

    public CurrentAccount getCurrentAccount() {
        return currentAccount;
    }
}
