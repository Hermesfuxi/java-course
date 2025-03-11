package demo.hermesfuxi.java.design.create.factory.simple;

public class TriangleGeometricShape implements GeometricShape {
    @Override
    public void draw() {
        System.out.println("绘图工具正在绘制三角形");
    }

    @Override
    public void erase() {
        System.out.println("绘图工具正在擦除三角形");
    }
}
