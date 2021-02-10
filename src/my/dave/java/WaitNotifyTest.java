package my.dave.java;

/**
 * @author Xu Rui
 * @date 2021/2/10 19:25
 */
//  两线程交替打印1-100
class PrintNumber implements Runnable{
    private int number = 1;
    @Override
    public void run() {
        while (true){
            synchronized (this) {
                notify();

                if(number <= 100){
                    System.out.println(Thread.currentThread().getName() + ":" + number++);
                }
                else break;

                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class WaitNotifyTest {
    public static void main(String[] args) {
        PrintNumber pn = new PrintNumber();

        Thread t1 = new Thread(pn);
        Thread t2 = new Thread(pn);

        t1.setName("线程1");
        t2.setName("线程2");

        t1.start();
        t2.start();
    }
}
