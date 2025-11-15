package org.example;

import java.util.Scanner;

public class final3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pid = sc.nextLine();
        ProcessBuilder pb = new ProcessBuilder("Kill", "-INT", pid);
    }

}
