package my.dave.java;

/**
 * @author Xu Rui
 * @date 2021/2/19 12:46
 */
public class ProxyTest {
    public static void main(String[] args) {
        Calc test = new Calc();
        RunTimeLog<CalcInterface> timeLog = new RunTimeLog<>(test);
        CalcInterface proxyInstance = timeLog.getProxyInstance();
        int value = proxyInstance.calcResult(100000000);
        System.out.println("用时: " + timeLog.getSpendTime());
        proxyInstance.calc();
        System.out.println("用时: " + timeLog.getSpendTime());
    }
}
interface CalcInterface{
    void calc();
    void calc(int times);
    int calcResult();
    int calcResult(int times);

}
class Calc implements CalcInterface{
    @Override
    public void calc(){
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
    }

    @Override
    public void calc(int times){
        int sum = 0;
        for (int i = 0; i < times; i++) {
            sum += i;
        }
    }

    @Override
    public int calcResult(){
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }

    @Override
    public int calcResult(int times){
        int sum = 0;
        for (int i = 0; i < times; i++) {
            sum += i;
        }
        return sum;
    }
}