package threadcoreknowledge.sixstates;

/**
 * @ClassName BlockedWaitingTimedWaiting
 * @Author DiangD
 * @Date 2020/2/1
 * @Version 1.0
 * @Description 展示 Blocked Waiting TimedWaiting 三种状态
 **/
public class BlockedWaitingTimedWaiting implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getState());
        System.out.println(thread1.getName()+":"+thread1.getState());
        System.out.println(thread2.getName()+":"+thread2.getState());
        Thread.sleep(1300);
        System.out.println(thread1.getName()+":"+thread1.getState());
    }
    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
