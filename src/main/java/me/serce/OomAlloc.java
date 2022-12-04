package me.serce;

import java.util.ArrayList;

public class OomAlloc {
    public static void main(String[] args) {
        var blocks = new ArrayList<byte[]>();
        try {
            while (true) {
                for (int i = 0; i < 1024; i++) {
                    blocks.add(new byte[1024]);
                }
                Thread.sleep(1);
            }
        } catch (Throwable e) {
            int size = blocks.size();
            blocks = null;
            System.out.println("Final size: " + (size / 1000) + " mb, and " + (size % 1000) + " kb");
            e.printStackTrace();
        }
    }
}
