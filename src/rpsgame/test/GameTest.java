package rpsgame.test;


import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.Assert;
import org.junit.Test;
import rpsgame.common.CommonUtils;
import rpsgame.demo.Game;
import rpsgame.demo.Player;
import rpsgame.server.MyHttpHandler;
import rpsgame.server.MyHttpServer;

public class GameTest {


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
  public void remoteGameTest() throws IOException {

    // Server set up
    int iteration = 10;
    BlockingQueue<String> queue = new LinkedBlockingQueue<>(1);
    MyHttpHandler httpHandler = new MyHttpHandler(queue);
    MyHttpServer httpServer = new MyHttpServer(httpHandler);
    httpServer.start();

    // Act
    new Thread(() -> remoteGameProcess(iteration, queue)).start();
    new Thread(() -> httpCallProcess(iteration)).start();

    // stop the server
    httpServer.close();
    httpHandler.close();
  }


  private void remoteGameProcess(int iteration, BlockingQueue<String> queue) {
    // Arrange
    Player p1 = new Player("player1", false, true);
    Player p2 = new Player("player2", true, true);

    // Act
    Game game = new Game(p1, p2, iteration, queue);

    // Asserts
    // result is not empty
    Assert.assertFalse(game.toString().isEmpty());

    // the sum of p1 win's counter, p2 win's counter
    // and drawCounter of one of them must be the number of iterations.
    Assert.assertEquals(p1.getWinCounter() + p2.getWinCounter() + p1.getDrawCounter(), iteration);
    Assert.assertEquals(p1.getWinCounter() + p2.getWinCounter() + p2.getDrawCounter(), iteration);
  }

  private void httpCallProcess(int iteration) {
    String url = "http://localhost:8081/myserver?choice=Rock";
    for (int x = 0; x < iteration; x++) {
      CommonUtils.sendHttpCall(url, "POST");
    }
  }

}
