package com.priceminister.account.implementation;

import com.priceminister.account.*;


public class CustomerAccount implements Account {

    private double balance = 0.0;

    public void add(Double addedAmount) throws IllegalArgumentException {

        if (addedAmount < 0.0)
            throw new IllegalArgumentException("illegal amount " + addedAmount.toString());

        balance += addedAmount;

    }

    public Double getBalance() {

        return balance;

    }

    public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule)
            throws IllegalBalanceException {

        final Double resultingAccountBalance = balance - withdrawnAmount;

        if (!rule.withdrawPermitted(resultingAccountBalance))
            throw new IllegalBalanceException(resultingAccountBalance);

        balance = resultingAccountBalance;

        return balance;
    }

}
