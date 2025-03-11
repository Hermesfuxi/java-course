package demo.hermesfuxi.java.design.create.factory.simple;

public class SquareGeometricShape implements GeometricShape {
    @Override
    public void draw() {
        System.out.println("绘图工具正在绘制方形");
    }

    @Override
    public void erase() {
        System.out.println("绘图工具正在擦除方形");
    }
}
