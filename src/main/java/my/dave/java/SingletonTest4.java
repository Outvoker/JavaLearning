package my.dave.java;

/**
 * @author Xu Rui
 * @date 2021/2/8 18:43
 */
public class SingletonTest4 {

}

//懒汉式（线程安全）
class Singleton4{
    private static Singleton4 instance = null;

    private Singleton4(){
    }

    public static Singleton4 getInstance(){
        if (instance == null) {
            synchronized (Singleton4.class){
                if(instance == null) instance = new Singleton4();
            }
        }
        return instance;
    }
}