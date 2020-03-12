package threadcoreknowledge.threadobjectcommonmethods;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName ProducerConsumerModel
 * @Author DiangD
 * @Date 2020/3/12
 * @Version 1.0
 * @Description 用wait/notify实现生产者消费者模式
 **/
public class ProducerConsumerModel {
    public static void main(String[] args) {
        EventStorage eventStorage = new EventStorage(10,new LinkedList<>());
        Producer producer = new Producer(eventStorage);
        Consumer consumer = new Consumer(eventStorage);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
    static class Producer implements Runnable {
        private EventStorage storage;

        public Producer(EventStorage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                storage.put();
            }
        }
    }
    static class Consumer implements Runnable {
        private EventStorage storage;

        public Consumer(EventStorage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                storage.take();
            }
        }
    }

    static class EventStorage {
        private int maxSize;
        private List<Date> storage;

        public EventStorage(int maxSize, LinkedList<Date> storage) {
            this.maxSize = maxSize;
            this.storage = new LinkedList<>();
        }

        public synchronized void put() {
            if (storage.size() == maxSize) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            storage.add(new Date());
            System.out.println("仓库里有了" + storage.size() + "个产品");
            notify();
        }

        public synchronized void take() {
            if (storage.size() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("拿到了"+storage.remove(0)+"现在仓库还剩下"+storage.size());
            notify();
        }

    }

}
