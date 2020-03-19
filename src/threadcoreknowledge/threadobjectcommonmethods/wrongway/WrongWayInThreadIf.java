package threadcoreknowledge.threadobjectcommonmethods.wrongway;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName WrongWayInThreadIF
 * @Author DiangD
 * @Date 2020/3/16
 * @Version 1.0
 * @Description 使用if语句，可能会出现错误
 **/
public class WrongWayInThreadIf {
    public static final Lock LOCK = new ReentrantLock();
    public static final Condition fullCondition = LOCK.newCondition();
    public static final Condition emptyCondition = LOCK.newCondition();
    private static LinkedList<Integer> storageA = new LinkedList<>();
    private static LinkedList<Integer> storageB = new LinkedList<>();

    public static void main(String[] args) {
        Thread producerA = new Producer(storageA, 2, "producerA");
        Thread producerB = new Producer(storageB, 2, "producerB");
        Consumer consumerA = new Consumer(storageA, "consumerA");
        Consumer consumerB = new Consumer(storageB, "consumerB");
        producerA.start();
        producerB.start();
        consumerA.start();
        consumerB.start();
    }

    static class Producer extends Thread {
        private LinkedList<Integer> storage;
        private int maxSize;

        public Producer(LinkedList<Integer> storage, int maxSize, String name) {
            this.storage = storage;
            this.maxSize = maxSize;
            this.setName(name);
        }

        @Override
        public void run() {
            int i = 0;
            while (true) {
                try {
                    LOCK.lock();
                    put(i);
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    LOCK.unlock();
                }
            }
        }

        public void put(int num) throws InterruptedException {
            while (storage.size() >= maxSize) {
                System.out.println(Thread.currentThread().getName() + " :仓库满了，生产者阻塞");
                fullCondition.await();
            }
            storage.add(num);
            System.out.println(Thread.currentThread().getName() + ":仓库增加了：" + num + " 仓库里有了" + storage.size() + "个产品");
            emptyCondition.signalAll();
            fullCondition.signalAll();
        }
    }

    static class Consumer extends Thread {
        private LinkedList<Integer> storage;

        public Consumer(LinkedList<Integer> storage, String name) {
            this.storage = storage;
            this.setName(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 2; i++) {
                try {
                    LOCK.lock();
                    take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    LOCK.unlock();
                }
            }
        }

        public void take() throws InterruptedException {
            while (storage.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + ":仓库里没有东西了，消费者阻塞");
                emptyCondition.await();
            }
            System.out.println(Thread.currentThread().getName() + "消费了：" + storage.poll() + "仓库里还有" + storage.size() + "个产品");
            emptyCondition.signalAll();
            fullCondition.signalAll();
        }
    }
}
