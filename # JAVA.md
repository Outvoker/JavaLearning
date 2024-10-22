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

#### 懒汉式（线程安全，效率较低）

```java
class Singleton3{
    private static Singleton3 instance = null;

    private Singleton3(){
    }

    public static synchronized Singleton3 getInstance(){
        if(instance == null) instance = new Singleton3();
        return instance;
    }
}
```

- 效率低：对象创建后还是同时只有一个线程可以获取对象

#### 懒汉式（线程安全，效率较高）

```java
class Singleton4{
    private static Singleton4 instance = null;

    private Singleton4(){
    }

    public static Singleton4 getInstance(){
        if (instance == null) {
            synchronized (Singleton4.class){
                if(instance == null) instance = new Singleton4();
            }
        }
        return instance;
    }
}
```

- 双重检查

- 效率稍高：一开始可能有多个线程阻塞，对象创建好之后，新来的线程不再阻塞

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

#### 反射的应用——动态代理

- 动态代理是指客户通过代理类来调用其它对象的方法，并且是在程序运行时根据需要动态创建目标类的代理对象。
- 动态代理使用场合
  - 调试
  - 远程方法调用
- 动态代理相比于静态代理的优点
  - 抽象角色中（接口）声明的所有方法都被转移到调用处理器一个集中的方法中代理，这样，我们可以更加灵活和同一的处理众多的方法。

```java
/**
 * 该类可以代理任何一个类使得类中的每个方法执行后都会输出执行时间
 */
public class RunTimeLog {
    //调用此方法，返回一个代理类的对象
    @SuppressWarnings("unchecked")
    public static <T> T getProxyInstance(T obj) { //obj为被代理对象
        return (T) Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        long start = System.currentTimeMillis();
                        //method即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法。
                        Object returnValue = method.invoke(obj, args);
                        long end = System.currentTimeMillis();
                        System.out.println("方法" + obj.getClass().getName() + "." + method.getName() + "() 用时: " +  (end - start) + " ms");
                        return returnValue;
                    }
                }
        );
    }
}
```

### 工厂设计模式

接口的应用

工厂模式： 实现了创建者与调用者的分离，即将创建对象的具体过程屏蔽隔离起来，达到提高灵活性。

#### 分类

- 简单工厂模式：用来生产同一等级结构中的任意产品。（对于增加新的产品，需要修改已有代码）
- 工厂方法模式：用来生产同一等级结构中的固定产品。（支持增加任意产品）
- 抽象工厂模式：用来生产不同产品族的全部产品。（对于增加新的产品，无能为力；支持增加产品族）

### MVC设计模式

MVC是常用的设计模式之一，将整个程序分为三层：**视图模型层，控制器层，数据模型层**。这种将程序输入输出、数据处理，以及数据的展示分离开来的设计模式使程序结构变得灵活而且清晰，同时也描述了程序各个对象间的通信方式，降低了程序的耦合性。

#### 模型层 model 主要处理数据

- 数据对象封装  model.bean / domain
- 数据库操作类  model.dao
- 数据库        model.db

#### 控制层 controller 处理业务逻辑

- 应用界面相关  controller.activity
- 存放fragment  controller.fragment
- 显示列表的适配器  controller.adapter
- 服务相关的    controller.service
- 抽取的基类    controleer.base

#### 视图层 view 显示数据

- 相关工具类    view.util
- 自定义view    view.ui

### 迭代器设计模式

提供一种方法访问一个容器对象中各个元素，而又不需暴露该对象的内部细节。

### 装饰设计模式

- 装饰模式是动态的给一个对象添加一些额外的功能，就增加功能来说，装饰模式比生成的子类更为灵活。
- 装饰模式是在不必改变原类文件和使用继承的情况下，动态的扩展一个对象的功能。提供比继承更多的灵活性
- 装饰模式是创建一个包装对象，也就是使用装饰来包裹真实的对象。
- 举例：缓冲流

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

#### 实现Callable接口

与Runnable相比，Callable功能更强大些
- 相比run()方法，可以有返回值
- 方法可以抛出异常
- 支持泛型的返回值
- 需要借助FutureTask类，比如获取返回结果

Future接口
- 可以对具体Runnable、Callable任务的执行结果进行取消、查询是否完成、获取结果等。
- FutureTask使Future接口的唯一实现类
- FutureTask同时实现了Runnable，Future接口。它既可以作为Runnable被线程执行，又可以作为Future得到Callable的返回值

方法：
1. 创建一个实现Callable的实现类
2. 实现call方法，将此线程需要执行的操作声明在call()中
3. 创建Callable接口实现类的对象
4. 将此Callable接口实现类的对象作为参数传递到FutureTask构造器中，创建FutureTask对象
5. 将FutrueTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()
6. 获取Callable中call方法的返回值（使用FutureTask对象的get()方法）

#### 使用线程池

背景：经常创建和销毁、使用量特别大的资源，比如并发情况下的线程，对性能影响很大。

思路：提前创建好多个线程，放入线程池中，使用时直接获取，使用完放回池中。可以避免频繁创建销毁、实现重复利用。类似生活中的公共交通工具。

好处：
- 提高响应速度（减少了创建新线程的时间）
- 降低资源消耗（重复利用线程池中线程，不需要每次都创建）
- 便于线程管理
  - corePoolSize：核心池的大小
  - maximumPoolSize：最大线程数
  - keepAliveTime：线程池没有任务时最多保持多长时间后会终止

方法：

1. 提供指定线程数量的线程池
2. 执行指定的线程的操作。需要提供实现Runnable接口或者Callable接口实现类的对象（execute(), submit()）
3. 关闭线程池（shutdown()）

```java
class NumberThread implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

class NumberThread1 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 1){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;
        //设置线程池的属性(线程管理)
        service1.setMaximumPoolSize(20);

        service.execute(new NumberThread());  // 适合适用于Runnable
        service.execute(new NumberThread1());  // 适合适用于Runnable
//        service.submit();   // 适合适用于Callable
        service.shutdown();
    }
}

```

### 守护线程

线程分为**用户线程**和**守护线程**

- 守护线程是用来服务用户线程的，通过在start()方法前调用，`thread.setDaemon(true)`可以把一个用户线程变成一个守护线程。
- Java垃圾回收就是一个典型的守护线程。
- 若JVM中都是守护线程，当前JVM将退出。
- 一个Java应用程序java.exe，至少有三个线程：main()主线程（用户线程），gc()垃圾回收线程（守护线程），异常处理线程

### 线程的生命周期

![20210210163551](http://ruiimg.hifool.cn/img20210210163551.png)

### 同步

#### 同步代码块

```java
synchronized(同步监视器){
    
}
```

同步监视器 == 锁

多个线程必须要公用同一把锁

补充：
- 在实现Runnable接口创建多线程的方式中，我们可以考虑使用this充当锁
- 在继承Thread类创建多线程的方式中，慎用this，可以考虑当前类充当锁（继承类.class 是该类的对象）

#### 同步方法

在方法上添加 `synchronized` 关键字

非静态同步方法： 同步监视器：this

- 使用同步方法解决继承Thread类的多线程 --> 方法静态。相当于同步监视器是 继承类.class

#### Lock锁（JDK 5.0 新增）

![20210210175920](http://ruiimg.hifool.cn/img20210210175920.png)

如果fair参数为true，保证先进先出原则-->公平,默认false

1. 实例化ReentrantLock
2. 调用锁定方法lock()
3. 调用解锁方法unlock()

```java
public class ReentrantLockTest {
    public static void main(String[] args) {
        Window w = new Window();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class Window implements Runnable{
    private int ticket = 100;

    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while(true){
            try {
                lock.lock();

                if(ticket > 0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "售票：" + ticket--);
                }else break;
            } finally {
                lock.unlock();
            }
        }
    }

}
```

#### synchronized 与 Lock的异同

- 相同：都是用来解决线程安全问题
- 不同：
  - synchronized机制 是隐式锁 在执行完相应的同步代码以后，自动释放同步监视器
  - Lock 是显示锁 需要手动的启动同步（lock()），同时结束同步也需要手动的实现（unlock()）
  - 使用Lock锁，JVM将花费较少的时间来调度线程，性能更好。并且具有更好的扩展性（提供更多的子类）

### 线程的通信

wait(), notify(), notifyAll()

1. 必须使用在同步代码块或同步方法中
2. 这三个方法的调用者必须是同步代码块或同步方法中的同步监视器，否则会出现`IllegalMonitorStateException`异常
3. 这三个放是定义在java.lang.Object当中

```java
//  两线程交替打印1-100
class PrintNumber implements Runnable{
    private int number = 1;
    @Override
    public void run() {
        while (true){
            synchronized (this) {
                notify();

                if(number <= 100){
                    System.out.println(Thread.currentThread().getName() + ":" + number++);
                }
                else break;

                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class WaitNotifyTest {
    public static void main(String[] args) {
        PrintNumber pn = new PrintNumber();

        Thread t1 = new Thread(pn);
        Thread t2 = new Thread(pn);

        t1.setName("线程1");
        t2.setName("线程2");

        t1.start();
        t2.start();
    }
}

```

### 释放锁的操作 VS 不释放锁的操作

释放锁：
- 当前线程的同步方法、同步代码块执行结束。
- 当前线程在同步代码块、同步方法中遇到break、return终止了改代码块、该方法的执行。
- 当前线程在同步代码块、同步方法中出现了未处理的Error或Exception，导致一场结束。
- 当前线程在同步代码块、同步方法中执行了线程对象的wait()方法，当前线程暂停，并释放锁。

不会释放锁：
- 线程执行同步代码块、同步方法时，程序调用Thread.sleep()、Thread.yield()方法暂停当前线程的执行
- 线程执行同步代码块时，其他线程调用了该线程的suspend()方法将该线程挂起，该线程不会释放锁
  - 过时了

### sleep() 和 wait() 的异同

- 相同点：一旦执行方法，都可以使当前的线程进入阻塞状态。
- 不同点：
  1. 两个方法声明的位置不同：Thread类中声明sleep()，Object类中声明wait()
  2. 调用的要求不同：sleep()可以在任何需要的场景下调用。wait()必须在同步代码块中调用
  3. 关于是否释放同步监视器：如果两个方法都使用在同步代码块或同步方法中，sleep()不会释放，wait()会释放锁

### 生产者消费者问题

```java
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

```

## 常用类

### String

1. String类被声明为final，不可被继承
2. String实现了：
   1. Serializable接口：表示字符串是支持序列化的
   2. Comparable接口：表示String可以比较大小
3. String内部定义里final char[] value用于存储字符串数据
4. String：代表不可变的字符序列。不可变性。
   1. 当对字符串重新赋值时，需要重写指定内存区域赋值，不能使用原有的value进行赋值。
   2. 当对现有的字符串进行连接操作时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值。
   3. 调用String的replace()方法修改指定的字符或字符串时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值。
5. 通过字面量的方式给一个字符串赋值，此时的字符串值声明在字符串常量池中。
6. 字符串常量池中是不会存储相同内容的字符串。

#### String实例化

1. 通过字面量定义的方式:此时"javaEE"声明在方法区的字符串常量池中。
   `String s1 = "javaEE"`
2. 通过new + 构造器的方式：此时s2保存的地址值是数据在堆空间中开辟空间以后对应的的地址值。
   `String s2 = new String("javaEE")`
3. `s1 != s2` 字符串常量存储在字符串常量池，目的是共享；字符串非常量对象存储在堆中。new的对象中value存储的地址值指向的是字符串常量池中的地址。
4. 如果加final表示该变量也为一个常量，拼接的话也是相当于字面量拼接。

![20210213160518](http://ruiimg.hifool.cn/img20210213160518.png)

面试题：`String s = new String("abc");` 方式创建对象，在内存中创建了几个对象？

两个：一个是堆空间中new结构，另一个是char型数组对应的常量池中的数据"abc"

![20210213162006](http://ruiimg.hifool.cn/img20210213162006.png)

String.intern()方法返回常量池中的字符串

### String, StringBuffer, StringBuilder三者的异同

- String:
  - 不可变的字符序列
  - 底层使用`char[]`存储，final
- StringBuffer:
  - 可变的字符序列
  - 线程安全的，效率低
  - 底层使用`char[]`存储
- StringBuilder:
  - 可变的字符序列
  - 线程不安全的，效率高
  - 底层使用`char[]`存储

都继承子charSequence

- StringBuffer 和 StringBuilder都继承自AbstractStringBuilder
  - 初始化创建一个长度为16的`char[]`数组
  - 每次append需要判断容量是否足够，进行扩容（ensureCapcityInternal()）
  - 默认情况下，每次扩容为原来的2倍再加2，原有内容复制到新的数组中

面试题：

```java
public static void main(String[] args) {
    String str = null;
    StringBuilder sb = new StringBuilder();
    sb.append(str);

    System.out.println(sb.length());    //  4

    System.out.println(sb);     //  "null"

    StringBuilder sb1 = new StringBuilder(str); //exception
    System.out.println(sb1);
}
```

appendNull()

## 注解（Annotation）

是代码里的特殊标记，这些标记可以在编译，类加载，运行时被读取，并执行相应的处理。通过使用Annotation，程序员可以在不改变原有逻辑的情况下，在源文件中嵌入一些补充信息。代码分析工具、开发工具和部署工具可以通过这些补充信息进行验证或者进行部署。

框架 = 注解 + 反射 + 设计模式

### 自定义注解

1. 注解声明为：@interface
2. 内部定义成员，通常使用value表示
3. 可以指定成员的默认值，使用default定义
4. 如果自定义注解没有成员，表明是一个标识作用
5. 自定义注解必须配上注解的信息处理流程才有意义

### 元注解

对现有注解进行修饰的注解

1. Retention：只能用于修饰一个Annotation定义，用于指定该Annotation的生命周期，包含一个RetentionPolicy类型的成员变量。
   1. RetentionPolicy.SOURCE：在源文件中有效（即源文件保留），编译器直接丢弃这种策略的注释。
   2. RetrntionPolicy.CLASS：在class文件中有效（即class保留），当运行java程序时，JVM不会保留注释。这是默认值。
   3. RetrntionPolicy.RUNTIME：在运行时有效（即运行时保留），当运行java程序时，JVM会保留注释。程序可以通过反射获取该注释。
   ![20210215155956](http://ruiimg.hifool.cn/img20210215155956.png)
2. Target：用于指定被修饰的Annotation能用于修饰哪些程序元素
3. Documented：表示所修饰的Annotation在被javadoc解析时，保留下来
4. Inherited：被它修饰的Annotation将具有继承性，（子类继承父类上的注解）
5. Repeatable：可重复注解 jdk 8之后

## Java集合

java集合分为：
- Collection：单列数据，定义了存取一组对象的方法的集合
  - List：元素有序、可重复的集合
  - Set：元素无序、不可重复的集合
- Map：双列数据，保存具有映射关系“key-value对”的集合

![20210215161840](http://ruiimg.hifool.cn/img20210215161840.png)

![20210215161853](http://ruiimg.hifool.cn/img20210215161853.png)

- 向集合中添加Obj对象，要求对象重写equal()方法（contains()，remove()调用的是equal()方法进行判断）

### 迭代器

- Collection接口继承了java.lang.Iterable接口。
- iterator()方法返回一个实现了iterator接口的对象
- 集合对象每次调用iterator()方法都得到一个全新的迭代器对象

### ArrayList,LinkedList,Vector异同

- 同：三个类都实现了List接口，存储数据的特点相同：存储有序、可重复的数据
- 不同：
  - ArrayList：
    - 作为List接口的主要实现类
    - 线程不安全的，效率高
    - 底层使用`Object[] elementData`存储
  - LinkedList：
    - 底层使用双向链表存储
    - 对于频繁的插入、删除操作，使用此类效率比ArrayList效率高
  - Vector：
    - 作为List接口的古老实现类（jdk 1.0）
    - 线程安全，效率低
    - 底层使用`Object[] elementData`存储
 
#### List源码分析

- ArrayList
  - Jdk 7 之前：
    - `new ArrayList()`底层创建了长度为10的`Object[]`数组
    - `grow()`方法扩容，默认扩容为原来的1.5倍
  - Jdk 8 之后：
    - `new ArrayList()`底层`Object[] elementData`初始化为`{}`，并没有创建长度为10的数组
    - 第一次调用`add()`时，底层才创建长度为10的数组
    - 延迟数组创建，节省内存
- LinkedList
  - 内部声明了Node类型的first和last属性，默认值为null
  - 每次add(e)将e封装到新创建的Node对象中
- Vector
  - 使用synchronized保证线程安全
  - 每次扩容为原来的2倍
  - new时创建长度为10的array

### Set

- HashSet为例
1. 无序性：根据数据的哈希值存储
2. 不可重复性：保证添加的元素按照equals()判断时，不能返回true。即相同的元素只能添加一个。（自定义对象需要重写hashcode()和equals()方法才能生效）
3. 元素存储使用拉链法，jdk7之前为头插法，jdk8之后使用尾插法
4. 元素插入时先调用hashcode计算存储位置，如果存储位置相同先比较hash值，如果hash值相同再使用equals()方法，如果返回值为true则舍弃新值，否则拉链发同时存储。
5. 向Set中添加的数据，其所在类一定要重写hashCode()和equals()方法；重写的两个方法要实现对象相等规则，即：相等的对象必须具有相等的散列码
- 底层使用HashMap实现，所有value值使用PRESENT一个Object对象来填充
  ```java
    private transient HashMap<E,Object> map;

    // Dummy value to associate with an Object in the backing Map
    private static final Object PRESENT = new Object();
  ```

- HashSet
  - Set接口主要实现类
  - 线程不安全
  - 可以存储null
- LinkedHashSet
  - HashSet子类
  - 遍历其内部数据时，可以按照添加的顺序遍历
  - 在添加数据的同时，每个数据还维护了一个双向链表
  - 对于频繁的遍历操作，效率较高一些
- TreeSet
  - 使用红黑树实现
  - 可以按照添加对象指定属性进行排序
    - 自然排序，实现Comparable接口
      - 使用compareTo来比较元素是否相同
    - 定制排序，实现Comparator接口，传入TreeSet构造方法中
  - 向TreeSet中添加的数据，要求是相同类的对象。 

- 面试题：

```java
public class Test {
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

```

### hashCode()为什么选择31作为系数

1. 选择系数的时候要选择尽量大的系数。因为如果计算出来的hash地址越大，冲突就越少，查找起来效率也会提高。
2. 31只占用5bits，相乘造成数据溢出的概率较小
3. 31可以由$i * 31 == (i << 5) - 1$来表示，很多虚拟机里面都有做相关优化。提高算法效率
4. 31是一个素数，素数使得相乘后减少冲突的概率

### Map

- HashMap
  - 主要实现类
  - 线程不安全，效率高
  - 可以存储null的key和value
  - 底层实现：
    - 数组 + 链表 （jdk7之前）
    - 数组 + 链表 + 红黑树 （jdk 8）
- LinkedHashMap
  - HashMap子类
  - 保证遍历map元素时，可以按照添加的顺序实现遍历
    - 在原有的HashMap底层结构基础上，维护了双向链表
    - 对于频繁的遍历操作，此类执行效率较高
- TreeMap
  - 保证按照添加的key进行排序，实现排序遍历。
  - 底层使用红黑树
- Hashtable
  - 古老实现类
  - 线程安全，效率低
  - 不能存储null的key和value
- Properties
  - Hashtable子类
  - 常用来处理配置文件
  - key和value都是String类型

#### Map结构的理解

- Mqp中的key：
  - 无序的、不可重复的
  - 使用Set存储所有key
    - key所在的类要重写equals()和hashCode()方法
- Map中的value：
  - 无序的、可重复的
  - 使用Collection存储所有value
    - 如果使用containsValue()方法，需要重写equals()方法
- Map中的Entry：
  - 一个键值对key-value构成了一个Entry对象
  - 无序的、不可重复的
  - 使用Set存储所有entry

#### HashMap底层实现

jdk 7：

- `HashMap map = new HashMap();`
  - 在实例化以后，底层创建了长度是16的一维数组`Entry[] table`
- `map.put(key1, value1);`
  1. 调用key1所在类的hashCode()计算key1哈希值，通过计算（`h & (length - 1)`）得到Entry数组中存放的位置
  2. - 如果此位置上数据为空，则entry添加成功
     - 如果此位置上的数据不为空，比较当前位置链表上（拉链法）key1和已经存在的一个或多个数据的哈希值：
       - 如果key1的哈希值与已经存在的数据的哈希值都不相同，此时key1-value1添加成功
       - 如果key1和已经存在的某一个数据的哈希值相同，继续调用key1所在类的equals()方法
         - 如果equals()返回false：添加成功
         - 如果equals()返回true：新值替换旧值
     - 头插法
- 默认的扩容方式：扩容为原来的两倍，数据复制过来
- 数组 + 链表
- 创建的容量一定为2的整数次幂
  ```java
    int capacity = 1;
    while(capacity < initialCapacity)
        capacity <<= 1;
  ```
- 扩容的条件：map中的数据量超过了临界值（$threshold = loadFactor * cap$）`(size >= threshold) && (null != table[bucketIndex])` 并且 要存放的位置非空

jdk 8：
- `new HashMap()`时，底层还没有创建一个长度为16的数组
- jdk8底层的数组是`Node[]`，而非`Entry[]`
- 首次调用put()方法时，底层创建长度为16的数组
- 数组 + 链表 + 红黑树
  - 当某一个索引位置上的元素以链表形式存在的数据个数 > 8 （TREEIFY_THRESHOLD）且当前数组长度 > 64 （MIN_TREEIFY_CAPACITY）时，此时此索引位置上的所有数据改为使用红黑树存储
  - 查询冲突时提高效率 从$O(n) \rightarrow O(\log(n)))$
- 扩容的条件变为只要超过临界值就扩容

源码分析：<https://www.bilibili.com/video/BV1Kb411W75N?p=553&spm_id_from=pageDriver>

- DEFAULT_INITIAL_CAPCITY：HashMap默认容量16
- DEFAULT_LOAD_FACTOR：HashMap默认加载因子0.75
- threshold：扩容临界值 = 容量 * 填充因子 => 16 * 0.75 = 12
- TREEIFY_THRESHOLD：Bucket中链表长度大于该默认值，转化为红黑树：8
- MIN_TREEIFY_CAPACITY：桶中的Node被树化时最小的hash表容量：64

##### LinkedHashMap底层实现

- 在新建立节点时，重写newNode()方法
  - 新建`LinkedHashMap.Entry<K,V>`对象
  - 内部类Entry继承了`HashMap.Node<K,V>`
  - 增加befor, after引用，记录先后插入的顺序

#### TreeMap

- 向TreeMap中添加key-value，要求key必须是由用一个类创建的对象
- 实现自然排序或者定制排序

#### Properties

jdbc.properties
- =周围不能有空格

```properties
name=Tom
password=abc123
```

```java
public class PropertiesTest {
    public static void main(String[] args) throws Exception {
        Properties pros = new Properties();
        FileInputStream fis = new FileInputStream("jdbc.properties");
        pros.load(fis);

        String name = pros.getProperty("name");
        String password = pros.getProperty("password");

        System.out.println(name);
        System.out.println(password);
        fis.close();
    }
}
```

#### 负载因子值的大小，对于HashMap有什么影响

- 负载因子的大小决定了HashMap的数据密度。
- 负载因子越大密度越大，发生碰撞的几率越高，数组中的链表越容易长，造成查询或插入时的比较次数增多，性能会下降。
- 负载因子越小，就越容易出发扩容，数据密度也越小，意味着发生碰撞的几率越小，数组中的链表也就越短，查询和插入时比较的次数也越小，性能会更高。但会浪费一定的内存空间。而且经常扩容也会影响性能。
- 按照其他语言的参考及研究经验，考虑将负载因子设置为0.7~0.75，此时平均检索长度接近于常数。

#### ConcurrentHashMap与HashTable异同

- 底层采用分段的数组+链表实现，线程安全
- 通过把整个Map分为N个Segment，可以提供相同的线程安全，但是效率提升N倍，默认提升16倍。(读操作不加锁，由于HashEntry的value变量是 volatile的，也能保证读取到最新的值。)
- Hashtable的synchronized是针对整张Hash表的，即每次锁住整张表让线程独占，ConcurrentHashMap允许多个修改操作并发进行，其关键在于使用了锁分离技术
- 有些方法需要跨段，比如size()和containsValue()，它们可能需要锁定整个表而而不仅仅是某个段，这需要按顺序锁定所有段，操作完毕后，又按顺序释放所有段的锁
- 扩容：段内扩容（段内元素超过该段对应Entry数组长度的75%触发扩容，不会对整个Map进行扩容），插入前检测需不需要扩容，有效避免无效扩容

从类图中可以看出来在存储结构中ConcurrentHashMap比HashMap多出了一个类Segment，而Segment是一个可重入锁。

ConcurrentHashMap是使用了锁分段技术来保证线程安全的。

锁分段技术：首先将数据分成一段一段的存储，然后给每一段数据配一把锁，当一个线程占用锁访问其中一个段数据的时候，其他段的数据也能被其他线程访问。 

ConcurrentHashMap提供了与Hashtable和SynchronizedMap不同的锁机制。Hashtable中采用的锁机制是一次锁住整个hash表，从而在同一时刻只能由一个线程对其进行操作；而ConcurrentHashMap中则是一次锁住一个桶。

ConcurrentHashMap默认将hash表分为16个桶，诸如get、put、remove等常用操作只锁住当前需要用到的桶。这样，原来只能一个线程进入，现在却能同时有16个写线程执行，并发性能的提升是显而易见的。

### Collections

操作Collection、Map的工具类

- reverse(List)：反转List中元素
- shuffle(List)：对List集合元素进行随机排序
- sort(List)：排序
- sort(List, Comparator)：使用比较器排序
- swap(List, int, int)：将两个位置元素交换
- max / min(Collection)：根据自然顺序返回集合中最大/小元素
- max / min(Collection, Comparator)：根据比较器顺序返回集合中最大/小元素
- frequency(Collection, Object)：返回指定集合中指定元素的出现次数
- copy(List dest, List src)：将src中的内容复制到dest中
- replaceAll(List list, Object oldVal, Object newVal)：使用新值替换List对象

Collections提供了多个synchronizedXxx()方法，该方法可将指定集合包装成线程同步的集合，从而解决多线程并发访问集合时的线程安全问题

![20210216223940](http://ruiimg.hifool.cn/img20210216223940.png)

- 相当于给新的Collection对象的所有方法全部嵌套上一个synchronized(mutex)

## 泛型

### 通配符

有限制条件的通配符的使用

- `? extends A`：
  - `G<? extends A>` 可以作为`G<A>`和`G<B>`的父类的，其中B是A的子类
- `? super Person`：可以作为`G<A>`和`G<B>`的父类的，其中B是A的父类

## IO流

异常处理：为了保证流资源一定可以执行关闭操作。需要使用try-catch-finally处理

### File类

- File类的一个对象，代表一个文件或一个文件目录
- File类声明在java.io包下
- 创建File对象
  - 相对路径：相对于当前module
  - 绝对路径

### 流的分类

- 按操作数据单位不同分为
  - 字节流（8 bit）byte
  - 字符流（16 bit）char
- 按数据流的流向不同分为
  - 输入流
  - 输出流
- 按流的角色的不同分为
  - 节点流：直接作用在文件上的流
  - 处理流：在已有流上包装一层

| （抽象基类） | 字节流 | 字符流 |
| ---- | ---- | ---- |
| 输入流 | InputStream | Reader |
| 输出流 | OutputStream | Writer |

![20210217184644](http://ruiimg.hifool.cn/img20210217184644.png)

### 缓冲流

- 属于处理流，就是套接在已有的流的基础上
- 提高流的读取、写入速度
- 内部提供了一个缓冲区`private static int DEFAULT_BUFFER_SIZE = 8192;`

```java
public static void fileCopy(String srcPath, String destPath){
    //1. 造文件
    FileInputStream fis = null;
    FileOutputStream fos = null;
    try {
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        //2. 造流
        //2.1 造节点流
        fis = new FileInputStream(srcFile);
        fos = new FileOutputStream(destFile);
        //2.2 造缓冲流
        BufferedInputStream bis = new BufferedInputStream(fis);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        //3. 复制的细节：读取，写入
        byte[] buffer = new byte[1024];
        int len;
        while((len = bis.read(buffer)) != -1)
            bos.write(buffer, 0, len);
    } catch (IOException e) {
        e.printStackTrace();
    } finally {

        //        bis.close();
//        bos.close();
        //说明：关闭外层流的同时，内层流也会自动的进行关闭。
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### 转换流

- 提供字节流与字符流之间的转换 XxxStreamReader / XxxStreamWriter

![20210218112426](http://ruiimg.hifool.cn/img20210218112426.png)

### 对象流

- ObjectInputStream和ObjectOutputStream
- 用于存储和读取**基本数据类型**数据或**对象**的处理流
- 对象的序列化机制：允许把内存中的Java对象转换成平台无关的二进制流，从而允许把这种二进制流持久地保存在磁盘上，或通过网络将这种二进制流传输到另一个网络节点。当其他程序获取了这种二进制流，就可以恢复成Java对象
  - 序列化：用ObjectOutputStream类保存基本数据或对象的机制
  - 反序列化：用ObjectInputStream类读取基本类型数据或对象的机制
  - 不能序列化static和transient修饰的成员变量
  - 可将任何实现了Serializable接口的对象转化为字节数据，使其在保存和传输时可被还原。
  - 序列化是RMI过程的参数和返回值都必须实现的机制，而RMI过程的参数和返回值都必须实现的机制，而RMI是JavaEE的基础。因此序列化机制是JavaEE平台的基础。
  - 如果需要让某个对象支持序列化机制，则
    - 必须让对象所属的类及其属性是可序列化的
    - 当前类提供一个全局常量serialVersionUID
      - 用来表明类的不同版本间的兼容性。进行版本控制。
      - 如果没有显示提供，则Java运行时环境根据类的内部细节自动生成的，如果类发生修改，该值会变化
      - Java的序列化机制是通过在运行时判断此值来验证版本是否一致的，否则抛出版本不一致的异常（InvalidCastException）
    - 该类必须实现如下两个接口之一：
      - Serializable
      - Externalizable
  - static和transiant修饰的成员变量是不可被序列化的 变为默认值（null, 0）

### 随机存储文件

RandomAccess

- 实现了DataInput和DataOutPut两个接口，既可以读也可以写
- 支持随机访问，可以直接跳到文件的任意位置读、写文件。
- 包含一个记录指针，用来标记当前读写处的位置。
- 写入已有文件时，从头开始写，从头开始覆盖。而前面的输出流都是直接覆盖整个文件。

### NIO

- 1.4引入NIO
- NIO支持面向缓冲区（IO是面向流的）的、基于通道的IO操作
- 更加高效
- JAVA API提供两套NIO，一套是针对**标准输入输出**的，另一套是**网络编程NIO**
- jdk 7引入NIO2

- java.nio.channels.Channel
  - FileChannel：处理本地文件
  - SocketChannel：TCP网络编程的客户端的Channel
  - ServerSocketChannel：TCP网络编程的服务器端的Channel
  - DatagramChannel：UDP网络编程中发送端和接收端的Channel

### 第三方包

org.apache.commons.io.FileUtils

## 网络编程

- 在Java中使用InetAddress类代表IP

### Socket

- 利用套接字（Socket）开发网络应用程序早已被广泛的采用，以至于成为事实上的标准。
- 网络上具有唯一标识的IP地址和端口号组合在一起才能构成唯一能识别的标识符套接字。
- 通信的两端都要有Socket，是两台机器间通信的端点。
- 网络通信其实就是Socket间的通信。
- Socket允许程序把网络连接当成一个流，数据在两个Socket间通过IO传输。
- 分类
  - 流套接字（stream socket）：使用TCP提供可依赖的字节流服务
  - 数据报套接字（datagram socket）：使用UDP提供“尽力而为”的数据报服务

```java
public class SocketTest {
    @Test
    public void client() {
        Socket socket = null;
        OutputStream outputStream = null;
        try {
            socket = new Socket("127.0.0.1", 8899);
            outputStream = socket.getOutputStream();
            outputStream.write("hello world!".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Test
    public void server() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        try {
            serverSocket = new ServerSocket(8899);
            socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            int len;
            char[] message = new char[1024];
            while ((len = inputStreamReader.read(message)) != -1) {
                System.out.println(new String(message, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

```

## 反射

- Reflection是被视为**动态语言**的关键，反射机制允许程序在执行期借助于Reflection API取得任何类的内部信息，并能直接操作任意对象的内部属性及方法。
- 加载完类之后，在堆内存的方法区中就产生了一个Class类型的对象，这个对象就包含了完整的类的结构信息。我们可以通过这个对象看到类的结构。

### Java反射机制提供的功能

- 在运行时判断任意一个对象所属的类
- 在运行时构造任意一个类的对象（可以使用私有构造器）
- 在运行时判断任意一个类所具有的成员变量和方法（包括私有）
- 在运行时获取泛型信息
- 在运行时调用任意一个对象的成员变量和方法
- 在运行时处理注解
- 生成动态代理

### java.lang.Class类

#### 类的加载过程：
1. 程序经过javac.exe命令以后，会生成一个或多个字节码文件(.class)
2. 使用java.exe对某个字节码文件进行解释运行。相当于将某个字节码文件加载到内存中。此过程称为类的加载。加载到内存中的类，我们就称为运行时类，此运行时类就作为Class的一个实例。

- Class的实例就对应着一个运行时类
- 加载到内存中的运行时类，会缓存一定的时间。在此时间之内，我们可以通过不同的方式来获取此运行时类

![20210219114238](http://ruiimg.hifool.cn/img20210219114238.png)

#### 获取实例的方法

1. 调用运行时类的属性： .class
2. 通过运行时类的对象调用getClass()方法
3. 调用Class的静态方法：`Class.forName("java.lang.String");`
4. 使用类的加载器：`XXX.class.getClassLoader().loadClass("xxx.xxx.xxx")`

#### Class对象类型

- class：
  - 外部类，成员（成员内部类，静态内部类），局部内部类，匿名内部类
- interface接口
- 数组
- enum枚举
- annotation注解@interface
- primitive type基本数据类型
- void
- Class本身

```java
public static void main(String[] args) {
    Class c1 = Object.class;
    Class c2 = Comparable.class;
    Class c3 = String[].class;
    Class c4 = int[][].class;
    Class c5 = ElementType.class;
    Class c6 = Override.class;
    Class c7 = int.class;
    Class c8 = void.class;
    Class c9 = Class.class;

    int[] a = new int[10];
    int[] b = new int[100];
    Class c10 = a.getClass();
    Class c11 = b.getClass();
    //只要数组的元素类型与唯独一样，就是同一个Class
    System.out.println(c10 == c11);
}
```

## 函数式

- jdk8之后
- java内置的4大核心函数式接口
  - 消费型接口 `Consumer<T>`
    - `void accept(T t)`
  - 供给型接口 `Supplier<T>`
    - `T get()`
  - 函数型接口 `Function<T, R>`
    - `R apply(T t)`
  - 断定型接口 `Predicate<T>`
    - `boolean test(T t)`

## Stream API

- 是数据渠道，用于操作数据源（集合、数组等）所生成的元素序列
  - Stream自己不会存储元素
  - Stream不会改变源对象。相反，他们会返回一个持有结果的新Stream。
  - Stream操作是延迟执行的。这意味着他们会等到需要结果的时候才执行
- Stream的操作三个步骤
  1. 创建Stream：一个数据源（如：集合、数组），获取一个流
  2. 中间操作：一个中间操作链，对数据源的数据进行处理
  3. 终止操作（终端操作）：一旦执行终止操作，就执行中间操作链，并产生结果。之后，不会再被使用
   ![20210219153304](http://ruiimg.hifool.cn/img20210219153304.png)

- 创建stream
  ```java
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
  ```

### Stream 和 Collection集合的区别

- Collection是一种静态的内存数据结构
  - 面向内存，存储在内存中
- Stream是有关计算的
  - 面向CPU，通过CPU实现计算

## JVM

### 堆

![20210213164102](http://ruiimg.hifool.cn/img20210213164102.png)

1. 新生区（Young Genration Space）
2. 养老区（Tenure Generation Space）
3. 永久存储区（Permanent Space）

虽然JVM规范将方法区描述为堆的一个逻辑部分，但它却还有一个别名叫Non-Heap（非堆），目的就是要和堆分开。

## idea使用

- `ctrl + p` 看方法参数信息或者重载方法
- `ctrl + alt + t` 环绕方法模板
   ![20210210175750](http://ruiimg.hifool.cn/img20210210175750.png)
- `alt + insert` 自动创建构造器、setter等
- `ctrl + alt + ←` 查看代码时回退 → 向前
- `ctrl + h` 查看继承树