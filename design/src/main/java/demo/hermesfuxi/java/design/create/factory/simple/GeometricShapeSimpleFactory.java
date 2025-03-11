package demo.hermesfuxi.java.design.create.factory.simple;

/**
 * 简单工厂模式：设计一个可以创建不同几何形态的绘图工具，每个几何形状都有绘制和擦除两个功能，并在
 */
public class GeometricShapeSimpleFactory {
    public static GeometricShape getGeometricShape(String type){
        GeometricShape geometricShape;
        if("Circle".equalsIgnoreCase(type)){
            geometricShape = new CircleGeometricShape();
        }else if("Square".equalsIgnoreCase(type)){
            geometricShape = new SquareGeometricShape();
        }else if("Triangle".equalsIgnoreCase(type)){
            geometricShape = new TriangleGeometricShape();
        }else {
            throw new IllegalArgumentException("UnSupportedShapeException");
        }
        return geometricShape;
    }

    public static void main(String[] args) {
        GeometricShape circle = getGeometricShape("Circle");
        circle.draw();
        circle.erase();
    }
}
