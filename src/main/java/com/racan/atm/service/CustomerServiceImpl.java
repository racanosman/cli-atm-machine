package com.racan.atm.service;

import com.racan.atm.model.Customer;
import com.racan.atm.utils.Constants;
import org.apache.log4j.Logger;

public class CustomerServiceImpl implements CustomerService {

    private static Logger logger = Logger.getLogger(CustomerServiceImpl.class);

    /**
     * Authenticates the customer using their pin code.
     *
     * @param customer
     * @param pin
     * @return
     */
    public Boolean authenticateCustomer(Customer customer, int pin) {
        return customer.getPin() == pin;
    }

    /**
     * Returns the customers current balance.
     *
     * @param customer
     * @return
     */
    public int getBalance(Customer customer) {
        return customer.getBalance();
    }

    /**
     * Returns the customers current over draft.
     *
     * @param customer
     * @return
     */
    public int getOverDraft(Customer customer) {
        return customer.getOverdraft();
    }

    /**
     * Returns the customers account number.
     *
     * @param customer
     * @return
     */
    public int getAccountNumber(Customer customer) {
        return customer.getAccountNumber();
    }

    /**
     * Withdraws funds from the customers current balance.
     *
     * @param customer
     * @param amount
     * @return
     */
    public String withDrawFunds(Customer customer, int amount) {
        int balance = customer.getBalance() - amount;
        if(balance < 0) {
            logger.error("Customer out of funds: " + Constants.ERROR_CUSTOMER_OUT_OF_FUNDS);
            return Constants.ERROR_CUSTOMER_OUT_OF_FUNDS;
        } else {
            return Integer.toString(balance);
        }
    }

}
