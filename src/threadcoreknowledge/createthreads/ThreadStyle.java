package threadcoreknowledge.createthreads;

/**
 * @ClassName ThreadStyle
 * @Author DiangD
 * @Date 2020/1/20
 * @Version 1.0
 * @Description
 **/
public class ThreadStyle extends Thread {

    @Override
    public void run() {
        System.out.println("用Thread方式创建线程");
    }

    public static void main(String[] args) {
        new ThreadStyle().start();
    }
}
