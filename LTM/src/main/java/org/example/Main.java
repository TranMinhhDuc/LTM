package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String s = "1,2,9,10,8,5,6";
        String[] str = s.split(",");
        for (String string : str) {
            System.out.print(string + " ");
        }
        System.out.println();
        Arrays.sort(str, (a, b) -> {
            return Integer.compare(Integer.parseInt(a), Integer.parseInt(b));
        } );
        for (String string : str) {
            System.out.print(string + " ");
        }
    }
}