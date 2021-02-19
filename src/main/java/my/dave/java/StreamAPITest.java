package my.dave.java;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Xu Rui
 * @date 2021/2/19 15:34
 */
public class StreamAPITest {
    @Test
    public void test1(){
        //1.Collection
        Collection<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        //返回一个顺序流
        Stream<Integer> stream = list.stream();
        //返回一个并行流
        Stream<Integer> integerStream = list.parallelStream();

        //2.Arrays
        IntStream stream1 = Arrays.stream(new int[]{1, 2, 3, 4, 5});

        //3.Stream
        Stream<Integer> integerStream1 = Stream.of(1, 2, 3, 4, 5, 6);

        //4.无限流
        //迭代
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);
        //创建
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    @Test
    public void test2(){

    }
}
