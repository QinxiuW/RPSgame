package rpsgame.server;

import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class MyHttpServer {

  private com.sun.net.httpserver.HttpServer httpServer;
  static int PORT = 8081;
  static String PATH = "/myserver";

  /**
   * Constructor.
   * @param myHttpHandler {@link HttpHandler}
   * @throws IOException Exception.
   */
  public MyHttpServer(HttpHandler myHttpHandler) throws IOException {
    //Create an HttpServer instance and bind it to the specified IP address and port number
    this.httpServer = com.sun.net.httpserver.HttpServer
        .create(new InetSocketAddress(PORT), 0);
    //Create an HttpContext, map the request with the path /myserver to the MyHttpHandler processor
    this.httpServer.createContext(PATH, myHttpHandler);
    //Set the server's thread pool object
    this.httpServer.setExecutor(Executors.newFixedThreadPool(1));

  }

  /**
   * start the server.
   */
  public void start() {
    //Start the server
    this.httpServer.start();
    System.out.println("server started...");
  }

  /**
   * close the server.
   */
  public void close() {
    this.httpServer.stop(0);
  }
}
