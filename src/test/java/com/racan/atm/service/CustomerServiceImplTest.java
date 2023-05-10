package com.racan.atm.service;

import com.racan.atm.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.racan.atm.utils.Constants.ERROR_CUSTOMER_OUT_OF_FUNDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    Customer customer;

    @Test
    public void testSuccessfulCustomerAuthentication() {
        when(customer.getPin()).thenReturn(1234);
        assertTrue(customerService.authenticateCustomer(customer, 1234));
    }

    @Test
    public void testFailedCustomerAuthentication() {
        when(customer.getPin()).thenReturn(5678);
        assertFalse(customerService.authenticateCustomer(customer, 1234));
    }

    @Test
    public void testSuccessfulWithdrawFunds() {
        when(customer.getBalance()).thenReturn(100);
        assertEquals("75", customerService.withDrawFunds(customer, 25) );
    }

    @Test
    public void testFailedWithdrawFunds() {
        when(customer.getBalance()).thenReturn(100);
        assertEquals(ERROR_CUSTOMER_OUT_OF_FUNDS, customerService.withDrawFunds(customer, 200));
    }

    @Test
    public void testGetCustomerBalance() {
        when(customer.getBalance()).thenReturn(1000);
        assertEquals(1000, customerService.getBalance(customer));
    }

    @Test
    public void testGetCustomerOverDraft() {
        when(customer.getOverdraft()).thenReturn(100);
        assertEquals(100, customerService.getOverDraft(customer));
    }

    @Test
    public void testGetCustomerAccountNumber() {
        when(customer.getAccountNumber()).thenReturn(123456789);
        assertEquals(123456789, customerService.getAccountNumber(customer));
    }

}
