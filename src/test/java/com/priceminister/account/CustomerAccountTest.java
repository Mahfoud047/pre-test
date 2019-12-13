package com.priceminister.account;


import static org.junit.Assert.*;

import org.junit.*;

import com.priceminister.account.implementation.*;
import org.junit.rules.ExpectedException;


/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, the develop the code that makes it pass.
 * Then focus on the second test, and so on.
 * <p>
 * We want to see how you "think code", and how you organize and structure a simple application.
 * <p>
 * When you are done, please zip the whole project (incl. source-code) and send it to recrutement-dev@priceminister.com
 */
public class CustomerAccountTest {

    private Account customerAccount;
    private AccountRule rule;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        customerAccount = new CustomerAccount();
        rule = new CustomerAccountRule();
    }

    /**
     * Tests that an empty account always has a balance of 0.0, not a NULL.
     */
    @Test
    public void testAccountWithoutMoneyHasZeroBalance() {
        final Double balance = customerAccount.getBalance();
        assertEquals((Double) 0.0, balance);
    }

    /**
     * Adds money to the account and checks that the new balance is as expected.
     */
    @Test
    public void testAddPositiveAmount() {

        final Double amount = 100.0;

        final Double balance = customerAccount.getBalance();

        final Double expectedBalance = balance + amount;

        customerAccount.add(amount);

        final Double balanceAfter = customerAccount.getBalance();

        assertEquals(expectedBalance, balanceAfter);
    }


    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    /**
     * Adds negative amount to the account and expect IllegalArgumentException.
     */
    @Test
    public void testAddNegativeAmountReportIllegalArgument() {

        //expect
        thrown.expect(IllegalArgumentException.class);

        final Double amount = -100.0;

        final Double balance = customerAccount.getBalance();

        //test
        customerAccount.add(amount);

        final Double balanceAfter = customerAccount.getBalance();

        assertEquals(balance, balanceAfter);
    }




    /**
     * Tests that an illegal withdrawal throws the expected exception.
     */
    @Test
    public void testWithdrawAndReportBalanceIllegalBalance() throws IllegalBalanceException {

        //expect
        thrown.expect(IllegalBalanceException.class);

        final Double amount = 900.0;

        //test
        customerAccount.withdrawAndReportBalance(amount, rule);

    }

    /**
     * Tests that a legal withdrawal works as expected
     */
    @Test
    public void testLegalWithdraw() throws IllegalBalanceException {

        final Double balanceBefore = customerAccount.getBalance();

        final Double amount = balanceBefore / 2;

        final Double expectedBalance = balanceBefore - amount;

        customerAccount.withdrawAndReportBalance(amount, rule); // withdraw balance / 2

        final Double balanceAfter = customerAccount.getBalance();

        // test case of expectedBalance > 0
        assertEquals(expectedBalance, balanceAfter);

        customerAccount.withdrawAndReportBalance(amount, rule); //  withdraw balance / 2

        // test case of expectedBalance == 0 (empty account)
        assertEquals(expectedBalance, balanceAfter);

    }

}
