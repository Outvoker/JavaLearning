package my.dave.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * @author Xu Rui
 * @date 2021/2/16 22:13
 */
public class PropertiesTest {
    public static void main(String[] args) throws Exception {
        Properties pros = new Properties();
        FileInputStream fis = new FileInputStream("jdbc.properties");
        pros.load(fis);

        String name = pros.getProperty("name");
        String password = pros.getProperty("password");

        System.out.println(name);
        System.out.println(password);
        fis.close();
    }
}
