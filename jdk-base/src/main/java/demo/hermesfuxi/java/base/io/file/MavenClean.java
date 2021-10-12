package demo.hermesfuxi.java.base.io.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MavenClean {
    private static List<File> fileList = new ArrayList<>();
    private static String path = "/Users/hermesfuxi/opt/maven/repository";

    public static void main(String[] args) {

        System.out.println("开始查找空文件。。。");
        File file = new File(path);
        find(file);
        System.out.println("清除成功！");
        /*
         * 如果执行上面的操作jar引入还是失败的话放开 8、18、36 行代码
         */
        fileList.forEach(File::delete);
    }

    /**
     * 递归查找并删除无用文件
     *
     * @param file 文件
     */
    private static void find(File file) {
        if (file.isDirectory()) {//判断传入的是文件还是目录
            File[] listFiles = file.listFiles();
            boolean flag = false;
            assert listFiles != null;
            for (File sonFile : listFiles) {
                String fileName = sonFile.getName();
                if (sonFile.isDirectory()) {
                    find(sonFile);
                } else {
                    //因为maven如果jar下载成功了会生成一个lastUpdated.properties结尾的文件 如果有则说明jar没有问题直接跳出
                    if (fileName.endsWith("lastUpdated.properties")) {
                        fileList.add(sonFile);
                        return;
                    } else {
                        flag = true;//如果没有则将标志改为true在下面直接提供删除
                    }
                }
            }
            if (Objects.requireNonNull(file.list()).length == 0) {//判断是否是有子文件
                file.delete();//如果没有子文件直接删除
            }
            if (flag) {//如果flag = true 并且文件不为空，则说明里面有多余错误文件
                delete(file);
            }
        }
    }

    /**
     * 递归删除
     *
     * @param file 文件
     */
    private static void delete(File file) {
        if (file.isDirectory() && Objects.requireNonNull(file.list()).length != 0) {//先判断是目录还是文件
            File[] sonfiles = file.listFiles();
            //如果子目录还有文件则递归调用该方法
            assert sonfiles != null;
            for (File sonFile : sonfiles) {
                delete(sonFile);
            }

        }
        //如果不是目录直接删除
        file.delete();
    }

}
