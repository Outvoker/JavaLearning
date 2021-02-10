package my.dave.java;

/**
 * @author Xu Rui
 * @date 2021/2/10 13:43
 */
public class ThreadTest {
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                Thread.yield();
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }).start();
        for (int i = 0; i < 100; i++) {
            Thread.yield();
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}
