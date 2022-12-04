package me.serce;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicLong;

public class LongRunningAlloc {
    public static final AtomicLong A = new AtomicLong(1);

    public static void main(String[] args) {
        var bigBocks = new ArrayDeque<byte[]>();
        var smallBlocks = new ArrayDeque<char[]>();
        var tinyBlocks = new ArrayDeque<short[]>();
        try {
            while (true) {
                runAllocHeavy(bigBocks, smallBlocks, tinyBlocks);
                runCompute();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("done");
    }

    private static void runAllocHeavy(ArrayDeque<byte[]> bigBocks, ArrayDeque<char[]> smallBlocks, ArrayDeque<short[]> tinyBlocks) throws InterruptedException {
        allocBigBlocks(bigBocks);
        allocSmallBlocks0(smallBlocks);
        allocTinyBlocks0(tinyBlocks);
    }

    private static void allocTinyBlocks0(ArrayDeque<short[]> tinyBlocks) throws InterruptedException {
        allocTinyBlocks1(tinyBlocks);
    }

    private static void allocTinyBlocks1(ArrayDeque<short[]> tinyBlocks) throws InterruptedException {
        allocTinyBlocks(tinyBlocks);
    }

    private static void allocSmallBlocks0(ArrayDeque<char[]> smallBlocks) throws InterruptedException {
        allocSmallBlocks(smallBlocks);
    }

    private static void allocTinyBlocks(ArrayDeque<short[]> tinyBlocks) throws InterruptedException {
        tinyBlocks.add(new short[1024]);
        runAlloc(tinyBlocks);
    }

    private static void allocSmallBlocks(ArrayDeque<char[]> smallBlocks) throws InterruptedException {
        smallBlocks.add(new char[10 * 1024]);
        runAlloc(smallBlocks);
    }

    private static void allocBigBlocks(ArrayDeque<byte[]> bigBocks) throws InterruptedException {
        bigBocks.add(new byte[1024 * 1024]);
        runAlloc(bigBocks);
    }

    private static void runCompute() {
        for (int i = 0; i < 20_000; i++) {
            A.addAndGet(i);
        }
    }

    private static void runAlloc(ArrayDeque<?> blocks) throws InterruptedException {
        Thread.sleep(1);
        if (blocks.size() > 150) {
            for (int i = 0; i < 25; i++) {
                blocks.pop();
            }
        }
    }

}
