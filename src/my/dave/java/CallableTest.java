package my.dave.java;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Xu Rui
 * @date 2021/2/10 20:02
 */
class NumThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0){
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}

public class CallableTest {
    public static void main(String[] args) {
        NumThread numThread = new NumThread();
        FutureTask<Integer> futureTask = new FutureTask<>(numThread);

        new Thread(futureTask).start();

        try {
            Object sum = futureTask.get();
            System.out.println(sum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
