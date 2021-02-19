package my.dave.java;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Xu Rui
 * @date 2021/2/19 10:52
 */
public class ReflectionTest {
    @Test
    public void test1() throws Exception {
        Class clazz = P.class;
        //1.通过反射，创建P类的对象
        Constructor constructor = clazz.getConstructor(String.class, int.class);
        Object obj = constructor.newInstance("Tom", 12);
        P p = (P) obj;
        System.out.println(p);
        //2.通过反射，调用对象指定的属性、方法
        //调用属性
        Field age = clazz.getDeclaredField("age");
        age.set(p, 10);
        System.out.println(p);
        //调用方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p);

        //通过反射，可以调用P类的私有结构
        Constructor constructor1 = clazz.getDeclaredConstructor(Class.forName("name"));
        constructor1.setAccessible(true);
        P p1 = (P) constructor1.newInstance("Jerry");
        System.out.println(p1);

        //调用私有属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p1, "HanMeiMei");
        System.out.println(p1);
    }
}

@Data
@AllArgsConstructor
class P{
    private String name;
    public int age;

    private P(String name){
        this.name = name;
    }

    public void show(){
        System.out.println("show " + this.toString());
    }
}
