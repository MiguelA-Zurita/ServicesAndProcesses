package org.example;

public class p2 {
    public static void main(String[] args) {
        System.out.println("PID fill:" + ProcessHandle.current().pid());
        System.out.println("PID Pare:" + ProcessHandle.current().parent().get().pid());
    }
}
