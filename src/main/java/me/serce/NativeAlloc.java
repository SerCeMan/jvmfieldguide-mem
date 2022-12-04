package me.serce;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class NativeAlloc {
    public static void main(String[] args) {
        var block = new ArrayList<ByteBuffer>();
        try {
            while (true) {
                int size = 1024 * 1024;
                var buf = ByteBuffer.allocateDirect(size);
                for (int i = 0; i < size; i++) {
                    buf.put(i, (byte) 0);
                }
                block.add(buf);
                Thread.sleep(1);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("Final size: " + (block.size()) + " mb");
    }
}
