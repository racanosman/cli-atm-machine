package com.racan.atm.service;

import com.racan.atm.model.Customer;

public interface CustomerService {

    Boolean authenticateCustomer(Customer customer, int pin);
    int getBalance(Customer customer);
    String withDrawFunds(Customer customer, int amount);
    int getOverDraft(Customer customer);
    int getAccountNumber(Customer customer);

}
