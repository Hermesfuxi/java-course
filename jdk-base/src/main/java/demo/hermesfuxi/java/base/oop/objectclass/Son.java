package demo.hermesfuxi.java.base.oop.objectclass;

/**
 * Java 面试题：类的执行顺序 - 先父后子，先类后例
 */
public class Son extends Parent {
    //静态代码块：在虚拟机加载类的时候就会加载执行，而且只执行一次
    static{
        System.out.println("Son static");
    }
    //构造代码块：
    // 构造代码块和构造函数都是给对象进行初始化的，但是构造代码块在对象一建立就运行，优先于构造函数执行
    // 构造代码块是给此类所有对象进行的初始化，构造函数只是给它对应的对象初始化
    {
        System.out.println("Son block 01");
    }
    //构造函数
    public Son(){
        System.out.println("Son constructor");
    }

    //构造代码块
    {
        System.out.println("Son block 02");
    }

    //main方法
    public static void main(String[] args) {
        System.out.println("start Son main");
        new Son();
    }

    //构造代码块
    {
        System.out.println("Son block 03");
    }
}

class Parent{

    static{
        System.out.println("Parent static");
    }

    {
        System.out.println("Parent block 01");
    }

    public Parent(){
        System.out.println("Parent constructor");
    }

    {
        System.out.println("Parent block 02");
    }
}