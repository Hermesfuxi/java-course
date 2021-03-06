package demo.hermesfuxi.java.base.newjdk.jdk8.optionals;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * 空指针异常是导致Java应用程序失败的最常见原因。以前，为了解决空指针异常，Google公司著名的Guava项目引入了Optional类，Guava通过使用检查空值的方式来防止代码污染.受到Google Guava的启发，Optional类已经成为Java 8类库的一部分。
 * Optional 实际上是个容器：它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测。
 * Optional 最大的好处，就是代码在语义上政治正确:
 * 首先，返回值就能明确区分出，方法是每次返回合理的值还是有条件的返回合理的值。
 * 其次，IDE还能检查出来对Optional对象跳过isPresent直接调用get方法。
 */
public class OptionalDemo {
    public static void main(String[] args) {
        // 创建Optional对象： of不允许参数是null，而ofNullable则无限制
        // 参数不能是null
        Optional<Integer> optional1 = Optional.of(1);
//        Optional<Integer> optional1 = Optional.of(null);

        // 参数可为null，亦可非null
        Optional<Integer> optional2 = Optional.ofNullable(null);
        Optional<Integer> optional03 = Optional.ofNullable(2);

        // 所有null包装成的Optional对象
        Optional<Integer> empty = Optional.<Integer>empty();
        System.out.println(optional2 == empty);// true
        System.out.println(optional2 == empty);// true

        // 所有的空值包装成的Optional对象 基本都是一个引用： 调用的Object的 equal/hashcode 方法
        Object o1 = Optional.<Integer>empty();
        Object o2 = Optional.<String>empty();
        System.out.println(o1 == o2);// true
        System.out.println(o1 == empty);// true

        // isPresent:判断值是否存在
        System.out.println(optional1.isPresent()); // false
        System.out.println(optional2.isPresent()); // true

        // 如果不是null,调用Consumer
        optional1.ifPresent(new Consumer<Integer>() {
            @Override
            public void accept(Integer t) {
                System.out.println("value is " + t);
            }
        });

        // null,不调用Consumer
        optional2.ifPresent(new Consumer<Integer>() {
            @Override
            public void accept(Integer t) {
                System.out.println("value is " + t);
            }
        });

        // orElse(value)：如果optional对象保存的值不是null，则返回原来的值，否则返回value
        System.out.println(optional1.orElse(1000));// 1
        System.out.println(optional2.orElse(1000));// 1000

        // orElseGet(Supplier supplier)：功能与orElse一样，只不过orElseGet参数是一个对象
        System.out.println(optional1.orElseGet(() -> 1000));//1
        System.out.println(optional2.orElseGet(() -> 1000));//1000

        // orElseThrow()：值不存在则抛出异常，存在则什么不做，有点类似Guava的Precoditions
        optional1.orElseThrow(()->new IllegalStateException("get error1"));

        try {// 抛出异常
            optional2.orElseThrow(()->new IllegalStateException("get error2"));
        } catch(IllegalStateException e ) {
            System.out.println(e.getMessage());;
        }

        // filter(Predicate)：判断Optional对象中保存的值是否满足Predicate，并返回新的Optional。
        Optional<Integer> filter1 = optional1.filter((a) -> a == null);
        Optional<Integer> filter2 = optional1.filter((a) -> a == 1);
        Optional<Integer> filter3 = optional2.filter((a) -> a == null);
        System.out.println(filter1.isPresent());// false
        System.out.println(filter2.isPresent());// true
        System.out.println(filter2.get().intValue() == 1);// true
        System.out.println(filter3.isPresent());// false

        // map(Function)：对Optional中保存的值进行函数运算，并返回新的Optional(可以是任何类型)
        Optional<String> str1Optional = optional1.map((a) -> "key" + a);
        Optional<String> str2Optional = optional2.map((a) -> "key" + a);

        System.out.println(str1Optional.get());// key1
        System.out.println(str2Optional.isPresent());// false

        // flatMap()：功能与map()相似，差别请看如下代码。flatMap方法与map方法类似，区别在于mapping函数的返回值不同。
        // map方法的mapping函数返回值可以是任何类型T，而flatMap方法的mapping函数必须是Optional。
        Optional<Optional<String>> str3Optional = optional1.map((a) -> Optional.of("key" + a));
        Optional<String> str4Optional = optional1.flatMap((a) -> Optional.of("key" + a));
        System.out.println(str3Optional.get().get());// key1
        System.out.println(str4Optional.get());// key1

        // test 一下
        String userInfo = null;
        // 链式调用
        System.out.println(Optional.ofNullable(userInfo).map(String::hashCode).orElse(123));

        // 异常处理
        try {
            System.out.println(getName(userInfo));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String userInfo2 = "hermesfuxi";
        try {
            System.out.println(getName(userInfo2));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getName(String userInfo) throws Exception {
//        if(userInfo == null){
//            throw new Exception("userInfo is needed");
//        }
//        return userInfo;
        return Optional.ofNullable(userInfo).orElseThrow(()-> new Exception("userInfo is needed"));
    }
}
