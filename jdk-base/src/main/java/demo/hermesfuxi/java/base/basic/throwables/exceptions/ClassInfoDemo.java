package demo.hermesfuxi.java.base.basic.throwables.exceptions;

import java.lang.invoke.MethodHandles;

/**
 * 写类名：通常在打印日志的时候需要输出类名，普通类,但是静态类没有this，直接写类名耦合度高
 */
public class ClassInfoDemo {
    public static void main(String[] args) {
        // 获取类名
        getClassName();
    }

    /**
     * 非静态可以用this.getClass()
     */
    private static void getClassName() {
        System.out.println(MethodHandles.lookup().lookupClass().getName());
        System.out.println(new Object(){}.getClass().getEnclosingClass().getName());
        System.out.println(new Throwable().getStackTrace()[0].getClassName());

        // 堆栈轨迹元素
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[1];

        System.out.println(stackTraceElement.getClassName()); // 类全名
        System.out.println(stackTraceElement.getClassLoaderName()); // 类加载器名 app
        System.out.println(stackTraceElement.getMethodName()); // 方法名 getClassName
        System.out.println(stackTraceElement.getFileName()); // 文件名 ClassInfoDemo.java
        System.out.println(stackTraceElement.getLineNumber()); // 实例所在的行号
    }
}
