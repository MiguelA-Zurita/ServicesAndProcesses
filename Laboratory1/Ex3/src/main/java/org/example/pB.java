package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class pB {
    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println(ProcessHandle.current().pid());
        ProcessBuilder pb = new ProcessBuilder("java","-cp","target/classes","org.example.p2");
        pb.redirectErrorStream(true);
        Process p = pb.start();
        Thread reader = new Thread(() -> {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println("Sortida fill: " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        reader.start();
        Thread.sleep(10000);
    }
}
