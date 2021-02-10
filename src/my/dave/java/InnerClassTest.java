package my.dave.java;

/**
 * @author Xu Rui
 * @date 2021/2/9 17:45
 */
public class InnerClassTest {
    void method(){
        int num = 10;
        class A{
            void print(){
                System.out.println(num);    //
            }
        }
    }

    public static void main(String[] args) {
        //  创建静态的成员内部类Dog
        Person1.Dog dog = new Person1.Dog();
        dog.show();
        Person1 person1 = new Person1();
        Person1.Bird bird = person1.new Bird();
        bird.sing();
    }

    //  开发中局部内部类的使用
    public <T> Comparable<T> getComparable(){
        //  方式一
        class MyComparable implements Comparable<T>{
            @Override
            public int compareTo(T o) {
                return 0;
            }
        }
        return new MyComparable();

        //  方式二

//        return new Comparable<T>() {
//            @Override
//            public int compareTo(T o) {
//                return 0;
//            }
//        }
    }
}

class Person1{
    String name;
    int age;

    public void eat(){
        System.out.println("人吃饭");
    }

    static class Dog{
        String name;
        int age;

        public void show(){
            System.out.println("卡拉是条狗");
        }
    }

    class Bird{
        String name;
        int age;
        public Bird(){

        }

        public void sing(){
            System.out.println("我是一只小小鸟");
            Person1.this.eat();
            eat();
        }

        public void display(String name){
            System.out.println(name);   //  方法形参
            System.out.println(this.name);  //  内部类的属性
            System.out.println(Person1.this.name);  //  外部类的属性
        }

    }
}
