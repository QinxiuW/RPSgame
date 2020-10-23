package rpsgame.server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import rpsgame.common.Choices;
import rpsgame.common.CommonUtils;
import rpsgame.common.ResponseStatus;
import rpsgame.common.httpUtils;
import rpsgame.demo.Player;

public class MyHttpHandler implements HttpHandler {

  private BlockingQueue<String> queue;

  private HttpExchange httpExchange;

  private String paramPrompt;

  public MyHttpHandler(BlockingQueue<String> queue, String paramPrompt) {
    this.queue = queue;
    this.paramPrompt = paramPrompt;
  }

  @Override
  public void handle(HttpExchange httpExchange) throws IOException {
    this.httpExchange = httpExchange;
    String requestMethod = httpExchange.getRequestMethod();
    if (requestMethod.equalsIgnoreCase("POST")) {
      //Set the encoding format of the server response,
      // otherwise the received on the client may be garbled
      Headers responseHeaders = httpExchange.getResponseHeaders();
      responseHeaders.set("Content-Type", "text/html;charset=utf-8");

      String query = httpExchange.getRequestURI().getQuery();
      // check if query is empty
      if (!query.isEmpty() && !query.isBlank()) {
        var paramsMap = CommonUtils.getQueryMap(query);
        // check if map's keys match with the given paramPrompt
        if (httpUtils.validateParamsMap(paramsMap, this.paramPrompt)) {
          httpUtils.sendResponse(httpExchange, HttpURLConnection.HTTP_OK, ResponseStatus.OK);
          // check the value of paramsMap
          validateParamsValue(paramsMap.get(this.paramPrompt));
        }
      }
      httpUtils.sendResponse(httpExchange, HttpURLConnection.HTTP_BAD_REQUEST, ResponseStatus.INVALID_PARAMS);
    }
  }


  private void validateParamsValue(String value) throws IOException {
    if(this.paramPrompt.equals(httpUtils.PROMPT_CHOICE)){
      validateChoice(value);
    }else if (this.paramPrompt.equals(httpUtils.PROMPT_PLAYER)){
      validatePlayer(value);
    }
  }

  private void validateChoice(String choice) throws IOException {
    if(Arrays.asList(Choices.COMPLETE_CHOICE_LIST).contains(choice)){
      updateQueue(choice);
    }else{
      httpUtils.sendResponse(httpExchange, HttpURLConnection.HTTP_OK, ResponseStatus.INVALID_CHOICE);
    }
  }

  private void validatePlayer(String player) throws IOException {
    if(player.length()<= Player.NAME_MAX_LENGTH){
      updateQueue(player);
    }else{
      httpUtils.sendResponse(httpExchange, HttpURLConnection.HTTP_OK, ResponseStatus.INVALID_PLAYER);
    }

  }


  public void close(){
    this.httpExchange.close();
  }

  private void updateQueue(String content) {
    try {
      this.queue.put(content);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
