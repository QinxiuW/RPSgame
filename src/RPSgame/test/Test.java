//import com.sun.net.httpserver.Headers;
//import com.sun.net.httpserver.HttpExchange;
//import com.sun.net.httpserver.HttpHandler;
//import com.sun.net.httpserver.HttpServer;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.InetSocketAddress;
//import java.net.URI;
//
//public class Test {
//  public static void main(String[] args) throws Exception {
//    HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
//    server.createContext("/test", new MyHandler());
//    server.setExecutor(null); // creates a default executor
//    server.start();
//    System.out.println("server started");
//  }
//
//  static class MyHandler implements HttpHandler {
//    @Override
//    public void handle(HttpExchange httpExchange) throws IOException {
//      System.out.println("receive");
//      String requestMethod = httpExchange.getRequestMethod();
//      if(requestMethod.equalsIgnoreCase("POST")){//客户端的请求是get方法
//        //设置服务端响应的编码格式，否则在客户端收到的可能是乱码
//        Headers responseHeaders = httpExchange.getResponseHeaders();
//        responseHeaders.set("Content-Type", "text/html;charset=utf-8");
//
//        //在这里通过httpExchange获取客户端发送过来的消息
//        URI url = httpExchange.getRequestURI();
//        InputStream requestBody = httpExchange.getRequestBody();
//
//        String response = "this is server";
//
//        httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.getBytes("UTF-8").length);
//
//        OutputStream responseBody = httpExchange.getResponseBody();
//        OutputStreamWriter writer = new OutputStreamWriter(responseBody, "UTF-8");
//        writer.write(response);
//        writer.close();
//        responseBody.close();
//      }
//
//    }
//  }
//}
