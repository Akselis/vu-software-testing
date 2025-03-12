package org.example;

import tests.DemoWebShopTest;

public class Main {
    public static void main(String[] args) {

        var test = new DemoWebShopTest();

        test.setup();
        test.run();
        test.teardown();
    }
}