package demo.hermesfuxi.java.design.create.single;

/**
 * @author : HermesFuxi
 * @date : 2022/5/24 0024 22:01
 * @desc : 饿汉式无法延迟加载，懒汉式会有受线程安全影响的性能问题
 * 一种更好的实现方式：静态内部类单例模式（IoDH）
 *  JVM类加载顺序：加载一个类时，其内部类不会同时被加载。一个类被加载，当且仅当其某个静态成员（静态域、构造器、静态方法等）被调用时发生。
 *  该实现方式比较简单，而且既实现了惰性初始化（Lazy-Initialazation），又由JVM保证了多线程并发访问的正确性
 */
public class TaskManagerStaticInnerSingleton {
    private TaskManagerStaticInnerSingleton(){}

    private static class TaskManagerStaticInner{
        private static TaskManagerStaticInnerSingleton taskManagerEagerSingleton = new TaskManagerStaticInnerSingleton();
    }

    public static TaskManagerStaticInnerSingleton getInstance(){
        return TaskManagerStaticInner.taskManagerEagerSingleton;
    }
}
