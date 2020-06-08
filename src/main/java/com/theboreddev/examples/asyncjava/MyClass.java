package com.theboreddev.examples.asyncjava;

public class MyClass {
    private MyAppState myAppState;

    synchronized public void pleaseBlockMe() {
        // omitted code
        updateState();
        // omitted code
    }

    private void updateState() {
        //some expensive operation
    }

    static class MyAppState {
        // some state logic here
    }
}


