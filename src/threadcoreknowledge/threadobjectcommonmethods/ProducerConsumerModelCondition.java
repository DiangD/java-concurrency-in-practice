package threadcoreknowledge.threadobjectcommonmethods;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ProducerConsumerModelCondition
 * @Author DiangD
 * @Date 2020/3/15
 * @Version 1.0
 * @Description
 **/
public class ProducerConsumerModelCondition {
    public static final Lock LOCK = new ReentrantLock();
    public static final Condition fullCondition = LOCK.newCondition();
    public static final Condition emptyCondition = LOCK.newCondition();
    private static LinkedList<Integer> storage = new LinkedList<>();
    public static void main(String[] args) {
        new Producer(storage, 10).start();
        new Consumer(storage).start();
    }

    static class Producer extends Thread {
        private LinkedList<Integer> storage;
        private int maxSize;

        public Producer(LinkedList<Integer> storage, int maxSize) {
            this.storage = storage;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    LOCK.lock();
                    put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    LOCK.unlock();
                }
            }
        }

        public void put(int num) throws InterruptedException {
            while (storage.size() == maxSize) {
                System.out.println("仓库满了，生产者阻塞");
                fullCondition.await();
            }
            storage.add(num);
            System.out.println("仓库增加了：" + num + " 仓库里有了" + storage.size() + "个产品");
            emptyCondition.signal();
        }
    }

    static class Consumer extends Thread {
        private LinkedList<Integer> storage;

        public Consumer(LinkedList<Integer> storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    LOCK.lock();
                    take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    LOCK.unlock();
                }
            }
        }

        public void take() throws InterruptedException {
            while (storage.isEmpty()) {
                System.out.println("仓库里没有东西了，消费者阻塞");
                emptyCondition.await();
            }
            System.out.println("消费者消费了：" + storage.poll() + "仓库里还有" + storage.size() + "个产品");
            fullCondition.signal();
        }
    }
}
