package threadcoreknowledge.stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @ClassName WrongWayVolatileFixed
 * @Author DiangD
 * @Date 2020/1/24
 * @Version 1.0
 * @Description 用中断修复无限等待问题
 **/
public class WrongWayVolatileFixed {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> storage = new ArrayBlockingQueue<>(10);
        Producer producer = new Producer(storage);
        Consumer consumer = new Consumer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了");
        producerThread.interrupt();
    }

    static class Producer implements Runnable {
        ArrayBlockingQueue<Integer> storage;

        public Producer(ArrayBlockingQueue<Integer> storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                while (num <= 100000 && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        //队列满了会阻塞在这一步
                        storage.put(num);
                        System.out.println(num + "是100的倍数,被放到仓库中了");
                    }
                    num++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("生产者结束运行");
            }
        }
    }

    static class Consumer {
        ArrayBlockingQueue<Integer> storage;

        public Consumer(ArrayBlockingQueue<Integer> storage) {
            this.storage = storage;
        }

        public boolean needMoreNums() {
            return !(Math.random() > 0.95);
        }
    }
}
