package org.example;

import java.util.Scanner;

public class pD {
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       String userInput;
       userInput = sc.nextLine();
       System.out.println(userInput.toUpperCase());
       sc.close();
    }
}
