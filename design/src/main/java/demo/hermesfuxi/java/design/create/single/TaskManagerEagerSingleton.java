package demo.hermesfuxi.java.design.create.single;

/**
 * @author : HermesFuxi
 * @date : 2022/5/24 0024 22:01
 * @desc : 饿汉式单例模式
 */
public class TaskManagerEagerSingleton {
    private static TaskManagerEagerSingleton taskManagerEagerSingleton = new TaskManagerEagerSingleton();

    private TaskManagerEagerSingleton(){}

    public static TaskManagerEagerSingleton getInstance(){
        return taskManagerEagerSingleton;
    }
}
