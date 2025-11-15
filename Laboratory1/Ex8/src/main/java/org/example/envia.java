package org.example;

import java.util.Scanner;

public class envia {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix un PID: "); String pid = sc.nextLine();
        System.out.println("Introdueix una senyal: "); String signal = sc.nextLine();
        ProcessBuilder pb = new ProcessBuilder("kill", "-"+signal, pid);
        try {
            pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}