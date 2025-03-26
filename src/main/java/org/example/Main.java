package org.example;

import tests.DemoQaTestTwo;

public class Main {
    public static void main(String[] args) {

        var test = new DemoQaTestTwo();

        test.setup();
        var result = test.run();
        if(result){
            System.out.println("Test passed");
        }
        else{
            System.out.println("Test failed");
        }
    }
}