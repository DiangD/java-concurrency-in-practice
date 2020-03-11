package threadcoreknowledge.threadobjectcommonmethods;

import java.util.concurrent.locks.Condition;

/**
 * @ClassName WaitNotifyReleaseOwnMonitor
 * @Author DiangD
 * @Date 2020/3/11
 * @Version 1.0
 * @Description wait只释放当前那把锁
 **/
public class WaitNotifyReleaseOwnMonitor {
    private static final Object resourceA = new Object();
    private static final Object resourceB = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
           synchronized (resourceA) {
                System.out.println("ThreadA got resourceA lock");
                synchronized (resourceB) {
                    System.out.println("ThreadA got resourceB lock");
                    try {
                        System.out.println("ThreadA releases resourceA lock");
                        resourceA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        Thread threadB = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (resourceA) {
                System.out.println("ThreadB got resourceA lock");
                System.out.println("ThreadB tries to get resourceB lock");
                synchronized (resourceB) {
                    System.out.println("ThreadB got resourceB lock");
                }
            }
        });
        threadA.start();
        threadB.start();
    }
}
