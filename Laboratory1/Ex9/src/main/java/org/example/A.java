package org.example;

import sun.misc.Signal;

public class A {
    public static void main(String[] args) {
        Signal.handle(new Signal("ALRM"), signal -> System.out.println("Alarma rebuda"));
        Signal.handle(new Signal("HUP"), signal -> System.out.println("Senyal HUP rebuda"));
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "target/classes", "org.example.B");
        try {
            pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}