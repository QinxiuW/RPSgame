package rpsgame.server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.BlockingQueue;

public class MyHttpHandler implements HttpHandler {

  private BlockingQueue<String> queue;

  private HttpExchange handler;

  public MyHttpHandler(BlockingQueue<String> queue) {
    this.queue = queue;
  }

  @Override
  public void handle(HttpExchange httpExchange) throws IOException {
    this.handler = httpExchange;
    String requestMethod = httpExchange.getRequestMethod();
    if (requestMethod.equalsIgnoreCase("POST")) {
      //Set the encoding format of the server response,
      // otherwise the received on the client may be garbled
      Headers responseHeaders = httpExchange.getResponseHeaders();
      responseHeaders.set("Content-Type", "text/html;charset=utf-8");

      //Get the message sent by the client through httpExchange here
      URI url = httpExchange.getRequestURI();
      try {
        this.queue.put(url.getQuery());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      String response = "this is server";
      httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.getBytes(
          StandardCharsets.UTF_8).length);
      OutputStream responseBody = httpExchange.getResponseBody();
      OutputStreamWriter writer = new OutputStreamWriter(responseBody, StandardCharsets.UTF_8);
      writer.write(response);
      writer.close();
      responseBody.close();
    }
  }

  public void close() {
    this.handler.close();
  }

  // params validation

  // reponse output


}
