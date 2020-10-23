package rpsgame.test;


import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.Assert;
import org.junit.Test;
import rpsgame.common.HttpUtils;
import rpsgame.demo.Game;
import rpsgame.demo.Player;
import rpsgame.server.MyHttpHandler;
import rpsgame.server.MyHttpServer;

public class GameTest {

  static final int PORT = 8082;

  @Test
  public void gameTest() {
    // Arrange
    int iteration = 10;
    Player p1 = new Player("player1", false, true);
    Player p2 = new Player("player2", false, true);

    // Act
    Game game = new Game(p1, p2, iteration, null);

    // Asserts
    // result is not empty
    Assert.assertFalse(game.toString().isEmpty());

    // the sum of p1 win's counter, p2 win's counter
    // and drawCounter of one of them must be the number of iterations.
    Assert.assertEquals(p1.getWinCounter() + p2.getWinCounter() + p1.getDrawCounter(), iteration);
    Assert.assertEquals(p1.getWinCounter() + p2.getWinCounter() + p2.getDrawCounter(), iteration);
  }


  @Test
  public void remoteGameTest() {
    // Arrange
    int iteration = 10;
    // Act
    new Thread(() -> {
      try {
        remoteGameProcess(iteration);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }).start();
    new Thread(() -> httpCallProcess(iteration)).start();
  }


  private void remoteGameProcess(int iteration) throws IOException {
    // Arrange
    Player p1 = new Player("player1", false, true);
    Player p2 = new Player("player2", true, true);
    // Server set up
    BlockingQueue<String> choiceQueue = new LinkedBlockingQueue<>(1);
    MyHttpHandler choiceHandler = new MyHttpHandler(choiceQueue, HttpUtils.PROMPT_CHOICE);
    MyHttpServer httpServer = new MyHttpServer(PORT, null, choiceHandler);
    httpServer.start();

    // Act
    Game game = new Game(p1, p2, iteration, choiceQueue);
    // Asserts
    // result is not empty
    Assert.assertFalse(game.toString().isEmpty());

    // the sum of p1 win's counter, p2 win's counter
    // and drawCounter of one of them must be the number of iterations.
    Assert.assertEquals(p1.getWinCounter() + p2.getWinCounter() + p1.getDrawCounter(), iteration);
    Assert.assertEquals(p1.getWinCounter() + p2.getWinCounter() + p2.getDrawCounter(), iteration);

    // stop the server
    httpServer.close();
    choiceHandler.close();
  }

  private void httpCallProcess(int iteration) {
    String url = "http://localhost:8081/myserver?choice=Rock";
    for (int x = 0; x < iteration; x++) {
      HttpUtils.sendHttpCall(url, "POST");
    }
  }

}
