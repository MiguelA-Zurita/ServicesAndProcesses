package org.example;

public class p {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(ProcessHandle.current().pid());
        Thread.sleep(10000);
    }
}
