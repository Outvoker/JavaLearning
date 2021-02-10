package my.dave.java;

/**
 * @author Xu Rui
 * @date 2021/2/8 18:43
 */
public class SingletonTest2 {

}

//懒汉式
class Singleton2{
    private static Singleton2 instance = null;

    private Singleton2(){
    }

    public Singleton2 getInstance(){
        if(instance == null) instance = new Singleton2();
        return instance;
    }


}