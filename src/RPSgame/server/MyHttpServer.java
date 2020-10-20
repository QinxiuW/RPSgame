package RPSgame.server;

import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class MyHttpServer {

  private com.sun.net.httpserver.HttpServer httpServer;


  public MyHttpServer(HttpHandler myHttpHandler) throws IOException {
    //创建一个HttpServer实例，并绑定到指定的IP地址和端口号
    this.httpServer = com.sun.net.httpserver.HttpServer
        .create(new InetSocketAddress(8081), 0);
    //创建一个HttpContext，将路径为/myserver请求映射到MyHttpHandler处理器
    this.httpServer.createContext("/myserver", myHttpHandler);
    //设置服务器的线程池对象
    this.httpServer.setExecutor(Executors.newFixedThreadPool(1));
    //启动服务器
    this.httpServer.start();
    System.out.println("server started...");
  }

  public void close(){
    this.httpServer.stop(0);
  }
}
