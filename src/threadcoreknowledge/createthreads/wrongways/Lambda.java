package threadcoreknowledge.createthreads.wrongways;

/**
 * @ClassName Lambda
 * @Author DiangD
 * @Date 2020/1/20
 * @Version 1.0
 * @Description Lambda表达式创建线程
 **/
public class Lambda {
    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}
