package threadcoreknowledge.uncaughtexception;

/**
 * @ClassName UseMyUncaughtExceptionHandler
 * @Author DiangD
 * @Date 2020/3/20
 * @Version 1.0
 * @Description 使用MyUncaughtExceptionHandler
 **/
public class UseMyUncaughtExceptionHandler implements Runnable {
    public static void main(String[] args) throws InterruptedException {
//        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("捕获器1"));
        Thread thread = new Thread(new CantCatchDirectly(), "MyThread-1");
        thread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler("捕获器1"));
        thread.start();
        Thread.sleep(300);
        new Thread(new CantCatchDirectly(), "MyThread-2").start();
        Thread.sleep(300);
        new Thread(new CantCatchDirectly(), "MyThread-3").start();
        Thread.sleep(300);
        new Thread(new CantCatchDirectly(), "MyThread-4").start();
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
