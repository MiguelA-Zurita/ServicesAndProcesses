package org.example;

import sun.misc.Signal;
import sun.misc.SignalHandler;

public class receptor {
    public static void main(String[] args) throws InterruptedException {
        Signal.handle(new Signal("TERM"), sig -> {
            System.out.println("Senyal TERM rebut");
        });

        Signal.handle(new Signal("HUP"), sig -> {
            System.out.println("Senyal HUP rebut");
        });

        Signal.handle(new Signal("INT"), sig -> {
            System.out.println("Senyal INT rebut");
        });

        while(true) {
            Thread.sleep(5000);
        }
    }
}
