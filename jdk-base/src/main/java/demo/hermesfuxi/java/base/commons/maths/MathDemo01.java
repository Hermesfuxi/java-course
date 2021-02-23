package demo.hermesfuxi.java.base.commons.maths;

/**
 * @author Hermesfuxi
 */
public class MathDemo01 {
    interface MathOperation {
        int operation(int a, int b);
    }
    interface MathOperation2 {
        int operation(int a);
    }

    public static void main(String[] args) {
        // 加法
        MathOperation2 increment = a -> a + 1;
        MathOperation2 increment2 = Math::incrementExact;

        // 自减
        MathOperation2 decrement = a -> a - 1;
        MathOperation2 decrement2 = Math::decrementExact;

        MathOperation addition = (a, b) -> a + b;
        MathOperation addition2 = Integer::sum;

        // 减法
        MathOperation subtraction = (a, b) -> a - b;
        MathOperation subtraction2 = Math::subtractExact;

        // 乘法
        MathOperation multiplication = Math::multiplyExact;
        MathOperation multiplication2 = Math::multiplyExact;

        // 除法： 没有大括号及返回语句
        MathOperation division = (a, b) -> a/b;
        MathOperation division2 = Math::floorDiv;

        System.out.println("10 + 5 = " + addition.operation(10, 5));
        System.out.println("10 - 5 = " + subtraction.operation(10, 5));
        System.out.println("10 x 5 = " + multiplication.operation(10, 5));
        System.out.println("10 / 5 = " + division.operation(10, 5));

        // 求模运算和求余运算在第一步不同: 取余运算在取c的值时，向 0 方向舍入(fix()函数)；而取模运算在计算c的值时，向负无穷方向舍入(floor()函数)
        // 当a和b符号一致时，求模运算和求余运算所得的c的值一致，因此结果一致。当符号不一致时，结果不一样。
        // 各个环境下%运算符的含义不同，比如c/c++，java 为取余，而python则为取模。
        // Math.floorMod(+4, -3) == -2;    (+4 % -3) == +1;
        // Math.floorMod(-4, +3) == +2;    (-4 % +3) == -1;
        // Math.floorMod(-4, -3) == -1;    (-4 % -3) == -1;
        // Math.floorMod(+4, +3) == +1;    (+4 % +3) == +1;

        // 取余运算: 向 0 方向舍入（记忆：零余）
        MathOperation  remainderOperation = (a, b) -> a % b;

        System.out.println("10 % -3 = " + remainderOperation.operation(10, -3));
        System.out.println("-10 % 3 = " + remainderOperation.operation(-10, 3));
        System.out.println("-10 % -3 = " + remainderOperation.operation(-10, -3));
        // 取余的时候符号和被除数保持一致, 值不变
        //10 % 3 = 1
        //10 % -3 = 1
        //-10 % 3 = -1
        //-10 % -3 = -1

        // 取模运算 : 向负无穷方向舍入（记忆：做最坏打算）
        MathOperation  modulusOperation = Math::floorMod;

        System.out.println("10 mod -3 = " + modulusOperation.operation(10, -3));
        System.out.println("-10 mod 3 = " + modulusOperation.operation(-10, 3));
        System.out.println("-10 mod -3 = " + modulusOperation.operation(-10, -3));
        //取模的时候符合和除数保持一致，同号一值，异号一值
        // 10 mod 3 = 1
        // 10 mod -3 = -2
        //-10 mod 3 = 2
        //-10 mod -3 = -1
    }
}