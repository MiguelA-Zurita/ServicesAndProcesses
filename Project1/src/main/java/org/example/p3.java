package org.example;

import sun.misc.Signal;

import java.io.IOException;

public class p3 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hola, sóc el procés p3 amb PID: " + ProcessHandle.current().pid());

        Signal.handle(new Signal("ALRM"), sig -> {
            ProcessBuilder signalPb = new ProcessBuilder("kill", "-s","SIGALRM", args[0]);
            try {
                signalPb.start();
                System.exit(0);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });

        Signal.handle(new Signal("TERM"), sig -> {
            ProcessBuilder signalPb = new ProcessBuilder("kill", "-s","SIGTERM", args[0]);
            try {
                signalPb.start();
                System.exit(0);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });

        Signal.handle(new Signal("INT"), sig -> {
            ProcessBuilder signalPb = new ProcessBuilder("kill", "-s","SIGINT", args[0]);
            try {
                signalPb.start();
                System.exit(0);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });

        Signal.handle(new Signal("HUP"), sig -> {
            try {
                ProcessBuilder signalPb = new ProcessBuilder("kill", "-s","SIGHUP", args[0]);
                signalPb.start();
                System.exit(0);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });

        while (true){
            Thread.sleep(1000);
        }
    }
}