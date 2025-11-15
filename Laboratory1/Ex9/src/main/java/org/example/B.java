package org.example;

public class B {
    public static void main(String[] args) throws InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("kill", "-SIGALRM", String.valueOf(ProcessHandle.current().pid()));
        try {
            pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread.sleep(2000);
        ProcessBuilder pb2 = new ProcessBuilder("kill", "-SIGHUP", String.valueOf(ProcessHandle.current().pid()));
        try {
            pb2.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
