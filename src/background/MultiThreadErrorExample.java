package background;

/**
 * @ClassName MultiThreadErrorExample
 * @Author DiangD
 * @Date 2020/3/27
 * @Version 1.0
 * @Description 第一种：运行结果错误
 * 演示计数不准确（减少）。
 **/
public class MultiThreadErrorExample implements Runnable{
    static MultiThreadErrorExample instance = new MultiThreadErrorExample();
    int index;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("表面结果是" + instance.index);

    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            index++;
        }
    }
}
