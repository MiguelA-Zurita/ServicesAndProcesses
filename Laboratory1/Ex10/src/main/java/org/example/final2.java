package org.example;

import java.io.OutputStreamWriter;
import java.util.Scanner;

public class final2 {
    static public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pidPare = sc.nextLine();
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "target/classes", "org.example.final3");
        pb.redirectErrorStream(true);

        try {
            Process p = pb.start();
            OutputStreamWriter osw = new OutputStreamWriter(p.getOutputStream());
            osw.write(pidPare + "\n");
            osw.flush();
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
