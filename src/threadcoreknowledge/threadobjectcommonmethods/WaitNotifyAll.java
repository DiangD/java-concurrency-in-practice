package threadcoreknowledge.threadobjectcommonmethods;

/**
 * @ClassName WaitNotifyAll
 * @Author DiangD
 * @Date 2020/3/11
 * @Version 1.0
 * @Description 3个线程 线程1 线程2首先被阻塞 线程3唤醒他们。
 * notify notifyAll
 * start先执行不代表线程先启动
 **/
public class WaitNotifyAll implements Runnable {
    public static final Object resourceA = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new WaitNotifyAll();
        Thread threadA = new Thread(runnable);
        Thread threadB = new Thread(runnable);
        Thread threadC = new Thread(() -> {
            synchronized (resourceA) {
                resourceA.notifyAll();
                resourceA.notify();
                System.out.println(Thread.currentThread().getName() + "notified");
            }
        });
        threadA.start();
        threadB.start();
        Thread.sleep(200);
        threadC.start();
    }

    @Override
    public void run() {
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + "got resourceA lock");
            try {
                System.out.println(Thread.currentThread().getName() + "waits to start");
                resourceA.wait();
                System.out.println(Thread.currentThread().getName() + "is waiting to end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
