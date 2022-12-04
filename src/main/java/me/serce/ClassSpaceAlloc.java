package me.serce;

import java.lang.reflect.Proxy;
import java.util.ArrayList;

public class ClassSpaceAlloc {
    public static void main(String[] args) {
        var proxies = new ArrayList<Class<?>>();
        try {
            while (true) {
                //
                var cl = new ClassLoader(ClassSpaceAlloc.class.getClassLoader()) {};
                var proxy = Proxy.newProxyInstance( //
                        cl, //
                        new Class[]{Runnable.class}, //
                        (p, m, a) -> null);
                proxies.add(proxy.getClass());
                //Thread.sleep(1);
                if (proxies.size() % 10000 == 0) {
//                    System.out.println("allocated: " + proxies.size() + " classes");
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        System.out.println("Final num of classes: " + (proxies.size()) + " ");
    }
}
