package com.racan.atm.service;

import com.racan.atm.model.ATMMachine;

public interface ATMService {

    int getATMTotalBalance(ATMMachine atmMachine);
    String withDrawFunds(ATMMachine atmMachine, int amount);

}
