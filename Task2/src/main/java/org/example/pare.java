package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class pare {
    public static void main(String[] args) throws Exception {
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", ".", "fill");
        pb.redirectErrorStream(true);
        Process p = pb.start();

        long parentPid = ProcessHandle.current().pid();
        System.out.println("Pare PID: " + parentPid);

        Thread reader = new Thread(() -> {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        reader.start();

        boolean finished = p.waitFor(15, TimeUnit.SECONDS);
        if (!finished) {
            System.out.println("El fill no ha acabat en 15s. S'eliminarà forçosament.");
            p.destroyForcibly();
        } else {
            System.out.println("El fill ha acabat dins 15s.");
        }

        reader.join();
    }
}
