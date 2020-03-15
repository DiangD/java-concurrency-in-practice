package threadcoreknowledge.threadobjectcommonmethods;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SleepInterrupted
 * @Author DiangD
 * @Date 2020/3/13
 * @Version 1.0
 * @Description
 **/
public class SleepInterrupted implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        SleepInterrupted runnable = new SleepInterrupted();
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(new Date());
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            System.out.println("我被中断了");
            e.printStackTrace();
        }
    }
}
