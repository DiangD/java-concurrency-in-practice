package background;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MultiThreadsError3
 * @Author DiangD
 * @Date 2020/4/9
 * @Version 1.0
 * @Description 发布逸出
 **/
public class MultiThreadsError3 {
    private Map<String, String> states;

    public MultiThreadsError3() {
        this.states = new HashMap<>();
        states.put("1", "MON");
        states.put("2", "TUES");
        states.put("3", "WED");
        states.put("4", "THUS");
        states.put("5", "FRI");
    }

    public Map<String, String> getStates() {
        return states;
    }

    public Map<String, String> getStatesImprove() {
        return new HashMap<>(states);
    }
    public static void main(String[] args) {
        MultiThreadsError3 threadsError3 = new MultiThreadsError3();
        System.out.println(threadsError3.getStatesImprove().get("1"));
        threadsError3.getStatesImprove().remove("1");
        System.out.println(threadsError3.getStatesImprove().get("1"));
    }
}
