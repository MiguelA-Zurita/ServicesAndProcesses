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
            System.out.print("Enter a signal (ALRM, TERM, INT o HUP): ");
            signal = sc.nextLine();
        } while (!(signal.equalsIgnoreCase("ALRM") || signal.equalsIgnoreCase("TERM") || signal.equalsIgnoreCase("INT") || signal.equalsIgnoreCase("HUP")));

        Signal.handle(new Signal("ALRM"), sig -> {
            System.out.println("Rebut SIGALRM, faig una breu alarma");
            try {
                Thread.sleep(3000);
                System.out.println("Fi de alarma");
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        });

        Signal.handle(new Signal("TERM"), sig -> {
            System.out.println("Rebut SIGTERM, tancant proces");
            System.exit(0);
        });

        Signal.handle(new Signal("INT"), sig -> {
            System.out.println("Rebut SIGINT");
            System.out.println("Interrupció detectada...");
            try {
                Thread.sleep(5000);
                System.exit(0);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Signal.handle(new Signal("HUP"), sig -> {
            System.out.println("Rebut SIGHUP, ignorant senyal...");
        });

        ProcessBuilder pb = new ProcessBuilder("java", "p2.java");
        pb.inheritIO();

        try {
            Process p = pb.start();
            Thread.sleep(5000);
            ProcessBuilder signalPb = new ProcessBuilder("kill", "-s", ("SIG" + signal.toUpperCase()), String.valueOf(p.pid()));
            signalPb.start();
            p.waitFor();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}