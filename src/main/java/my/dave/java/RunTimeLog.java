package my.dave.java;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Xu Rui
 * @date 2021/2/19 12:52
 */

/**
 * 该类可以代理任何一个类使得类中的每个方法执行后都会输出执行时间
 */
@Data
public class RunTimeLog<T> {

    @Getter
    private final T proxyInstance;

    @Getter
    private long spendTime;

    @SuppressWarnings("unchecked")
    RunTimeLog(T obj){
        proxyInstance =  (T) Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        long start = System.currentTimeMillis();
                        //method即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法。
                        Object returnValue = method.invoke(obj, args);
                        long end = System.currentTimeMillis();
                        spendTime = end - start;
                        return returnValue;
                    }
                }
        );
    }
}
