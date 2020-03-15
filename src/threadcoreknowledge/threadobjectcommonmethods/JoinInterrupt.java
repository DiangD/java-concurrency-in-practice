package threadcoreknowledge.threadobjectcommonmethods;

import static java.lang.Thread.currentThread;

/**
 * @ClassName JoinIterrupt
 * @Author DiangD
 * @Date 2020/3/14
 * @Version 1.0
 * @Description 演示join期间被中断的效果
 **/
public class JoinInterrupt {
    public static void main(String[] args) {
        Thread mainThread = currentThread();
        Thread thread = new Thread(() -> {
            try {
                mainThread.interrupt();
                Thread.sleep(5000);
                System.out.println("子线程finish");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("子线程中断");
            }
        });
        thread.start();
        System.out.println("等待子线程执行完毕");
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println(currentThread().getName()+"主线程被中断");
            thread.interrupt();
        }
        System.out.println("子线程已运行完毕");
    }
}
