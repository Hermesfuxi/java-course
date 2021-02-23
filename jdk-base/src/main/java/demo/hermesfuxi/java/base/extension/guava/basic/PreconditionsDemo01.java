package demo.hermesfuxi.java.base.extension.guava.basic;

import com.google.common.base.Preconditions;

public class PreconditionsDemo01 {
    public static void main(String[] args) {
        getUserName("username");
        String username = null;
        getUserName(username);

    }

    public static String getUserName(String userInfo){
        String s = Preconditions.checkNotNull(userInfo);
        System.out.println(s);
        return "hermesfuxi";
    }
}
