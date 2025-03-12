package org.example;

import tests.DemoWebShopTest;

public class Main {
    public static void main(String[] args) {

        var test = new DemoWebShopTest();

        test.setup();
        var result = test.run();
        if(result){
            System.out.println("Test passed");
        }
        else{
            System.out.println("Test failed");
        }
        test.teardown();
    }
}