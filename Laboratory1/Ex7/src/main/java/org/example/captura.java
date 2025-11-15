package org.example;


import sun.misc.Signal;
import sun.misc.SignalHandler;

public class captura {
    public static void main(String[] args) throws InterruptedException {
        Signal.handle(new Signal("INT"), sig -> System.out.println("He rebut SIGINT, no em pots aturar tan facilment!"));
        Signal.handle(new Signal("ALRM"), sig -> System.out.println("He rebut una alarma"));
        Signal.handle(new Signal("TERM"), sig -> {
            System.out.println("Procés finalitzat");
            System.exit(0);
        });
        Signal.handle(new Signal("HUP"), sig -> System.out.println("Senyals ignorada"));
        System.out.println("Programa iniciat!");
        Thread.sleep(10000);
    }
}