package org.example;

import java.io.IOException;
import java.util.Scanner;
import sun.misc.Signal;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String signal = "";
        System.out.println("PID actual: " + ProcessHandle.current().pid());
        do {
            System.out.print("Enter a signal (ALRM, TERM, INT, HUP o KILL): ");
            signal = sc.nextLine();
        } while (signal.equalsIgnoreCase("ALRM") || signal.equalsIgnoreCase("TERM") || signal.equalsIgnoreCase("IN") || signal.equalsIgnoreCase("HUP"));

        switch(signal) {
            case "ALRM":
                Signal.handle(new Signal("ALRM"), sig -> {
                    System.out.println("Rebut SIGALRM, faig una breu alarma");
                    try {
                        Thread.sleep(3000);
                        System.out.println("Fi de alarma");
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                });
                break;
            case "TERM":
                Signal.handle(new Signal("TERM"), sig -> {
                    System.out.println("Rebut SIGTERM, tancant proces");
                    System.exit(0);
                });
                break;
            case "INT":
                Signal.handle(new Signal("INT"), sig -> {
                    Signal.raise(sig);
                    System.out.println("Rebut SIGINT");
                    System.out.println("Interrupció detectada...");
                    try {
                        Thread.sleep(5000);
                        System.exit(0);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
                break;
            case "HUP":
                Signal.handle(new Signal("HUP"), sig -> {
                    System.out.println("Rebut SIGHUP, ignorant senyal...");
                });
                break;
            case "KILL":
                System.out.println("No se puede capturar la señal KILL");
                break;
        }
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", ".", "org.example.p2");
        pb.inheritIO();
        try {
            Process p = pb.start();
            // Give p2 time to set up its signal handlers
            Thread.sleep(1000);
            // Send the actual OS signal based on the signal variable
            ProcessBuilder signalPb = new ProcessBuilder("kill", "-" + signal, String.valueOf(p.pid()));
            signalPb.start();
            p.waitFor();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}