package threadcoreknowledge.startthread;

/**
 * @ClassName StartAndRunMethod
 * @Author DiangD
 * @Date 2020/1/22
 * @Version 1.0
 * @Description 对比Start Run两种启动线程的方式
 **/
public class StartAndRunMethod {
    public static void main(String[] args) {
        Runnable runnable = ()-> System.out.println(Thread.currentThread().getName());
        runnable.run();
        new Thread(runnable).start();

    }
}
