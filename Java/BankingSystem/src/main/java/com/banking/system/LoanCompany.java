package com.banking.system;

import com.banking.system.resources.Transaction;
import com.banking.system.utils.Utils;

/**
 * <h1>Loan Company</h1>
 * This class represents the student's loan company.
 *
 * <p> Student ID : 2018117 </p>
 * <p> UoW ID : w1714893 </p>
 *
 * @author Shiromi Thevarajan
 * @version 1.0
 * @since 2022-01-01
 */

public class LoanCompany extends Thread {
    private final CurrentAccount currentAccount;
    private final String loanCompanyName;

    public LoanCompany(ThreadGroup group, CurrentAccount currentAccount, String loanCompanyName) {
        super(group, loanCompanyName);
        this.currentAccount = currentAccount;
        this.loanCompanyName = loanCompanyName;
    }

    @Override
    public void run() {
        System.out.println("------------- Loan Company's Bank Transactions Started -------------");

        for (int i = 0; i < 3; i++) {
            Transaction loanDeposit = new Transaction(getName(), 50000);
            currentAccount.deposit(loanDeposit);
            System.out.println(Utils.dateAndTime + " {Loan Company}  - The deposit transaction was successful.    " + loanDeposit);

            // Sleep for random amount of time between each transaction.
            try {
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                System.err.println(Utils.dateAndTime + " ERROR - The InterruptedException has been thrown while sleeping between " +
                        "each transaction. " + e.getMessage() + ".");
            }
        }

        System.out.println("------------- Loan Company's Bank Transactions Ended -------------");
    }

    public String getLoanCompanyName() {
        return loanCompanyName;
    }

    public CurrentAccount getCurrentAccount() {
        return currentAccount;
    }
}
