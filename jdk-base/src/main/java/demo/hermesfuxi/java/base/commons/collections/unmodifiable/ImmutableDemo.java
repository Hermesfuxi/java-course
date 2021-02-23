package demo.hermesfuxi.java.base.commons.collections.unmodifiable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.ArrayList;

public class ImmutableDemo {
    public static void main(String[] args) {
        ArrayList<String> stringArrayList = Lists.newArrayList("wo","bu","ke","bian");
        ImmutableList<String> immutableList = ImmutableList.copyOf(stringArrayList);
//        immutableList.add(1, "hermesfuxi"); // java.lang.UnsupportedOperationException
        System.out.println(stringArrayList);
        System.out.println(immutableList);

        stringArrayList.add("hermesfuxi");
        System.out.println(stringArrayList);
        System.out.println(immutableList);
    }
}
