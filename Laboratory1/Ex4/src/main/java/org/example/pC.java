package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class pC {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Inserta un text:");
        String userInput = sc.next();

        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "target/classes", "org.example.pD");
        pb.redirectErrorStream(true);
        Process p = pb.start();

        OutputStreamWriter osw = new OutputStreamWriter(p.getOutputStream());
        osw.write(userInput + "\n");
        osw.flush();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
