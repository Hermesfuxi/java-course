package demo.hermesfuxi.java.design.create.factory.simple;

public class CircleGeometricShape implements GeometricShape {
    @Override
    public void draw() {
        System.out.println("绘图工具正在绘制圆形");
    }

    @Override
    public void erase() {
        System.out.println("绘图工具正在擦除圆形");
    }
}
