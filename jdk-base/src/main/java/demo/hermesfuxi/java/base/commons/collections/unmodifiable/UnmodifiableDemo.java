package demo.hermesfuxi.java.base.commons.collections.unmodifiable;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 为什么需要不可变集合
 * （1）保证线程安全：在并发程序中，使用Immutable既保证线程安全性，也大大增强了并发时的效率（跟并发锁方式相比）。尤其当一个对象是值对象时，更应该考虑采用Immutable方式；
 * （2）被不可信的类库使用时会很安全；
 * （3）如果一个对象不需要支持修改操作(mutation)，将会节省空间和时间的开销；经过分析，所有不可变的集合实现都比可变集合更加有效地利用内存；
 * （4）可以当作一个常量来对待，并且这个对象在以后也不会被改变。
 * 将一个对象复制一份成immutable的，是一个防御性编程技术。
 */
public class UnmodifiableDemo {
    public static void main(String[] args) {
        // 下面的代码利用Collections.unmodifiableList(list)得到一个不可修改的集合unmodifiableList
        List list = new ArrayList();
        list.add("wyp");
        list.add("good");

        List unmodifiableList = Collections.unmodifiableList(list);
        System.out.println(unmodifiableList);//[wyp, good]

        try {
            boolean add = unmodifiableList.add("add");// 会抛出异常 java.lang.UnsupportedOperationException
        }catch (Exception e){
            System.out.println("error");
        }

        list.add("hermesfuxi");    // 当原始集合被修改后，不可变集合里面的元素也是跟着发生变化。
        System.out.println(unmodifiableList); // [wyp, good, hermesfuxi]

        /*
         * 利用JDK类库中提供的unmodifiableXXX方法最少存在以下几点不足：
         * 笨拙：每次都得写那么多代码；
         * 不安全：如果没有引用到原来的集合，这种情况之下才会返回唯一真正永恒不变的集合；
         *       当原始集合被修改后，不可变集合里面的元素也是跟着发生变化;
         * 效率很低：返回的不可修改的集合数据结构仍然具有可变集合的所有开销。
         */
    }
}
