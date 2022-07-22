package demo.hermesfuxi.java.design.action.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者(炒股人 ShareHolder )
 */
public class ShareHolder implements Observer {
    String name;

    ShareHolder(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {//当观察者收到变化时执行
        String param = arg.toString();
        if (param.contains("涨")) {
            System.out.println("Poor " + name + ", " + param);
        } else {
            System.out.println("Locky " + name + ", " + param);
        }
    }
}
