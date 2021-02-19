package my.dave.java;

/**
 * @author Xu Rui
 * @date 2021/2/10 19:42
 */
class Product{

    private int productCount = 0;

    //生产产品
    public synchronized void produce() {
        if(productCount < 20){
            System.out.println(Thread.currentThread().getName() + ":开始生产第" + ++productCount + "个产品");
            notify();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //消费产品
    public synchronized void consume() {
        if(productCount > 0){
            System.out.println(Thread.currentThread().getName() + ":开始消费第" + productCount-- + "个产品");
            notify();
        }else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer implements Runnable{

    private Product product;

    public Producer(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "：开始生产产品");
        while (true){
            product.produce();
        }
    }
}

class Consumer implements Runnable{

    private Product product;

    public Consumer(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "：开始消费产品");
        while (true){
            product.consume();
        }
    }
}

public class ProductTest {
    public static void main(String[] args) {
        Product product = new Product();

        Producer p1 = new Producer(product);
        Consumer c1 = new Consumer(product);

        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(c1);

        t1.setName("生产者1");
        t2.setName("消费者1");

        t1.start();
        t2.start();
    }
}
