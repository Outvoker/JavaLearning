# JAVA

## 面向对象

### import关键字

- 使用"xxx.*"方式表明可以调用xxx包下的所有结构。但是如果使用的是xxx子包下的结构，则仍需要显示import
- import static: 导入指定类或接口的静态结构：属性或方法。

### == 和 equals()的区别

#### == 运算符

1. 可以使用在基本数据类型变量和引用数据类型变量中
2. 如果比较的是基本数据类型变量：比较两个变量保存的数据是否相等（不一定类型相同，存在自动类型转换）；
   如果比较的是引用数据类型变量，比较两个对象的地址值是否相同，即两个引用是否指向同一个对象实体。

#### equals()方法

1. 是一个方法，而非运算符
2. 只能适用于引用数据类型
3. Object类中equals() 直接 return this == obj;
4. 像String、Date、File、包装类都重写了Object中的equals()方法。重写以后，比较的不是两个引用的地址是否相同，而是比较两个对象的“实体内容”是否相同。

### 三元运算符

java要求三元运算符 ":" 两侧的类型相同，如果是基本类型，涉及到自动类型提升。

```java
    Object o1  = true ? new Integer(1) : new Double(2.0);
    System.out.println(o1);
```

### 包装类

```java
    @Test
    public void test2(){
        Integer i = new Integer(1);
        Integer j = new Integer(1);
        System.out.println(i == j);

        Integer m = 1;
        Integer n = 1;
        System.out.println(m == n);

        Integer x = 128;
        Integer y = 128;
        System.out.println(x == y);
    }
```

1. 对象==号比较的是地址
2. -128~127有IntegerCache类提前缓存，因此地址相同

### static关键词

1. 静态变量随着类的加载而加载。可以通过"类.静态变量"的方式进行调用
2. 静态变量的加载要早于对象的创建。
3. 由于类只会加载一次，则静态变量在内存中也只会存在一份：**存在方法区的静态域中**。

### 类中代码块

1. 代码块的作用：用来初始化类、对象
2. 代码块如果有修饰的话，只能使用static
3. 分类：静态代码块 vs 非静态代码块
   1. 静态代码块：
      - 随着类的加载而执行
      - 作用：初始化类的信息
      - 如果一个类中定义了多个静态代码块，则按照声明的先后顺序执行
   2. 非静态代码块：
      - 随着对象的创建而执行
      - 每创建一个对象，就执行一次
      - 作用：可以在创建对象时，对对象的属性进行初始化
      - 如果一个类中定义了多个非静态代码块，按照顺序执行

```java
public class BlockTest {
    public static void main(String[] args) {
        //  静态代码块执行
        String desc = Person.desc;


        Person p =
                //  非静态代码块执行
                new Person("1");
    }
}

class Person{
    String name;
    static String desc = "im a person.";

    //  static代码块
    static {
        System.out.println("hello, static block");
    }

    //  非static代码块
    {
        System.out.println("hello, block");
    }

    public static void info(){
        System.out.println("im a happy person");
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

开发中例如数据连接池

![20210209110301](http://ruiimg.hifool.cn/img20210209110301.png)

赋值顺序：
1. 默认初始化
2. **显式初始化 / 在代码块中赋值**（看在对象中的顺序了，如果代码块在前会出现"先赋值再声明"的情况）
3. 构造器中初始化
4. 有类对象以后，通过对对象属性或者使用对象的方法进行赋值。

### final

1. final可以用用来修饰的结构：类、方法、变量
2. 修饰类：表示此类不能被其他类所**继承** 比如String类、System类、StringBuffer类
3. final用来修饰方法：表示该方法不可以再被**重写**了 比如getClass()
4. final修饰变量：此时的“变量”成为一个常量
   1. final修饰的属性：可以赋值的位置：显示初始化，代码块中初始化，构造器中初始化
   2. final修饰局部变量：
      - final修饰形参时，表明此形参是一个常量。当我们调用此方法时，给常量形参赋一个实参。

### 接口

1. 接口中定义的静态方法，只能通过接口来调用。
2. 通过实现类的对象，可以调用接口中的默认方法。
   如果实现类重写了接口中的默认方法，调用时，仍然调用的是重写以后的方法
3. 如果子类（或实现类）继承的父类和实现的接口中声明了同名同参数的默认方法，那么子类在没有重写此方法的情况下，默认调用的是父类中的同名同参数的方法。-->类优先原则
4. 如果实现类实现了多个接口，而这个接口中定义了同名同参数的默认方法，那么在实现类没有重写此方法的情况下，报错-->接口冲突。这就必须重写此方法。
5. 如何在子类（或实现类）的方法中调用父类、接口中被重写的方法
   ![20210209142310](http://ruiimg.hifool.cn/img20210209142310.png)

### 接口和抽象类的区别

#### 相同点

- 不能实例化
- 都可以包含抽象方法

#### 不同点

- 抽象类和接口的定义、内部结构的解释说明
  - 抽象类：有构造器，可以定义属性，可以定义抽象方法
  - 接口：jdk7之前只能定义常量、抽象方法；jdk8可以定义静态方法、默认方法；jdk9可以定义私有方法。
- 类：单继承性  接口：多继承
  - 类与接口：多实现

### 内部类

关注三个问题：
1. 如何实例化成员内部类的对象
2. 如何在成员内部类中区分调用外部类的结构
3. 开发中局部内部类的使用

```java
public class InnerClassTest {
    public static void main(String[] args) {
        //  创建静态的成员内部类Dog
        Person1.Dog dog = new Person1.Dog();
        dog.show();
        Person1 person1 = new Person1();
        Person1.Bird bird = person1.new Bird();
        bird.sing();
    }

    //  开发中局部内部类的使用
    public <T> Comparable<T> getComparable(){
        //  方式一
        class MyComparable implements Comparable<T>{
            @Override
            public int compareTo(T o) {
                return 0;
            }
        }
        return new MyComparable();

        //  方式二

//        return new Comparable<T>() {
//            @Override
//            public int compareTo(T o) {
//                return 0;
//            }
//        }
    }
}

class Person1{
    String name;
    int age;

    public void eat(){
        System.out.println("人吃饭");
    }

    static class Dog{
        String name;
        int age;

        public void show(){
            System.out.println("卡拉是条狗");
        }
    }

    class Bird{
        String name;
        int age;
        public Bird(){

        }

        public void sing(){
            System.out.println("我是一只小小鸟");
            Person1.this.eat();
            eat();
        }

        public void display(String name){
            System.out.println(name);   //  方法形参
            System.out.println(this.name);  //  内部类的属性
            System.out.println(Person1.this.name);  //  外部类的属性
        }

    }
}


```

在局部内部类的方法中，如果调用声明局部内部类的方法中的局部变量的话，要求此局部变量声明为final

```java
void method(){
    int num = 10;
    class A{
        void print(){
            System.out.println(num);    //  num自动变为final
        }
    }
}
```

## 异常

异常包括**Error**(栈溢出（StackOverflowError），OOM（Out of Mem）)和Exception

### 分类

Exception分为两种

![20210210091200](http://ruiimg.hifool.cn/img20210210091200.png)

![20210210091613](http://ruiimg.hifool.cn/img20210210091613.png)

### finally

- finally中声明的是一定会被执行的代码。即使catch中又出现异常了 / try中有return语句 / catch中有return语句等情况，也会被执行。
- 像数据库连接、输入输出流、网络变成socket等资源，JVM是不能自动回收的，我们需要手动的进行资源释放。此时资源释放就需要声明在finally里面。

### 开发中如何选择使用try-catch-fianlly还是throws

- 如果父类中被重写的方法没有thorws方式处理异常，则子类重写的方法也不能使用throws，意味着如果子类重写的方法中有异常，必须使用try-catch-finally方式处理。
- 执行的方法a中，先后调用了另外的几个方法，这几个方法是递进关系执行的。我们建议这几个方法使用thorws的方式进行处理。而执行的方法a可以考虑使用try-catch-finally方式进行处理。

### throw 和 throws

throw与throws的比较
1. throws出现在方法函数头；而throw出现在函数体。

2. throws表示出现异常的一种可能性，并不一定会发生这些异常；throw则是抛出了异常，执行throw则一定抛出了某种异常对象。

3. 两者都是消极处理异常的方式（这里的消极并不是说这种方式不好），只是抛出或者可能抛出异常，但是不会由函数去处理异常，真正的处理异常由函数的上层调用处理。

## JavaBean

- JavaBean是一种Java语言写成的可重用组件。
- 所谓JavaBean，是指符合如下标准的Java类：
  - 类是公共的
  - 有一个无参的公共的构造器
  - 有属性，且有对应的get、set方法
- 用户可以使用JavaBean将功能、处理、值、数据库访问和其他任何可以用Java代码创造的对象进行打包，并且其他的开发者可以通过内部的JSP页面、Servle、其他JavaBean、applet程序或者应用来使用这些对象。用户可以认为JavaBean提供了一种随时随地的复制和粘贴的功能，而不用关心任何改变。

## MVC设计模式

MVC是常用的设计模式之一，将整个程序分为三层：**视图模型层，控制器层，数据模型层**。这种将程序输入输出、数据处理，以及数据的展示分离开来的设计模式使程序结构变得灵活而且清晰，同时也描述了程序各个对象间的通信方式，降低了程序的耦合性。

### 模型层 model 主要处理数据

- 数据对象封装  model.bean / domain
- 数据库操作类  model.dao
- 数据库        model.db

### 控制层 controller 处理业务逻辑

- 应用界面相关  controller.activity
- 存放fragment  controller.fragment
- 显示列表的适配器  controller.adapter
- 服务相关的    controller.service
- 抽取的基类    controleer.base

### 视图层 view 显示数据

- 相关工具类    view.util
- 自定义view    view.ui

## Java继承

### 重写

- 返回值类型：
  - 父类被重写的方法的返回值是void，则子类重写的方法的返回值类型只能是void
  - 父类被重写的返回值类型是A类型，则子类重写的方法的返回值可以是**A类或A类的子类**
  - 父类被重写的方法的返回值类型是基本数据类型，则子类重写的方法的返回值类型必须是相同的基本类型
  - 子类重写的方法抛出的异常类型不大于父类被重写的方法抛出的异常类型
  - 子类和父类中的同名同参数的方法要么都声明为非static的（考虑重写），要么都声明为static的（不是重写）

## 设计模式

设计模式：设计模式是在大量的实践中总结和理论优化之后优选的代码结构、编程风格、以及解决问题的思考方式。设计模式免去我们自己再思考和摸索。

- 创建型模式，共5种：
  - 工厂方式模式、抽象工厂模式、单例模式、建造者模式、原型模式
- 结构型模式，共7种：
  - 适配器模式、装饰器模式、代理模式、外观模式、桥接模式、组合模式、享元模式
- 行为型模式，共11种：
  - 策略模式、模板方式模式、观察者模式、迭代子模式、责任链模式、命令模式、备忘录模式、状态模式、访问者模式、中介者模式、解释器模式

### 单例（Singleton）设计模式

所谓类的单例设计模式，就是采取一定的方法保证在整个的软件系统中，对某个类**只能存在一个对象实例**，并且该类只提供一个取得其对象实例的方法。

如果我们要让类在一个虚拟机中只能产生一个对象，我们
首先必须将类的**构造器的访问权限设置成private**，这样，就不能用new操作符在类的外部产生类的对象了，但在类内部仍可以产生该类的对象。因为在类的外部开始还无法得到类的对象，只能**调用该类的某个静态方法**以返回类内部创建的对象，静态方法只能访问类中的静态成员变量，所以，指向类内部产生的**该类的变量也必须定义成静态的**。

- 优点：由于单例模式只生成一个实例，**减少了系统性能开销**，当一个对象的产生需要比较多的资源时，如读取配置、产生其他依赖对象时，则可以通过在应用启动时直接产生一个单例对象，然后永久驻留内存的方式来解决。

- 应用场景
  - **网站的计数器**：一般时单例模式实现，否则难以同步
  - **应用程序的日志应用**：一般都使用单例模式实现，这一般是由于共享的日志文件一直处于打开状态，因为只能有一个实例去操作，否则内容不好追加
  - **数据库连接池**的设计一般也是采用单例模式，因为数据库连接是一种数据库的资源。
  - 项目中，**读取配置文件的类**，一般也只用一个对象。没有必要每次使用配置文件数据，都生成一个对象去读取。
  - **Application**也是单例的典型应用。
  - Windows的**Task Manager（任务管理器）**就是很典型的单例模式。
  - Windows的**Recycle Bin（回收站）**也是典型的单例应用。在整个系统运行过程中，回收站一直维护着仅有的一个实例。

#### 饿汉式

```java
class Bank{
    private static Bank instance = new Bank();

    private Bank(){
    }

    public static Bank getInstance(){
        return instance;
    }
}
```

#### 懒汉式

```java
class Singleton2{
    private static Singleton2 instance = null;

    private Singleton2(){
    }

    public static Singleton2 getInstance(){
        if(instance == null) instance = new Singleton2();
        return instance;
    }
}
```

#### 饿汉式 vs 懒汉式

- 饿汉式：
  - 坏处：对象加载时间过长。
  - 好处：线程安全。
- 懒汉式：
  - 好处：延迟对象的创建。
  - 坏处：线程不安全。

### 模板方法设计模式（TemplateMethod）

抽象类体现的就是一种模板模式的设计，抽象类作为多个子类的通用模板，子类再抽象类的基础上进行扩展、改造，但子类总体上会保留抽象类的行为方式。

**钩子方法**，**回调方法**

![20210209120209](http://ruiimg.hifool.cn/img20210209120209.png)

### 代理模式（Proxy）

接口的应用：

代理模式是Java开发中使用较多的一种设计模式。代理设计就是为其他对象提供一种代理以控制对这个对象的访问。

```java
public class ProxyTest {
    public static void main(String[] args) {

        Server server = new Server();
        ProxyServer proxyServer = new ProxyServer(server);
        proxyServer.browse();
    }
}

interface NetWork{
    void browse();
}

//  被代理类
class Server implements NetWork{

    @Override
    public void browse() {
        System.out.println("真实的服务器访问网络");
    }
}

//  代理类
class ProxyServer implements NetWork{

    private NetWork work;

    ProxyServer(NetWork work){
        this.work = work;
    }

    public void check(){
        System.out.println("联网之前的检查工作");
    }

    @Override
    public void browse() {
        check();
        work.browse();
    }
}
```

#### 应用场景

- 安全代理：屏蔽对真实角色的直接访问。
- 远程代理：通过代理类处理远程方法调用（RMI）
- 延迟加载：先加载轻量级的代理对象，真正需要再加载真实对象
  - 比如你要开发一个大文档查看软件，大文档中有大的图片，有可能一个图片有100MB，在打开文件时，不可能将所有的图片都显示出来，这样就可以使用代理模式，当需要查看图片时，用proxy来进行大图片的打开。

#### 分类

- 静态代理（静态定义代理类）
- 动态代理（动态生成代理类）JDK自带

### 工厂设计模式

接口的应用

工厂模式： 实现了创建者与调用者的分离，即将创建对象的具体过程屏蔽隔离起来，达到提高灵活性。

#### 分类

- 简单工厂模式：用来生产同一等级结构中的任意产品。（对于增加新的产品，需要修改已有代码）
- 工厂方法模式：用来生产同一等级结构中的固定产品。（支持增加任意产品）
- 抽象工厂模式：用来生产不同产品族的全部产品。（对于增加新的产品，无能为力；支持增加产品族）

## 多线程

### 内存区域

![20210210120459](http://ruiimg.hifool.cn/img20210210120459.png)

**虚拟机栈**和**程序计数器**每个线程有一份

**方法区**和**堆**整个进程只有一份，多个线程共享

- 进程作为资源分配的单位，系统在运行时会为每个进程分配不同的内存区域
- 线程作为调度和执行的单位，每个线程拥有独立的运行栈和程序计数器，线程切换的开销小。

### 创建多线程的方法

#### 继承Thread类

1. 创建一个继承于Thread类的子类
2. 重写Thread类的run()
3. 创建Thread类的子类的对象
4. 通过此对象调用start()

#### 实现Runnable接口

1. 创建一个实现了Runnable接口的类
2. 实现类实现Runnable中的抽象方法：run()
3. 创建实现类的对象
4. 将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
5. 通过Thread类的对象调用start()

- 实现接口比继承好：
  1. 实现的方式没有类的单继承性的局限性
  2. 实现的方式更适合来处理多个线程有共享数据的情况
- 联系：Thread类实现了Runnable接口
- 相同点：两种方法都需要重写run()，将线程要执行的逻辑声明在run()中

### 守护线程

线程分为**用户线程**和**守护线程**

- 守护线程是用来服务用户线程的，通过在start()方法前调用，`thread.setDaemon(true)`可以把一个用户线程变成一个守护线程。
- Java垃圾回收就是一个典型的守护线程。
- 若JVM中都是守护线程，当前JVM将退出。
- 一个Java应用程序java.exe，至少有三个线程：main()主线程（用户线程），gc()垃圾回收线程（守护线程），异常处理线程