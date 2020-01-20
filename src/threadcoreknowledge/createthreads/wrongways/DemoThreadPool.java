package threadcoreknowledge.createthreads.wrongways;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ThreadPool
 * @Author DiangD
 * @Date 2020/1/20
 * @Version 1.0
 * @Description 线程池是创建线程一直方式是错误的
 **/
public class DemoThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.submit(new Task() {
            });
        }
    }
    static class Task implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep( 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }
}
