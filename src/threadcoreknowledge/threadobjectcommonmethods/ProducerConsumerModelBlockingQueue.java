package threadcoreknowledge.threadobjectcommonmethods;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @ClassName ProducerConsumerModelBlockingQueue
 * @Author DiangD
 * @Date 2020/3/15
 * @Version 1.0
 * @Description 使用阻塞队列来实现生产者消费者模式
 **/
public class ProducerConsumerModelBlockingQueue {
    public static final ArrayBlockingQueue<Integer> storage = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Producer(storage)).start();
        Thread.sleep(1000);
        new Thread(new Consumer(storage)).start();
    }

    static class Producer implements Runnable {
        private ArrayBlockingQueue<Integer> storage;

        public Producer(ArrayBlockingQueue<Integer> storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void put(int n) throws InterruptedException {
            storage.put(n);
            System.out.println("仓库新增了:" + n);
        }
    }

    static class Consumer implements Runnable {
        private ArrayBlockingQueue<Integer> storage;

        public Consumer(ArrayBlockingQueue<Integer> storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void take() throws InterruptedException {
            Integer num = storage.take();
            System.out.println("消费了：" + num + "；仓库还剩余：" + storage.size());
        }
    }
}
