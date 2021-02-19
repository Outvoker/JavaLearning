package my.dave.java;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Xu Rui
 * @date 2021/2/16 14:33
 */
public class BufferedStreamTest {
    public static void main(String[] args) {
        fileCopy("# JAVA.md", "D:\\university\\work\\学\\面经\\java\\# JAVA.md");
    }

    public static void fileCopy(String srcPath, String destPath){
        //1. 造文件
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File srcFile = new File(srcPath);
            File destFile = new File(destPath);
            //2. 造流
            //2.1 造节点流
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);
            //2.2 造缓冲流
            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            //3. 复制的细节：读取，写入
            byte[] buffer = new byte[1024];
            int len;
            while((len = bis.read(buffer)) != -1)
                bos.write(buffer, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            //        bis.close();
//        bos.close();
            //说明：关闭外层流的同时，内层流也会自动的进行关闭。
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

