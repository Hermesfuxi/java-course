package demo.hermesfuxi.java.base.newjdk.jdk8.streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        getNumList();
    }

    public static void getNumList() {
        // 起始数字
        int start = 1;
        // 生成数字的个数
        int end = 100;

        // 生成1,2,3,4,5...100
        List<Integer> list = Stream.iterate(start, item -> item+1).limit(end).collect(Collectors.toList());
        System.out.println(list.toString());

        // 生成1,3,5...199
        List<Integer> list1 = Stream.iterate(start, item -> item+2).limit(end).collect(Collectors.toList());
        System.out.println(list1.toString());

    }
}
