package threadcoreknowledge.threadobjectcommonmethods;

import static java.lang.Thread.currentThread;

/**
 * @ClassName JoinPrinciple
 * @Author DiangD
 * @Date 2020/3/14
 * @Version 1.0
 * @Description 通过讲解原理分析出join的代替写法
 **/
public class JoinPrinciple {
    public static void main(String[] args) throws InterruptedException {
        Thread main = currentThread();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(main.getState());
            System.out.println(currentThread().getName() + "执行完毕");
        });
        thread.start();
        System.out.println("开始等待子线程执行完毕");
        synchronized (thread){
            thread.wait();
        }
        System.out.println("所有线程执行完毕");
    }
}
