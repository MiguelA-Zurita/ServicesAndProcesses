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
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", ".", "p3");
        pb.inheritIO();
        Process p = pb.start();
        Signal.handle(new Signal("ALRM"), sig -> {
        });
        Signal.handle(new Signal("TERM"), sig -> {
            System.out.println("Rebut SIGTERM, tancant proces");
        });
        Signal.handle(new Signal("INT"), sig -> {
        });
        Signal.handle(new Signal("HUP"), sig -> {
            System.out.println("Rebut SIGHUP, ignorant senyal...");
        });

    }
}
