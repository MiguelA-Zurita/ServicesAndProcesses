package org.example;

import sun.misc.Signal;

import java.io.OutputStreamWriter;

public class final1 {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "target/classes", "org.example.final2");
        pb.redirectErrorStream(true);

        try {
            Process p = pb.start();
            OutputStreamWriter osw = new OutputStreamWriter(p.getOutputStream());
            osw.write((int) p.pid() + "\n");
            osw.flush();
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Signal.handle(new Signal("INT"), sig -> System.out.println("Rebut senyal INT, sortint del programa"));
    }
}