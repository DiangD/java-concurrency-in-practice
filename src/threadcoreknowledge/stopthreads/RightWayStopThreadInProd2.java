package threadcoreknowledge.stopthreads;

/**
 * @ClassName RightWayStopThreadInProd
 * @Author DiangD
 * @Date 2020/1/22
 * @Version 1.0
 * @Description 最佳实践2：在catch子语句调用 Thread.currentThread().interrupt() 来恢复中断标记位
 **/
public class RightWayStopThreadInProd2 implements Runnable {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("go");
                reInterrupt();
        }
    }

    private void reInterrupt() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd2());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
