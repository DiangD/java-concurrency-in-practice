package threadcoreknowledge.stopthreads;

/**
 * @ClassName CantStopThread
 * @Author DiangD
 * @Date 2020/1/22
 * @Version 1.0
 * @Description while内try/catch无法停止线程
 **/
public class CantStopThread {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            while (num <= 10000) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                num++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
