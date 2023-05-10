package com.racan.atm;

import com.racan.atm.processing.InputProcessor;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        File file = new File("input.txt");
        InputProcessor inputProcessor = new InputProcessor();
        inputProcessor.readInput(file);
    }

}