package threadcoreknowledge.createthreads.wrongways;

/**
 * @ClassName AnonymousInnerClassDemo
 * @Author DiangD
 * @Date 2020/1/20
 * @Version 1.0
 * @Description
 **/
public class AnonymousInnerClassDemo {
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }.start();

        new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();

    }
}
