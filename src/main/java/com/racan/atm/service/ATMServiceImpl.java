package com.racan.atm.service;

import com.racan.atm.model.ATMMachine;
import org.apache.log4j.Logger;

import static com.racan.atm.utils.Constants.ERROR_ATM_OUT_OF_FUNDS;

public class ATMServiceImpl implements ATMService {

    private static Logger logger = Logger.getLogger(ATMServiceImpl.class);

    /**
     * Gets the ATM current balance.
     *
     * @param atmMachine
     * @return
     */
    public int getATMTotalBalance(ATMMachine atmMachine) {
        return atmMachine.getTotalBalance();
    }

    /**
     * Withdraws funds from the ATM.
     *
     * @param atmMachine
     * @param amount
     * @return
     */
    public String withDrawFunds(ATMMachine atmMachine, int amount) {
        int atmFunds = atmMachine.getTotalBalance() - amount;
        if(atmFunds <= 0) {
            System.out.println(ERROR_ATM_OUT_OF_FUNDS);
            logger.error("ATM out of funds: " + ERROR_ATM_OUT_OF_FUNDS);
            return ERROR_ATM_OUT_OF_FUNDS;
        } else {
            atmMachine.setTotalBalance(atmFunds);
            return "success";
        }
    }
}
