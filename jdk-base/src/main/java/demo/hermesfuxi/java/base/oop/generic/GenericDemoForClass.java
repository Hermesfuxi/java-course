package demo.hermesfuxi.java.base.oop.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * 定义和使用含有泛型的类
 */
public class GenericDemoForClass {
    public static void main(String[] args) {
//        ArrayList<String> classDemo = new ArrayList<>();
//        String text = classDemo.getEnum("hermesfuxi");
//        System.out.println(text);
    }

//    final static class ArrayList<E> {
//        public E getEnum(E e){
//            return e;
//        }
//    }

    public boolean containsDuplicate(int[] nums) {
//        return Arrays.stream(nums).distinct().count() < nums.length;
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        return set.size() < nums.length;
    }

}
