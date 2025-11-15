package org.example;

import java.util.Scanner;

public class pG {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String paraula = sc.nextLine();
        String paraulaInvertida = new StringBuilder(paraula).reverse().toString();
        System.out.println(paraulaInvertida);
    }
}
