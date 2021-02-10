package my.dave.java;

import java.util.Scanner;

/**
 * @author Xu Rui
 * @date 2021/2/9 14:32
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int a;
        int b;
        System.out.println("请输入第一个数：");
        a = in.nextInt();
        System.out.println("请输入第二个数：");
        b = in.nextInt();
        System.out.println("两个数相乘等于：");
        System.out.println(a * b);
    }
}
