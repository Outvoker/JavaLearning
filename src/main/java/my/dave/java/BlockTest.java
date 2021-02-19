package my.dave.java;

/**
 * @author Xu Rui
 * @date 2021/2/9 10:46
 */
public class BlockTest {
    public static void main(String[] args) {
        //  静态代码块执行
        String desc = Person.desc;


        Person p =
                //  非静态代码块执行
                new Person("1");
    }
}

class Person{
    String name;
    static String desc = "im a person.";

    //  static代码块
    static {
        System.out.println("hello, static block");
    }

    //  非static代码块
    {
        System.out.println("hello, block");
    }

    public static void info(){
        System.out.println("im a happy person");
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

interface Flyable{
    int a = 1;
    void fly();
}

interface plane extends Flyable {

}