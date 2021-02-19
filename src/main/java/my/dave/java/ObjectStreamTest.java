package my.dave.java;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Objects;

/**
 * @author Xu Rui
 * @date 2021/2/18 14:33
 */
public class ObjectStreamTest {
    public static void main(String[] args) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("car.txt"));
            oos.writeObject(new Car("car1", 1));
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(oos != null)
                    oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void test(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("car.txt"));
            Car car = (Car) ois.readObject();
            System.out.println(car);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(ois != null)
                    ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
@Data
@AllArgsConstructor
class Car implements Serializable{
    String name;
    Integer id;
}
