package com.driver;

import java.util.Random;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;


    BankAccount(){

    }

    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;

    }
    public double getBalance(){
        return this.balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception

        if(sum < 0 || sum > 9*digits){
          throw new InsufficientBalanceException("Account Number can not be generated");
        }

        Random rand = new Random();
        StringBuilder accNum = new StringBuilder();

        for(int i=0;i<digits;i++){
            int maxdigit = Math.min(9, sum);
            int digit = rand.nextInt(maxdigit+1);

            accNum = accNum.append(digit);
            sum -= digit;
        }



        return accNum.toString();
    }

    public void deposit(double amount) {
        //add amount to balance
        this.balance = this.balance+amount;

    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
            double remainingBalance = this.balance - amount;
        if (remainingBalance < minBalance){
            throw new InsufficientBalanceException("Insufficient Balance");
        }
        
        this.balance = remainingBalance;
        

    }

}