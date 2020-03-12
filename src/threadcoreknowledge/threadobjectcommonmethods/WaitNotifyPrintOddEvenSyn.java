package threadcoreknowledge.threadobjectcommonmethods;
/**
 * @ClassName PrintOddAndEven
 * @Author DiangD
 * @Date 2020/3/12
 * @Version 1.0
 * @Description 交替打印0~100的奇数偶数
 **/
public class WaitNotifyPrintOddEvenSyn {
    static int count ;
    static final Object lock = new Object();

    /**
     * 遍历，拿到锁就打印，会进行多次遍历，浪费资源
     */
    public static void main(String[] args) {
        new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    if ((count&1)==1) {
                        System.out.println(Thread.currentThread().getName()+": "+count++);
                    }
                }
            }
        },"奇数").start();
        new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    if ((count&1)==0) {
                        System.out.println(Thread.currentThread().getName()+": "+count++);
                    }
                }
            }
        },"偶数").start();
    }


}
