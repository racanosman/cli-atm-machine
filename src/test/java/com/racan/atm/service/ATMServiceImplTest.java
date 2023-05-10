package com.racan.atm.service;

import com.racan.atm.model.ATMMachine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.racan.atm.utils.Constants.ERROR_ATM_OUT_OF_FUNDS;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ATMServiceImplTest {

    @InjectMocks
    private ATMServiceImpl atmService;

    @Mock
    ATMMachine atmMachine;

    @Test
    public void testGetATMTotalBalance() {
        when(atmMachine.getTotalBalance()).thenReturn(5000);
        assertEquals(5000, atmService.getATMTotalBalance(atmMachine));
    }

    @Test
    public void testSuccessfulWithdrawFunds() {
        when(atmMachine.getTotalBalance()).thenReturn(100);
        assertEquals("success", atmService.withDrawFunds(atmMachine, 50) );
    }

    @Test
    public void testATMOutOfFundsError() {
        when(atmMachine.getTotalBalance()).thenReturn(100);
        assertEquals(ERROR_ATM_OUT_OF_FUNDS, atmService.withDrawFunds(atmMachine, 200));
    }

}
