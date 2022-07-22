package demo.hermesfuxi.java.base;

public class Test {
    public static void main(String[] args) {
//        String s1 = "HelloWorld";
//        String s2 = new String("HelloWorld");
//        if (s1 == s2) {
//            System.out.println("s1 == s2");
//        } else {
//            System.out.println("s1 != s2");
//        }
//        if (s1.equals(s2)) {
//            System.out.println("s1 equals s2");
//        } else {
//            System.out.println("s1 not equals s2");
//        }

//        int[] a = {1, 6, -8, 9};
//        int[] a = {1};
        int[] a = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArrayV2(a));
        ;
    }

    public static int maxSubArray(int[] nums) {
        int maxNum = nums[0];
        // j 为滑动窗口大小
        for (int j = 1; j <= nums.length; j++) {
            // i 为计算光标：窗口左边界
            for (int i = 0; i + j <= nums.length; i++) {
                // nums[i] + ... + nums[i+j-1]
                int total = 0;
                for (int k = i; k <= i + j - 1; k++) {
                    total += nums[k];
                }
                maxNum = Math.max(total, maxNum);
            }
        }
        return maxNum;
    }

    public static int maxSubArrayV2(int[] nums) {
        int maxNum = nums[0];
        int sum = 0;
        for (int num : nums) {
            // 过去区间和：为负，舍弃，为正，与当时值累加
            if (sum < 0) {
                sum =  num;
            } else {
                sum += num;
            }
            maxNum = Math.max(maxNum, sum);
        }
        return maxNum;
    }
}
