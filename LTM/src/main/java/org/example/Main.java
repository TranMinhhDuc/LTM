package org.example;
public class Main {
    public static void main(String[] args) {
        String s = "Hello, world";
        String[] str = s.split("\\s+");
        for (String i : str) {
            System.out.println(i);
        }
    }
}