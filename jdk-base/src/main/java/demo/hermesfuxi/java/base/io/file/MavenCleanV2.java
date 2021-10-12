package demo.hermesfuxi.java.base.io.file;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MavenCleanV2 {
    private static long total;
    private static long time;

    public static void main(String[] args) {
        String path = "/Users/hermesfuxi/opt/maven/repository";
        File dir = new File(path);
        if (!dir.isDirectory()) {
            System.exit(0);
        } else {
            Instant startNow = Instant.now();
            delFile(dir);
            Instant endNow = Instant.now();
            time = Duration.between(startNow, endNow).toMillis();
        }
        System.out.printf("删除数量：%d 耗时：%d 毫秒", total, time);
    }

    private static void delFile(File file) {
        File[] list = file.listFiles();
        assert list != null;
        for (File f : list) {
            if (f.isDirectory()) {
                delFile(f);
                if ("unknown".equals(f.getName())) {
                    delAll(f);
                    total++;
                    System.out.println("删除：" + f.getAbsolutePath());
                } else if (f.getName().startsWith("${") && f.getName().endsWith("}")) {
                    // 如果 文件夹名称是以 ${ 开头 } 结尾，那么将这个文件夹及其下面所有文件全部删除
                    delAll(f);
                    f.delete();
                    total++;
                    System.out.println("删除：" + f.getAbsolutePath());
                } else if (Objects.requireNonNull(f.listFiles()).length == 0) {
                    // 删除空文件夹
                    f.delete();
                    total++;
                    System.out.println("删除：" + f.getAbsolutePath());
                }
            } else {
                if (f.getName().endsWith(".lastUpdated")) {
                    f.delete();
                    total++;
                    System.out.println("删除：" + f.getAbsolutePath());
                }
            }
        }
    }

    /**
     * 删除文件夹下的所有文件夹、文件及其子文件夹、文件
     *
     * @param file file
     */
    private static void delAll(File file) {
        File[] list = file.listFiles();
        assert list != null;
        for (File f : list) {
            if (f.isFile()) {
                // 是文件就删除
                f.delete();
                total++;
                System.out.println("删除：" + f.getAbsolutePath());
            } else {
                // 先将文件夹下的文件夹和文件全部删除再删除源文件夹
                delAll(f);
                f.delete();
                total++;
                System.out.println("删除：" + f.getAbsolutePath());
            }
        }
    }
}
