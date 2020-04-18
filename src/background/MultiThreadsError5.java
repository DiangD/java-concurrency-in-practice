package background;

/**
 * @ClassName MultiThreadsError5
 * @Author DiangD
 * @Date 2020/4/18
 * @Version 1.0
 * @Description 观察者模式 注册监听器
 **/
public class MultiThreadsError5 {
    int count ;

    public MultiThreadsError5(MySource source) {
        source.registerListener(e -> System.out.println("\n我得到的我数字是" + count));
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static void main(String[] args) {
        MySource mySource = new MySource();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySource.eventCome(new MySource.Event() {
            });
        });
        thread.start();
        new MultiThreadsError5(mySource);
    }

    static class MySource {
        private EventListener listener;

        void registerListener(EventListener eventListener) {
            this.listener = eventListener;
        }

        void eventCome(Event e) {
            if (listener != null) {
                listener.onEvent(e);
            } else {
                System.out.println("还未初始化完毕");
            }
        }

        interface EventListener {
            void onEvent(Event e);
        }

        interface Event {
        }

    }
}
