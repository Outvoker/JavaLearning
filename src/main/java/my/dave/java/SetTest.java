package my.dave.java;

import java.util.*;

/**
 * @author Xu Rui
 * @date 2021/2/15 11:42
 */
public class SetTest {
    public static void main(String[] args) {
        HashSet<User> set = new HashSet<>();
        User u1 = new User("AA", 1001);
        User u2 = new User("BB", 1002);

        set.add(u1);
        set.add(u2);

        u1.setName("CC");
        set.remove(u1);
        System.out.println(set);

        set.add(new User("CC", 1001));
        System.out.println(set);

        set.add(new User("AA", 1001));
        System.out.println(set);

    }
}

class User{
    private String name;
    private int no;

    public User(String name, int no) {
        this.name = name;
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return no == user.no && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, no);
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", no=" + no +
                '}';
    }
}
