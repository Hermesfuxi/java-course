package demo.hermesfuxi.java.base.commons.maths.base;

/**
 * 常规的进制转换都封装在Integer对象中
 * 10,2,8,16, n 互转
 */
public class BaseConversionDemo02 {
    public static void main(String[] args) {
        int n = 18;
        Integer.toHexString(n);
        System.out.println(n + "的二进制是:" + Integer.toBinaryString(n));
        System.out.println(n + "的八进制是:" + Integer.toOctalString(n));
        System.out.println(n + "的十六进制是:" + Integer.toHexString(n));
        System.out.println(n + "的三进制是:" + Integer.toString(n, 3));

        String s = "10101";//1+4+16==21
        System.out.println(Integer.parseInt(s,2));//结果是21
        // Integer 中封装好了一个方法：
        // 能够直接计算二进制中的1的个数，在竞赛中很有用.
        // JDk8中的实现方法使用的是 & 操作，所以比正常的遍历要快的多.
        System.out.println(Integer.bitCount(21));
        // 上文是21的二进制，所以21的二进制中有 3 个‘1’,


        int a = 1;
        System.out.printf("%04d\n", a);
        //. 运行结果: 0001 (使用printf几乎完全兼容C语言输出格式,适合刷OJ用)

        String str= String.format("%04d",a);
        System.out.println(str);
        // 也是0001 (感谢评论区提出的方法)
    }
}
