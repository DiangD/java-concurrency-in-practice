package threadcoreknowledge.threadobjectcommonmethods;

/**
 * @ClassName Id
 * @Author DiangD
 * @Date 2020/3/19
 * @Version 1.0
 * @Description ID从0开始，JVM运行起来后，我们创建的线程id绝不是0
 **/
public class Id {
    public static void main(String[] args) {
        Thread thread = new Thread("qzh");
        System.out.println("主线程Id："+Thread.currentThread().getId());
        System.out.println("子线程Id："+thread.getId());
        thread.setName("Dd");
    }
}
