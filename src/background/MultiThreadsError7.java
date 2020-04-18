package background;

/**
 * @ClassName MultiThreadsError5
 * @Author DiangD
 * @Date 2020/4/18
 * @Version 1.0
 * @Description 用工厂模式解决·初始化问题
 **/
public class MultiThreadsError7 {
    int count ;
    private MySource.EventListener listener;

    private MultiThreadsError7(MySource source) {
        listener = e -> System.out.println("\n我得到的我数字是" + count);
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static MultiThreadsError7 getInstance(MySource source) {
        MultiThreadsError7 safeListener = new MultiThreadsError7(source);
        source.registerListener(safeListener.listener);
        return safeListener;
    }

    public static void main(String[] args) {
        MySource mySource = new MySource();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySource.eventCome(new MySource.Event() {
            });
        });
        thread.start();
        MultiThreadsError7.getInstance(mySource);
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
