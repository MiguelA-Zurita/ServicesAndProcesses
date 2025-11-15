package org.example;


import sun.misc.Signal;
import sun.misc.SignalHandler;

public class captura {
    public static void main(String[] args) throws InterruptedException {
        Signal sig = new Signal("INT");
        SignalHandler handler = new SignalHandler() {
            public void handle(Signal sig) {
                System.out.println("He rebut SIGINT, No em pots aturar facilment!");
            }
        };
        Signal.handle(sig, handler);
        System.out.println("Programa iniciat!");
        Thread.sleep(10000);
    }
}