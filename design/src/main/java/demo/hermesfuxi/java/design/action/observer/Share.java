package demo.hermesfuxi.java.design.action.observer;

import java.util.Observable;

/**
 * @author HermesFuxi
 * @date 2022/5/21 0021 21:07
 * @desc: 观察者模式
 * 简易实例：
 * 1、被观察者(股票 Share)继承java.util.Observable[可观察的]类
 * 2、观察者(炒股人 ShareHolder )实现java.util.Observer接口
 * 3、被观察者实现自身业务逻辑(股票涨跌)，指定什么时候发起通知 notifyObservers()
 * 4、观察者实现接口方法 update()，指定被通知时的动作
 * 5、被观察者实例调用 addObserver() 方法选择观察自己的观察者
 *
 * 总结：
 * 1、观察者和被观察者是多对多的关系
 * 2、被观察者相当于自变量，自变量怎样以及何时发生变化根据业务逻辑设定，如上股票涨跌时变化。这个变化通过setChanged()+notifyObserver()方法通知到每一个观察者
 * 3、观察者重写update()方法根据notifyObserver(arg)传递来的参数决定动作
 */
public class Share extends Observable {
    public static void main(String[] args) {
        Share share1 = new Share("茅台");
        Share share2 = new Share("五粮液");

        ShareHolder shareHolder1 = new ShareHolder("王二");
        ShareHolder shareHolder2 = new ShareHolder("张三");
        ShareHolder shareHolder3 = new ShareHolder("李四");

        share1.addObserver(shareHolder1);
        share1.addObserver(shareHolder3);

        share2.addObserver(shareHolder1);
        share2.addObserver(shareHolder2);

        share1.increase();
        share2.decrease();
    }

    private String name;

    public Share(String name) {
        this.name = name;
    }

    //动作一：股票跌了
    public void decrease() {
        //标记被观察者已有变化
        setChanged();

        // 通知观察者变化, 参数是向观察者的 update()传去的值
        notifyObservers(name + "跌了");
    }

    //动作二：股票涨了
    public void increase() {
        //标记被观察者已有变化
        setChanged();

        //标记被观察者已有变化
        notifyObservers(name + "涨了");
    }
}

