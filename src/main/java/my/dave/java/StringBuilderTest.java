package my.dave.java;

import java.util.Date;

/**
 * @author Xu Rui
 * @date 2021/2/15 14:34
 */
public class StringBuilderTest {
    public static void main(String[] args) {
        String str = null;
        StringBuilder sb = new StringBuilder();
        sb.append(str);

        System.out.println(sb.length());    //  4

        System.out.println(sb);     //  "null"

        StringBuilder sb1 = new StringBuilder(str); //exception
        System.out.println(sb1);


    }
}

enum a{

}
