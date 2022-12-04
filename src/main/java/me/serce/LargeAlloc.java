package me.serce;

import java.util.ArrayList;
import java.util.Arrays;

public class LargeAlloc {
    public static void main(String[] args) {
        var block = new ArrayList<byte[]>();
        try {
            while (true) {
                byte[] e = new byte[1024 * 1024];
                block.add(e);
                Thread.sleep(1);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("Final size: " + (block.size()) + " mb");
    }
}
