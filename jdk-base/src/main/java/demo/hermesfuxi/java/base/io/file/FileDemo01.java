package demo.hermesfuxi.java.base.io.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;

/**
 * @author hermesfuxi
 * @date 2020/12/19 22:34
 * @description file类
 */
public class FileDemo01 {

    public static void searchFile(String fileName){
        File[] files = File.listRoots();
    }

    public static void main(String[] args) {
        // 路径分隔符 windows -> ";"  Linux -> ":"
        String pathSeparator = File.pathSeparator;
        // 文件名称分隔符  windows -> "\"  Linux -> "/"
        String separator = File.separator;

        // 相对路径与绝对路径
        File file = new File("D:\\tmp");
        File file1 = new File("src");
        File file2 = new File(file, "1.txt");
//        System.out.println(file);
//        System.out.println(file1.getAbsolutePath());
//        System.out.println(file2.getAbsolutePath());

        try {
            File canonicalFile = file2.getCanonicalFile();
            String path = canonicalFile.getPath();
            System.out.println("canonical " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 获取文件名 或 文件夹名
//        System.out.println(file.getName());
//        System.out.println(file1.getName());
//        System.out.println(file2.getName());
//
//        // 无法获取文件夹的大小，始终为0
//        System.out.println(file.length());
//        // 只能获取文件的字节大小，若路径不存在，返回 0
//        System.out.println(file2.length());
//
//        System.out.println(file.exists());
//        System.out.println(new File(file1, "1.txt").exists());
//
//        System.out.println(file.isDirectory());
//        System.out.println(file.isFile());

        File file3 = new File(file, "a.txt");

        boolean mkdir = file2.mkdir();
        boolean mkdirs = file2.mkdirs();

        try {
            boolean newFile = file3.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean delete = file3.delete();

        String[] list = file.list();
        File[] files = file.listFiles();
        assert files != null;
        for (File tmpFile : files) {
            System.out.println(tmpFile);
        }

        File[] systemFileRoots = File.listRoots();
        for (File systemFileRoot : systemFileRoots) {
            System.out.println(systemFileRoot);
        }
    }
}
