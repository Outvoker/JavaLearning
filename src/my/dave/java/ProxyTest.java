package my.dave.java;

/**
 * @author Xu Rui
 * @date 2021/2/9 12:25
 */
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