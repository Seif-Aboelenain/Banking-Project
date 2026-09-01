package com.bank.bankproj.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity

public class Account {
    @Id
    @GeneratedValue
    private int id;
    private int accnum;
    private double balance;
    @ManyToOne
    private Customer customer;



    public Account(){

    }
    public Account(int accnum,double balance){
        this.accnum=accnum;
        this.balance=balance;
    }

    public int getId() {
        return id;
    }

    public int getAccnum() {
        return accnum;
    }

    public double getBalance() {
        return balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccnum(int accnum) {
        this.accnum = accnum;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public void deposit(double amount){
        balance+=amount;
    }
    public void withdraw(double amount){
        balance-=amount;
    }
}
