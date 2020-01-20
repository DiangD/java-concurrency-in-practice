package threadcoreknowledge.createthreads;

import sun.applet.Main;

/**
 * @ClassName BothRunnableThread
 * @Author DiangD
 * @Date 2020/1/20
 * @Version 1.0
 * @Description 同时使用runnable和Thread方法会发生什么
 **/
public class BothRunnableThread {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            //匿名内部类
            @Override
            public void run() {
                System.out.println("用runnable方式创建线程");
            }
        }) {
            //匿名类
            @Override
            public void run() {
                System.out.println("用Thread方式创建线程");
            }
        }.start();
    }
}
