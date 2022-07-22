package demo.hermesfuxi.java.base.oop.generic;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 泛型通配符: 不知道使用什么类型来接收的时候,此时可以使用?,?表示未知通配符
 * 但是一旦使用泛型的通配符后，只能使用Object类中的共性方法，集合中元素自身方法无法使用
 * 此时只能接受数据,不能往该集合中存储数据
 */
public class GenericWildcardDemo {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("aaaa");
        list1.add("bbbb");

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(20);

        getElement(list1);
        getElement(list2);

        ArrayList<Number> list3 = new ArrayList<>();
        list2.add(111);
        list2.add(222);

        ArrayList<Object> list4 = new ArrayList<>();
        list2.add(333);
        list2.add(444);

//        getElement1(list1);
        getElement1(list2);
        getElement1(list3);
//        getElement1(list4);

//        getElement2(list1);
//        getElement2(list2);
        getElement2(list3);
        getElement2(list4);
    }

    public static void getElement(ArrayList<?> list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }

    // 泛型的上限：此时的泛型?，必须是Number类型或者Number类型的子类
    public static void getElement1(ArrayList<? extends Number> list){
        getElement(list);
    }

    public static void getElement2(ArrayList<? super Number> list){
        getElement(list);
    }
}
