package threadcoreknowledge.uncaughtexception;

/**
 * @ClassName CantCatchDirectly
 * @Author DiangD
 * @Date 2020/3/20
 * @Version 1.0
 * @Description 1.四个子线程，会抛出4个RuntimeException，无法看到堆栈
 *              2.在主函数进行捕获，希望捕获第一个异常，程序中止。
 *              3.结果：抛出4个异常，程序没有中止
 **/
public class CantCatchDirectly implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        try {
            new Thread(new CantCatchDirectly(), "MyThread-1").start();
            Thread.sleep(300);
            new Thread(new CantCatchDirectly(), "MyThread-2").start();
            Thread.sleep(300);
            new Thread(new CantCatchDirectly(), "MyThread-3").start();
            Thread.sleep(300);
            new Thread(new CantCatchDirectly(), "MyThread-4").start();
        } catch (RuntimeException e) {
            System.out.println("Caught Exception.");
        }
    }
    @Override
    public void run(){
        throw new RuntimeException();
    }
}
