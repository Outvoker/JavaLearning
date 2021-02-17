package my.dave.java;

/**
 * @author Xu Rui
 * @date 2021/2/13 16:21
 */
public class StringTest {
    public static void main(String[] args) {
        String s1 = "javaEE";
        String s2 = "Hadoop";
        String s3 = s1 + s2;
        String s4 = s1 + s2;
        String s5 = "javaEE" + "Hadoop";
        String s6 = s1.intern() + s2.intern();

        System.out.println(s3 == s4);
        System.out.println(s5 == s6);
        System.out.println(s3.intern() == s5);

        String s = "test";
        Person p = new Person("b");
        change(s, p);
        System.out.println(s);
        System.out.println(p.name);
    }

    public static void change(String s, Person p){
        s = "abc";
        p = new Person("a");
    }
}
