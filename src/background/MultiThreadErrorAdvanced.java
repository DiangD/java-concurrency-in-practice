package background;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName MultiThreadError
 * @Author DiangD
 * @Date 2020/3/26
 * @Version 1.0
 * @Description 第一种：运行结果错误
 * 演示计数不准确（减少）。找出具体出错位置
 **/
public class MultiThreadErrorAdvanced implements Runnable {
    static MultiThreadErrorAdvanced instance = new MultiThreadErrorAdvanced();
    int index = 0;
    static AtomicInteger realIndex = new AtomicInteger();
    static AtomicInteger wrongIndex = new AtomicInteger();
    static CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);
    final boolean[] marked = new boolean[100000];

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("表面结果是" + instance.index);
        System.out.println("成功次数" + realIndex);
        System.out.println("失败次数" + wrongIndex);
    }

    @Override
    public void run() {
        marked[0] = true;
        for (int i = 0; i < 10000; i++) {
            try {
                cyclicBarrier2.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            index++;
            try {
                cyclicBarrier1.reset();
                cyclicBarrier2.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            realIndex.incrementAndGet();
            synchronized (instance) {
                if (marked[index] && marked[index - 1]) {
                    System.out.println("发生错误" + index);
                    wrongIndex.incrementAndGet();
                }
                marked[index] = true;
            }
        }
    }
}
