package demo.hermesfuxi.java.concurrent.threadlocals;

public class ThreadLocalSourceDemo01 {
    // 连续生成的哈希码之间的差异-将隐式顺序线程本地ID转换为用于2的幂次方表的近似最佳分布的乘法哈希值。
    // 转化为十进制是 1640531527，2654435769 转换成 int 类型就是 -1640531527，2654435769 等于 (√5-1)/2 乘以 2 的 32 次方。
    // (√5-1)/2 就是黄金分割数，近似为 0.618，也就是说 0x61c88647 理解为一个黄金分割数乘以 2 的 32 次方，
    // 它可以保证 nextHashCode 生成的哈希值，均匀的分布在 2 的幂次方上，且小于 2 的 32 次方
    // 哈希增量：与 斐波纳契散列法 以及 黄金分割 有关
    private static final int HASH_INCREMENT = 0x61c88647;

    public static void main(String[] args) {
       int n = 5;
       int max = 2 << (n - 1);
       System.out.println(Integer.valueOf(HASH_INCREMENT));
       System.out.println(HASH_INCREMENT/(max));
       System.out.println(max);
       for(int i = 0;i<max;i++){
           System.out.print(i * HASH_INCREMENT & (max - 1));
           System.out.print(" ");
       }
    }
}
