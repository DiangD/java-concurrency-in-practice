package threadcoreknowledge.uncaughtexception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @ClassName MyUncaughtExceptionHandler
 * @Author DiangD
 * @Date 2020/3/20
 * @Version 1.0
 * @Description 自定义UncaughtExceptionHandler
 **/
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private String name;

    public MyUncaughtExceptionHandler(String name) {
        this.name = name;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        //输出到日志
        logger.log(Level.WARNING, "线程异常，终止了" + t.getName(), e);
        //打印到控制台
        System.out.println(name + "捕获了异常" + t.getName() + "异常" + e);
    }
}
