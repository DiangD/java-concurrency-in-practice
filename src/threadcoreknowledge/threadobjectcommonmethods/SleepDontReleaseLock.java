package threadcoreknowledge.threadobjectcommonmethods;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName SleepDontReleaseLock
 * @Author DiangD
 * @Date 2020/3/13
 * @Version 1.0
 * @Description 展示线程sleep的时候不释放lock，等sleep时间到了以后，正常结束后才释放锁
 **/
public class SleepDontReleaseLock implements Runnable {
    public static final Lock lock  = new ReentrantLock();
    @Override
    public void run() {
        lock.lock();
        System.out.println("线程"+Thread.currentThread().getName()+"获取到了锁");
        try {
            Thread.sleep(5000);
            System.out.println("线程" + Thread.currentThread().getName() + "已经苏醒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SleepDontReleaseLock runnable = new SleepDontReleaseLock();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
