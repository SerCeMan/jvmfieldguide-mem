package me.serce.nativelib;

public class NativeLib {

    static {
        System.load("/root/proj/src/main/java/me/serce/nativelib/libexnativee.so");
    }

    public static void main(String[] args) throws Exception {
        while (true) {
            goodNative(10 * 1024);
            badNative(10 * 1024);
            Thread.sleep(1);
        }
    }

    private static native void goodNative(int size);
    private static native void badNative(int size);
}
