package demo.hermesfuxi.java.design.create.single;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author : HermesFuxi
 * @date : 2022/5/24 0024 22:07
 * @desc : 懒汉式单例模式：延迟加载
 */
public class TaskManagerLazySingleton {
    private static TaskManagerLazySingleton taskManagerLazySingleton = null;
    private static volatile TaskManagerLazySingleton taskManagerLazySingleton_v2 = null;

    private TaskManagerLazySingleton() {
    }

    /**
     * 线程不安全：多线程高并发下，可以绕过 ==null 判定
     */
    public static TaskManagerLazySingleton getInstance_v1() {
        if (taskManagerLazySingleton == null) {
            taskManagerLazySingleton = new TaskManagerLazySingleton();
        }
        return taskManagerLazySingleton;
    }

    /**
     * 线程安全：多线程高并发下，影响系统性能
     * 无须对整个方法进行锁定，只需要锁定创建单例实例的代码
     */
    public static synchronized TaskManagerLazySingleton getInstance_v2() {
        if (taskManagerLazySingleton == null) {
            taskManagerLazySingleton = new TaskManagerLazySingleton();
        }
        return taskManagerLazySingleton;
    }

    /**
     * 线程不安全：多线程高并发下，影响系统性能
     */
    public static TaskManagerLazySingleton getInstance_v3() {
        if (taskManagerLazySingleton == null) {
            synchronized (TaskManagerLazySingleton.class) {
                taskManagerLazySingleton = new TaskManagerLazySingleton();
            }
        }
        return taskManagerLazySingleton;
    }

    /**
     * 双重检查锁定：需要在静态成员变量前添加修饰符 volatile，以保证多线程下保证可见性，禁止对象创建时指令之间重排序，所以其他线程不会访问到一个未初始化的对象，从而保证安全性
     * 主要是禁止重排序，初始化一个实例（SomeType st = new SomeType()）在java字节码中会有4个步骤：
     * 1、申请内存空间
     * 2、初始化默认值（区别于构造器方法的初始化）
     * 3、执行构造器方法
     * 4、连接引用和实例。
     * 这4个步骤后两个有可能会重排序，1234  1243都有可能，造成未初始化完全的对象发布。
     * 举例：
     * 如果 线程一 获取到锁进入创建对象实例，这个时候发生了指令重排序（先4后3）。当线程一 执行到 4，线程二刚好进入，由于此时对象已经不为 Null，
     * 所以线程二 可以自由访问该对象。然后该对象还未初始化（未执行3），所以线程二 访问时将会发生异常。
     *
     * volatile可以禁止指令重排序，从而避免这个问题。
     */
    public static TaskManagerLazySingleton getInstance_v4() {
        if (taskManagerLazySingleton_v2 == null) {
            synchronized (TaskManagerLazySingleton.class) {
                if (taskManagerLazySingleton_v2 == null) {
                    taskManagerLazySingleton_v2 = new TaskManagerLazySingleton();
                }
            }
        }
        return taskManagerLazySingleton_v2;
    }

    public static void main(String[] args) {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(100, 100,
                10L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        // 多线程下可以绕过 getInstance_v1 中的 ==null 判定， 造成线程不安全
//        for (int i = 0; i < 100; i++) {
//            threadPoolExecutor.execute(()-> {
//                try {
//                    Thread.sleep(1000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName() + ":"+ TaskManagerLazySingleton.getInstance_v1());
//            });
//        }

//        for (int i = 0; i < 100; i++) {
//            threadPoolExecutor.execute(()-> {
//                long startTime2 = System.currentTimeMillis();
//                System.out.println(Thread.currentThread().getName() + "_v2:"+ TaskManagerLazySingleton.getInstance_v2());
//                long endTime2 = System.currentTimeMillis();
//                System.out.println(Thread.currentThread().getName() + "_v2运行时间为：" + (endTime2 - startTime2));
//            });
//        }

//        for (int i = 0; i < 100; i++) {
//            threadPoolExecutor.execute(()-> {
//                try {
//                    Thread.sleep(1000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                long startTime2 = System.currentTimeMillis();
//                System.out.println(Thread.currentThread().getName() + "_v3:"+ TaskManagerLazySingleton.getInstance_v3());
//                long endTime2 = System.currentTimeMillis();
//                System.out.println(Thread.currentThread().getName() + "_v3运行时间为：" + (endTime2 - startTime2));
//            });
//        }

        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(() -> {
                long startTime2 = System.currentTimeMillis();
                System.out.println(Thread.currentThread().getName() + "_v4:" + TaskManagerLazySingleton.getInstance_v4());
                long endTime2 = System.currentTimeMillis();
                System.out.println(Thread.currentThread().getName() + "_v4运行时间为：" + (endTime2 - startTime2));
            });
        }

        threadPoolExecutor.shutdown();
    }
}
