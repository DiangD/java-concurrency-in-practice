package threadcoreknowledge.uncaughtexception;

/**
 * @ClassName ExceptionInChildThread
 * @Author DiangD
 * @Date 2020/3/20
 * @Version 1.0
 * @Description 单线程，抛出，处理，有异常堆栈
 * 多线程，会怎么样呢？
 **/
public class ExceptionInChildThread implements Runnable{
    public static void main(String[] args) {
        new Thread(new ExceptionInChildThread()).start();
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
    @Override
    public void run() {
        throw new RuntimeException();
    }
}
