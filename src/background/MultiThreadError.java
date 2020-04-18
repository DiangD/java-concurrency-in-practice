package background;

/**
 * @ClassName MultiThreadError
 * @Author DiangD
 * @Date 2020/4/9
 * @Version 1.0
 * @Description 演示死锁
 **/
public class MultiThreadError implements Runnable {
    public static void main(String[] args) {
        new Thread(new MultiThreadError(1)).start();
        new Thread(new MultiThreadError(2)).start();
    }
    public MultiThreadError(int flag) {
        this.flag = flag;
    }

    int flag;
    static final Object o1 = new Object();
    static final Object o2 = new Object();
    @Override
    public void run() {
        System.out.println("flag = "+flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println(flag);
                }
            }
        }
        if (flag != 1) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println(flag);
                }
            }
        }
    }
}
