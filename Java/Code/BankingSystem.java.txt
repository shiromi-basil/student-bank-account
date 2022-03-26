package com.banking.system;

import com.banking.system.utils.Utils;

/**
 * <h1>Banking System</h1>
 * This class represents the complete banking system.
 *
 * <p> Student ID : 2018117 </p>
 * <p> UoW ID : w1714893 </p>
 *
 * @author Shiromi Thevarajan
 * @version 1.0
 * @since 2022-01-01
 */

public class BankingSystem {
    CurrentAccount currentAccount;
    // Thread group for humans
    ThreadGroup humans;
    // Thread group for the Loan Company & University
    ThreadGroup organizations;
    Student student;
    Grandmother grandmother;
    LoanCompany loanCompany;
    University university;

    public BankingSystem() {
        currentAccount = new CurrentAccount("Shiromi Theverajan", 5614125, 5000);

        humans = new ThreadGroup("Humans");
        organizations = new ThreadGroup("Organizations");
        System.out.println(Utils.dateAndTime + " INFO - Humans and Organizations thread groups were created.");

        student = new Student(humans, currentAccount, currentAccount.getAccountHolder());
        grandmother = new Grandmother(humans, currentAccount, "Rajeswary Philip");
        loanCompany = new LoanCompany(organizations, currentAccount, "People's Bank");
        university = new University(organizations, currentAccount, "IIT");
        System.out.println(Utils.dateAndTime + " INFO - Student, Grandmother, Loan Company and University threads were created.");
    }

    public static void main(String[] args) {

        System.out.println("=========================================================");
        System.out.println("------------- Welcome to The Banking System -------------");
        System.out.println("=========================================================\n");

        BankingSystem bankingSystem = new BankingSystem();

        System.out.println(Utils.dateAndTime + " INFO - Starting Student, Grandmother, Loan Company and University threads.\n");
        bankingSystem.student.start();
        bankingSystem.grandmother.start();
        bankingSystem.loanCompany.start();
        bankingSystem.university.start();

        try {
            // Waiting until all the threads are executed.
            bankingSystem.student.join();
            bankingSystem.grandmother.join();
            bankingSystem.loanCompany.join();
            bankingSystem.university.join();

        } catch (InterruptedException e) {
            System.err.println(Utils.dateAndTime + " ERROR - The InterruptedException has been thrown while joining the threads. " +
                    e.getMessage() + ".");
        }

        System.out.println(Utils.dateAndTime + " INFO - Student, Grandmother, Loan Company and University threads complete their " +
                "execution.");

        // Prints out the final statement of the student's bank account.
        bankingSystem.currentAccount.printStatement();

        System.out.println("================================================");
        System.out.println("------------------ Thank You -------------------");
        System.out.println("================================================");
    }
}
