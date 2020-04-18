package background;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MultiThreadsError6
 * @Author DiangD
 * @Date 2020/4/18
 * @Version 1.0
 * @Description 构造函数中新建线程
 **/
public class MultiThreadsError6 {
    private Map<String, String> states;

    public MultiThreadsError6() {
        new Thread(()->{
            states = new HashMap<>();
            states.put("1", "MON");
            states.put("2", "TUES");
            states.put("3", "WED");
            states.put("4", "THUS");
            states.put("5", "FRI");
        }).start();
    }

    public Map<String, String> getStates() {
        return states;
    }

    public static void main(String[] args) throws InterruptedException {
        Map<String, String> states = new MultiThreadsError6().getStates();
       // Thread.sleep(1000);
        System.out.println(states.get("1"));
    }
}
