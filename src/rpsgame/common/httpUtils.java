package rpsgame.common;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class httpUtils {

  public static final String HTTP_PATH_PLAYER = "/v1/playerConnect";
  public static final String HTTP_PATH_CHOICE = "/v1/choiceConnect";

  public static final String PROMPT_CHOICE = "choice";
  public static final String PROMPT_PLAYER = "player";

  public static boolean validateParamsMap(Map<String, String> paramsMap, String paramPrompt) {
    return paramsMap.containsKey(paramPrompt) && !paramsMap.get(paramPrompt).isEmpty()
        && paramsMap.size() == 1;
  }

  // reponse output
  public static void sendResponse(HttpExchange httpExchange, int httpCode, String response)
      throws IOException {
    httpExchange.sendResponseHeaders(httpCode, response.getBytes(
        StandardCharsets.UTF_8).length);
    OutputStream responseBody = httpExchange.getResponseBody();
    OutputStreamWriter writer = new OutputStreamWriter(responseBody, StandardCharsets.UTF_8);
    writer.write(response);
    writer.close();
    responseBody.close();
  }

  public static void sendResponse(HttpExchange httpExchange, int httpCode, ResponseStatus status)
      throws IOException {
    httpExchange.sendResponseHeaders(httpCode, status.toString().getBytes(
        StandardCharsets.UTF_8).length);
    OutputStream responseBody = httpExchange.getResponseBody();
    OutputStreamWriter writer = new OutputStreamWriter(responseBody, StandardCharsets.UTF_8);
    writer.write(status.toString());
    writer.close();
    responseBody.close();
  }


  /**
   * Send HTTP call.
   *
   * @param urlDir {@code String} url of the HTTP call.
   * @param method {@code String} REST method.
   */
  public static void sendHttpCall(String urlDir, String method) {
    try {
      URL url = new URL(urlDir);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod(method);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
