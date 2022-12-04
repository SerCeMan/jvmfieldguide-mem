package me.serce;

import java.util.ArrayDeque;

public class LongRunningSafepointAlloc {


    private static final byte[] buf1 = new byte[512 * 1024 * 1024];
    private static final byte[] buf2 = new byte[512 * 1024 * 1024];

    public static void main(String[] args) {
        var t = new Thread(LongRunningSafepointAlloc::doBusyWork);
        t.setName("copying-thread");
        t.start();
        var blocks = new ArrayDeque<byte[]>();
        try {
            while (true) {
                blocks.add(new byte[1024 * 1024]);
                Thread.sleep(1);
                if (blocks.size() > 150) {
                    for (int i = 0; i < 25; i++) {
                        blocks.pop();
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("Final size: " + (blocks.size()) + " mb");
    }

    private static void doBusyWork() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            System.arraycopy(buf1, 0, buf2, 0, buf1.length);
            System.arraycopy(buf1, 0, buf2, 0, buf1.length);
            System.arraycopy(buf1, 0, buf2, 0, buf1.length);
            System.arraycopy(buf1, 0, buf2, 0, buf1.length);
        }
    }
}
