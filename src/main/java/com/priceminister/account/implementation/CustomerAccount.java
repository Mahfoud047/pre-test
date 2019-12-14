package com.priceminister.account.implementation;

import com.priceminister.account.*;


public class CustomerAccount implements Account {

    private double balance;

    public CustomerAccount() {
        this.balance = 0.0;
    }

    public CustomerAccount(Double initBalance) {
        this.balance = initBalance;
    }


    public void add(Double addedAmount) throws IllegalArgumentException {

        if (addedAmount < 0.0) {
            throw new IllegalArgumentException("illegal amount " + addedAmount.toString());
        }

        this.balance += addedAmount;

    }

    public Double getBalance() {

        return this.balance;

    }

    public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule)
            throws IllegalBalanceException {

        final Double resultingAccountBalance = this.balance - withdrawnAmount;

        if (!rule.withdrawPermitted(resultingAccountBalance)) {
            throw new IllegalBalanceException(resultingAccountBalance);
        }

        this.balance = resultingAccountBalance;

        return this.balance;
    }

}
