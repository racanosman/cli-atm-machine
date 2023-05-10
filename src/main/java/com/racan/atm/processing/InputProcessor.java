package com.racan.atm.processing;

import com.racan.atm.model.ATMMachine;
import com.racan.atm.model.Customer;
import com.racan.atm.service.ATMServiceImpl;
import com.racan.atm.service.CustomerServiceImpl;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.racan.atm.utils.Constants.*;
import static com.racan.atm.utils.Constants.PATTERN_BALANCE;
import static com.racan.atm.utils.Constants.PATTERN_WITHDRAW;

public class InputProcessor {

    private static Logger logger = Logger.getLogger(InputProcessor.class);

    ATMMachine atmMachine = new ATMMachine();
    ATMServiceImpl atmService = new ATMServiceImpl();
    CustomerServiceImpl customerService = new CustomerServiceImpl();

    Pattern accountNumberAndPinPattern = Pattern.compile(PATTERN_ACCOUNT_NUMBER_AND_PIN);
    Pattern balanceAndOverdraftPattern = Pattern.compile(PATTERN_BALANCE_AND_OVERDRAFT);
    Pattern withdrawAmountPattern = Pattern.compile(PATTERN_WITHDRAW);
    Pattern displayBalancePattern = Pattern.compile(PATTERN_BALANCE);

    /**
     * Reads and executes commands from input file
     *
     * @param file
     */
    public void readInput(File file) {
        try (Scanner scanner = new Scanner(file).useDelimiter(PATTERN_PARAGRAPH)) {
            int lineNumber = 0;
            while (scanner.hasNext()) {
                String input = scanner.next();
                if(lineNumber == 0) {
                    logger.debug("Setting ATM balance to " + input);
                    atmMachine.setTotalBalance(Integer.parseInt(input));
                } else {
                    String[] line = input.split("\n");
                    Customer customer = new Customer();
                    for(int i=0; i < line.length; i++) {
                        authenticateCustomer(accountNumberAndPinPattern.matcher(line[i]), customer);
                        getCustomerBalanceAndOverdraft(balanceAndOverdraftPattern.matcher(line[i]), customer);
                        displayCustomerBalance(displayBalancePattern.matcher(line[i]), customer);
                        withdrawFunds(withdrawAmountPattern.matcher(line[i]), customer);
                    }
                }
                lineNumber++;
            }
        } catch (FileNotFoundException fnfe) {
            logger.error("Input file not found: ", fnfe);
        }
    }

    /**
     * Checks the user's PIN and returns error message if authentication fails.
     *
     * @param matcher
     * @param customer
     */
    private void authenticateCustomer(Matcher matcher, Customer customer) {
        Matcher accountNumberAndPinMatcher = matcher;
        if(accountNumberAndPinMatcher.find()) {
            logger.debug("Account number and pin detected");
            customer.setAccountNumber(Integer.parseInt(accountNumberAndPinMatcher.group(1)));
            logger.debug("Customer account number is " + customer.getAccountNumber());
            customer.setPin(Integer.parseInt(accountNumberAndPinMatcher.group(2)));
            logger.debug("Customer pin number is " + customer.getPin());
            logger.debug("Customer entered pin number " + accountNumberAndPinMatcher.group(3));
            if(Boolean.TRUE.equals(customerService.authenticateCustomer(customer, Integer.parseInt(accountNumberAndPinMatcher.group(3))))) {
                logger.debug("Customer successfully authenticated.");
            } else {
                logger.error("Customer authentication failed. ACCOUNT_ERR");
                System.out.println(ERROR_AUTHENTICATION_FAILED);
            }
        }
    }

    /**
     * Sets the customers balance and overdraft.
     *
     * @param matcher
     * @param customer
     */
    private void getCustomerBalanceAndOverdraft(Matcher matcher, Customer customer) {
        Matcher balanceAndOverdraftMatcher = matcher;
        if(balanceAndOverdraftMatcher.find()) {
            logger.debug("Balance and overdraft detected");
            customer.setBalance(Integer.parseInt(balanceAndOverdraftMatcher.group(1)));
            logger.debug("Customer balance is " + customer.getBalance());
            customer.setOverdraft(Integer.parseInt(balanceAndOverdraftMatcher.group(2)));
            logger.debug("Customer overdraft is " + customer.getOverdraft());
        }
    }

    /**
     * Displays the customers balance to the console.
     *
     * @param matcher
     * @param customer
     */
    private void displayCustomerBalance(Matcher matcher, Customer customer) {
        Matcher displayBalanceMatcher = matcher;
        if(displayBalanceMatcher.find()) {
            logger.debug("Customer displayed balance");
            System.out.println(customerService.getBalance(customer));
        }
    }

    /**
     * Withdraws funds from the customers account and sets the new ATM balance.
     *
     * @param matcher
     * @param customer
     */
    private void withdrawFunds(Matcher matcher, Customer customer) {
        Matcher withDrawAmountMatcher = matcher;
        if(withDrawAmountMatcher.find()) {
            logger.debug("Customer withdraw amount " + withDrawAmountMatcher.group(1));
            int withdrawAmount = Integer.parseInt(withDrawAmountMatcher.group(1));
            if(atmService.withDrawFunds(atmMachine, withdrawAmount).equals("success")) {
                System.out.println(customerService.withDrawFunds(customer, withdrawAmount));
            }
        }
    }

}
