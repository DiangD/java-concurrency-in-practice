package threadcoreknowledge.createthreads;

/**
 * @ClassName RunnableStyle
 * @Author DiangD
 * @Date 2020/1/20
 * @Version 1.0
 * @Description 用runnable方式创建线程
 **/
public class RunnableStyle implements Runnable{
    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("用runnable方式创建线程");
    }
}


