package me.serce;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class StringInternator {
    public static void main(String[] args) throws InterruptedException {

        var strings = new ArrayList<String>();
        while (true) {
            byte[] b = new byte[1024 * 1024];
            ThreadLocalRandom.current().nextBytes(b);
            strings.add(new String(b, StandardCharsets.UTF_8).intern());
            Thread.sleep(1);
        }

    }
}
