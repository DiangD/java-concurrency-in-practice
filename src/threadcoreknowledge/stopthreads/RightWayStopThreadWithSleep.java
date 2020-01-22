package threadcoreknowledge.stopthreads;

/**
 * @ClassName RightWayStopThreadWithSleep
 * @Author DiangD
 * @Date 2020/1/22
 * @Version 1.0
 * @Description run方法内有sleep或wait方法时，停止线程的方法
 **/
public class RightWayStopThreadWithSleep {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while (!Thread.currentThread().isInterrupted() && num <= 300) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}
