package demo.hermesfuxi.java.commonutil.file.props;

import java.util.Date;
import java.util.Properties;

/**
 * @author Hermesfuxi
 * Desc: 由此可以看出getProperty()只能取字符串的数值，而get方法可以获取任意属性，主要是因为Properties 继承于 Hashtable，
 * 所以可以用put和get方法，但是不建议setProperty()、getProperty()和put()、get()方法互相混用，否则会出现取不出的情况
 */
public class PropertiesPutAndSetPropertyDifferentDemo {
    public static void main(String[] args) {
        Properties properties = new Properties();

        // 测试存储 字符串
        properties.setProperty("str", "测试字符串");
        properties.put("str1", "测试字符串1");
        System.out.println("getProperty()方法获取str值:"+properties.getProperty("str"));
        System.out.println("getProperty()方法获取str1值:"+properties.getProperty("str1"));
        System.out.println("get()方法获取str值:"+properties.get("str"));
        System.out.println("get()方法获取str1值:"+properties.get("str1"));
        /*
         * getProperty()方法获取str值:测试字符串
         * getProperty()方法获取str1值:测试字符串1
         * get()方法获取str值:测试字符串
         * get()方法获取str1值:测试字符串1
         */

        // 测试存储 数字
        properties.setProperty("str2", "123456");
        properties.put("str3", 123456);
        System.out.println("getProperty()方法获取str2值:"+properties.getProperty("str2"));
        System.out.println("getProperty()方法获取str3值:"+properties.getProperty("str3"));
        System.out.println("get()方法获取str2值:"+properties.get("str2"));
        System.out.println("get()方法获取str3值:"+properties.get("str3"));
        /*
         * getProperty()方法获取str2值:123456
         * getProperty()方法获取str3值:null
         * get()方法获取str2值:123456
         * get()方法获取str3值:123456
         */

        // 测试存储日期
        properties.setProperty("str4", new Date().toString());
        properties.put("str5", new Date());
        System.out.println("getProperty()方法获取str4值:"+properties.getProperty("str4"));
        System.out.println("getProperty()方法获取str5值:"+properties.getProperty("str5"));
        System.out.println("get()方法获取str4值:"+properties.get("str4"));
        System.out.println("get()方法获取str5值:"+properties.get("str5"));
        /*
         * getProperty()方法获取str4值:Tue Feb 23 22:06:24 CST 2021
         * getProperty()方法获取str5值:null
         * get()方法获取str4值:Tue Feb 23 22:06:24 CST 2021
         * get()方法获取str5值:Tue Feb 23 22:06:24 CST 2021
         */
    }
}
