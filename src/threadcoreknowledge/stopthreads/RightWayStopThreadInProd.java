package threadcoreknowledge.stopthreads;

/**
 * @ClassName RightWayStopThreadInProd
 * @Author DiangD
 * @Date 2020/1/22
 * @Version 1.0
 * @Description 最佳实践：catch了InterruptedException之后优先选择：
 *                      在方法签名中抛出异常 那么run()就会强制try / catch
 **/
public class RightWayStopThreadInProd implements Runnable {
    @Override
    public void run() {
        while (true && !Thread.currentThread().isInterrupted()) {
            System.out.println("go");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                System.out.println("保存日志");
                e.printStackTrace();
            }
        }
    }

    private void throwInMethod() throws InterruptedException {
            Thread.sleep(2000);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
