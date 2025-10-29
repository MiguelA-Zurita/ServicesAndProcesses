package org.example;

import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p2 {
    public static void main(String[] args) throws IOException {
        System.out.println("Hola, sóc el procés p2 amb PID: " + ProcessHandle.current().pid());
        System.out.println("PID del meu pare: " + ProcessHandle.current().parent().map(ProcessHandle::pid).orElse(null));
        ProcessBuilder pb = new ProcessBuilder("java", "p3.java", String.valueOf(ProcessHandle.current().parent().map(ProcessHandle::pid).orElse(null)));
        pb.inheritIO();
        Process p = pb.start();

        Signal.handle(new Signal("ALRM"), sig -> {
            ProcessBuilder signalPb = new ProcessBuilder("kill", "-s","SIGALRM", String.valueOf(p.pid()));
            try {
                signalPb.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Signal.handle(new Signal("TERM"), sig -> {
            ProcessBuilder signalPb = new ProcessBuilder("kill", "-s", "SIGTERM", String.valueOf(p.pid()));
            try {
                signalPb.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Signal.handle(new Signal("INT"), sig -> {
            ProcessBuilder signalPb = new ProcessBuilder("kill", "-s","SIGINT", String.valueOf(p.pid()));
            try {
                signalPb.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Signal.handle(new Signal("HUP"), sig -> {
            ProcessBuilder signalPb = new ProcessBuilder("kill", "-s", "SIGHUP", String.valueOf(p.pid()));
            try {
                signalPb.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        try {
            p.waitFor();
            ProcessBuilder signalPb = new ProcessBuilder("kill", "-s", "SIGHUP", String.valueOf(ProcessHandle.current().parent().map(ProcessHandle::pid).orElse(null)));
            signalPb.start();
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }



    }
}
