package demo.hermesfuxi.java.base.commons.maths.base;

// 不用Integer的方法，手写进制转换
public class InitBaseConversionDemo01 {
    public static void main(String[] args) {
        //讲一个n进制转换成m进制，比如3进制转换5进制
        System.out.println(tenBaseConversionV1(23, 12));
    }

    /**
     * 十进制转为任意进制
     * @param inputNum 输入数字
     * @param baseNum  输入进制数
     * @return 返回进制转换后的结果
     */
    public static String tenBaseConversionV1(int inputNum, int baseNum) {
        return getBaseStr(inputNum, baseNum, "");
    }

    private static String getBaseStr(int inputNum, int baseNum, String baseStr) {
        int dividend = Math.abs(inputNum);
        int quotient = dividend / baseNum;
        //大于十进制时大于9的数需要转换成英文字母表示形式
        int num = dividend % baseNum;
        if (baseNum > 10 && num > 9) {
            baseStr = (char) ('A' + num - 10) + baseStr;
        } else {
            baseStr = num + baseStr;
        }
        if (quotient < baseNum) {
            // 最后的商必然是进制的第一个数
            return ((inputNum < 0) ? "-" : "") + quotient + baseStr;
        } else {
            // 取 不断除 baseNum，余数从后向前拼接
            // 若 商 >= 进制数，记录其余数，并将商进入下一个迭代
            int nextInputNum = (inputNum < 0) ? -quotient : quotient;
            return getBaseStr(nextInputNum, baseNum, baseStr);
        }
    }

    /**
     * 另一种实现方式
     */
    public static String getBaseStr() {
        //讲一个n进制转换成m进制，比如3进制转换5进制
        String s = "20120122";  //这个字符串中存储的是3进制数
        int n = 0;   //用来存储n的三进制的值
        for (int i = 0; i < s.length(); i++) {
            n = n * 3 + s.charAt(i) - '0';
        }
        System.out.println("转化前的十进制数字：" + n);
        //转换5进制那么则需要将n的值一个个取出（从末尾向前取）
        StringBuilder s1 = new StringBuilder();
        //短除法
        while (n != 0) {
            s1.insert(0, n % 5);  //因为是倒着取余数，所以s1不能写成s1+n%5。
            n /= 5;
        }
        System.out.println("转化后的数字：" + s1.toString());
        return s1.toString();
    }
}
