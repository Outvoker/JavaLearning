package my.dave.java;

/**
 * @author Xu Rui
 * @date 2021/2/8 18:37
 */
public class SingletonTest1 {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    Singleton1.getInstance().increase();;
                }
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Singleton1.getInstance().getNum());
    }
}

//饿汉式
class Singleton1{
    private static Singleton1 instance = new Singleton1();
    private int num = 0;

    private Singleton1(){
    }

    public static Singleton1 getInstance(){
        return instance;
    }

    public void increase(){
        num++;
    }

    public int getNum() {
        return num;
    }
}