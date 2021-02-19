package my.dave.java;

/**
 * @author Xu Rui
 * @date 2021/2/8 18:43
 */
public class SingletonTest3 {

}

//懒汉式（线程安全）
class Singleton3{
    private static Singleton3 instance = null;

    private Singleton3(){
    }

    public static synchronized Singleton3 getInstance(){
        if(instance == null) instance = new Singleton3();
        return instance;
    }
}