package org.example;

public class fill {
    public static void main(String[] args) throws Exception {
        long pid = ProcessHandle.current().pid();
        System.out.println("Fill PID: " + pid + " - Soc el fill i estic viu.");
        Thread.sleep(20000);
        System.out.println("Fill PID: " + pid + " - Soc el fill, he terminat la meva espera.");
    }
}

