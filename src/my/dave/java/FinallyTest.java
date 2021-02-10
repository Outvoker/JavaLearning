package my.dave.java;

import org.junit.jupiter.api.Test;

import javax.imageio.IIOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Xu Rui
 * @date 2021/2/10 9:36
 */
public class FinallyTest {
    @Test
    public void test1(){
        FileInputStream fis = null;
        try {
            File file = new File("1.txt");
            fis = new FileInputStream(file);

            int data = fis.read();
            while(data != -1){
                System.out.println((char) data);
                data = fis.read();
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
