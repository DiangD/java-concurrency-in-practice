package threadcoreknowledge.startthread;

/**
 * @ClassName StartThreadTwice
 * @Author DiangD
 * @Date 2020/1/22
 * @Version 1.0
 * @Description 启动一个线程两次会发生什么
 **/
public class StartThreadTwice {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}
