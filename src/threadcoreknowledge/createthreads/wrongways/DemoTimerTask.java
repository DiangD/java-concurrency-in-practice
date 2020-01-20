package threadcoreknowledge.createthreads.wrongways;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName DemoTimerTask
 * @Author DiangD
 * @Date 2020/1/20
 * @Version 1.0
 * @Description
 **/
public class DemoTimerTask {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, 1000, 1000);
    }
}
