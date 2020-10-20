package RPSgame.server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

public class MyHttpHandler implements HttpHandler {

  private BlockingQueue<String> queue;
  public MyHttpHandler(BlockingQueue<String> queue){
    this.queue = queue;
  }

  @Override
  public void handle(HttpExchange httpExchange) throws IOException {
    String requestMethod = httpExchange.getRequestMethod();
    if(requestMethod.equalsIgnoreCase("POST")){//客户端的请求是get方法
      //设置服务端响应的编码格式，否则在客户端收到的可能是乱码
      Headers responseHeaders = httpExchange.getResponseHeaders();
      responseHeaders.set("Content-Type", "text/html;charset=utf-8");

      //在这里通过httpExchange获取客户端发送过来的消息
      URI url = httpExchange.getRequestURI();
//        System.out.println(url.getQuery());
      try {
        this.queue.put(url.getQuery());
//        this.q.offer(url.getQuery());
        System.out.println("put: "+url.getQuery());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      String response = "this is server";
      httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.getBytes("UTF-8").length);

      OutputStream responseBody = httpExchange.getResponseBody();
      OutputStreamWriter writer = new OutputStreamWriter(responseBody, "UTF-8");
      writer.write(response);
      writer.close();
      responseBody.close();
    }
  }

}
