package com.banking.system;

import com.banking.system.resources.Transaction;
import com.banking.system.utils.Utils;

/**
 * <h1>Student</h1>
 * This class represents the student.
 *
 * <p> Student ID : 2018117 </p>
 * <p> UoW ID : w1714893 </p>
 *
 * @author Shiromi Thevarajan
 * @version 1.0
 * @since 2022-01-01
 */

public class Student extends Thread {
    private final CurrentAccount currentAccount;
    private final String studentName;

    public Student(ThreadGroup group, CurrentAccount currentAccount, String studentName) {
        super(group, studentName);
        this.currentAccount = currentAccount;
        this.studentName = studentName;
    }

    @Override
    public void run() {
        System.out.println("------------- Student's Bank Transactions Started -------------");

        Transaction winLottery = new Transaction(getName(), 80000);
        currentAccount.deposit(winLottery);
        System.out.println(Utils.dateAndTime + " {Student}       - The deposit transaction was successful.    " + winLottery);
        sleep();

        Transaction buySamsungPhone = new Transaction(getName(), 25000);
        currentAccount.withdrawal(buySamsungPhone);
        System.out.println(Utils.dateAndTime + " {Student}       - The withdrawal transaction was successful. " + buySamsungPhone);
        sleep();

        Transaction payRent = new Transaction(getName(), 15000);
        currentAccount.withdrawal(payRent);
        System.out.println(Utils.dateAndTime + " {Student}       - The withdrawal transaction was successful. " + payRent);
        sleep();

        Transaction payGroceries = new Transaction(getName(), 5000);
        currentAccount.withdrawal(payGroceries);
        System.out.println(Utils.dateAndTime + " {Student}       - The withdrawal transaction was successful. " + payGroceries);
        sleep();

        Transaction buyClothes = new Transaction(getName(), 5000);
        currentAccount.withdrawal(buyClothes);
        System.out.println(Utils.dateAndTime + " {Student}       - The withdrawal transaction was successful. " + buyClothes);
        sleep();

        Transaction buyBooks = new Transaction(getName(), 2000);
        currentAccount.withdrawal(buyBooks);
        System.out.println(Utils.dateAndTime + " {Student}       - The withdrawal transaction was successful. " + buyBooks);
        sleep();

        System.out.println("------------- Student's Bank Transactions Ended -------------");

        // Prints the final bank statement after completing the transactions of the student.
        currentAccount.printStatement();
    }

    /**
     * The method for sleeping for random amount of time between each transaction.
     */
    public void sleep() {
        try {
            sleep((int) (Math.random() * 100));
        } catch (InterruptedException e) {
            System.err.println(Utils.dateAndTime + " ERROR - The InterruptedException has been thrown while sleeping between " +
                    "each transaction. " + e.getMessage() + ".");
        }
    }

    public CurrentAccount getCurrentAccount() {
        return currentAccount;
    }

    public String getStudentName() {
        return studentName;
    }
}
