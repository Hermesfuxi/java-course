package demo.hermesfuxi.java.base.commons.time.format;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.lang.invoke.MethodHandles;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * SimpleDateFormat 线程不安全：多个线程之间共享变量calendar，并修改calendar。
 *      SimpleDateFormat是继承自DateFormat类，DateFormat类中维护了一个全局的Calendar变量
 *      如调用format方法时，多个线程会同时调用 calender.setTime 方法，导致time被别的线程修改
 *      parse() 同理，其实际调用的是 calendarBuilder.establish() 来进行解析，入参正好就是前面的Calendar对象
 * 解决方案：
 *    1、将SimpleDateFormat定义成局部变量
 * 　　2、 加一把线程同步锁：synchronized(lock)
 * 　　3、使用ThreadLocal，每个线程都拥有自己的SimpleDateFormat对象副本。
 *    4、使用DateTimeFormatter代替SimpleDateFormat
 */
public class DateFormatDemo {
    public static void main(String[] args) {
        // 错误的 SimpleDateFormat 使用
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        simpleFormatUtils(()-> {
            try {
                return format.parse("2019-06-28");
            } catch (ParseException e) {
                return new Date();
            }
        });

        // 正确的 SimpleDateFormat 使用
        simpleFormatUtils(() -> {
            try {
                return SimpleDateFormatLocal.convert("2019-06-28");
            } catch (ParseException e) {
                return new Date();
            }
        });
    }

    private static void simpleFormatUtils(Supplier<Date> supplier) {
        Callable<Date> call = () -> {
            Date date = supplier.get();
            System.out.println(date);
            return date;
        };
        formatUtils(call);

        Runnable runnable = () -> System.out.println(supplier.get());
        formatUtils2(runnable);

    }

    private static void formatUtils2(Runnable runnable) {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat(MethodHandles.lookup().lookupClass().getName() + "-pool-%d").build();

        ExecutorService threadPool = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        threadPool.execute(runnable);

        threadPool.shutdown();
    }

    private static void formatUtils(Callable<Date> call) {
        // 创建线程池，最好直接new ThreadPoolExecutor，而不是用Executors工具类
        // ExecutorService threadPool = Executors.newCachedThreadPool(10);
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat(MethodHandles.lookup().lookupClass().getName() + "-pool-%d").build();

        ExecutorService threadPool = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        List<Future<Date>> list = new ArrayList<>();
        for (int i = 0; i<100; i++){
            Future<Date> submit = threadPool.submit(call);
            list.add(submit);
        }
        list.stream().map(DateFormatDemo::apply).forEach(System.out::println);
        threadPool.shutdown();
    }

    private static Date apply(Future<Date> e) {
        Date date = null;
        try {
            date = e.get();
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
        return date;
    }
}

class SimpleDateFormatLocal {
    // SimpleDateFormat 是线程不安全的类，一般不要定义为static变量，如果定义为static，必须加锁
    // ThreadLocal即线程本地变量
    private static final ThreadLocal<DateFormat> DF = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
    public static Date convert(String str) throws ParseException {
        return  DF.get().parse(str);
    }
}
