package threadcoreknowledge.threadobjectcommonmethods;

/**
 * @ClassName WaitNotifyprintOddEvenWait
 * @Author DiangD
 * @Date 2020/3/12
 * @Version 1.0
 * @Description 交替打印0~100的奇数偶数 用wait notify
 **/
public class WaitNotifyPrintOddEvenWait {
    private static int count = 0;
    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        TurningRunner turningRunner = new TurningRunner();
        new Thread(turningRunner, "偶数").start();
        Thread.sleep(100);
        new Thread(turningRunner, "奇数").start();

    }

    static class TurningRunner implements Runnable {
        /**
         * 拿到锁就打印
         * 打印后就释放锁，唤醒其他线程，自己休眠
         */
        @Override
        public void run() {
            while (count <= 100) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + ":" + count++);
                    lock.notify();
                    if (count <= 100) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
