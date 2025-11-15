package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class pF {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "target/classes", "org.example.pG");
        pb.redirectErrorStream(true);
        Process p = pb.start();

        OutputStreamWriter osw = new OutputStreamWriter(p.getOutputStream());
        osw.write(sc.nextLine() + "\n");
        osw.flush();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.close();
    }
}
