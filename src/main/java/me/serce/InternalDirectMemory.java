package me.serce;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class InternalDirectMemory {
    public static void main(String[] args) {
        var block = new ArrayList<ByteBuffer>();
        try {
            // 1Mb per second
            while (true) {
                block.add(ByteBuffer.allocateDirect(1024));
                Thread.sleep(1);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("done");
    }
}
