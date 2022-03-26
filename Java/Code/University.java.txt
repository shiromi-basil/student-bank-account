package com.banking.system;

import com.banking.system.resources.Transaction;
import com.banking.system.utils.Utils;

/**
 * <h1>University</h1>
 * This class represents the student's university.
 *
 * <p> Student ID : 2018117 </p>
 * <p> UoW ID : w1714893 </p>
 *
 * @author Shiromi Thevarajan
 * @version 1.0
 * @since 2022-01-01
 */

public class University extends Thread {
    private final CurrentAccount currentAccount;
    private final String universityName;

    public University(ThreadGroup group, CurrentAccount currentAccount, String universityName) {
        super(group, universityName);
        this.currentAccount = currentAccount;
        this.universityName = universityName;
    }

    @Override
    public void run() {
        System.out.println("------------- University's Bank Transactions Started -------------");

        for (int i = 0; i < 3; i++) {
            Transaction courseFees = new Transaction(getName(), 40000);
            currentAccount.withdrawal(courseFees);
            System.out.println(Utils.dateAndTime + " {University}    - The withdrawal transaction was successful. " + courseFees);

            // Sleep for random amount of time between each transaction.
            try {
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                System.err.println(Utils.dateAndTime + " ERROR - The InterruptedException has been thrown while sleeping between " +
                        "each transaction. " + e.getMessage() + ".");
            }
        }

        System.out.println("------------- University's Bank Transactions Ended -------------");
    }

    public CurrentAccount getCurrentAccount() {
        return currentAccount;
    }

    public String getUniversityName() {
        return universityName;
    }
}
