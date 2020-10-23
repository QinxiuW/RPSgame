package rpsgame.test;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.Assert;
import org.junit.Test;
import rpsgame.common.httpUtils;
import rpsgame.demo.Play;
import rpsgame.demo.Player;
import rpsgame.server.MyHttpHandler;
import rpsgame.server.MyHttpServer;

public class PlayTest {

  @Test
  public void singlePlayTest() {

    Player p1 = new Player("p1", false, true);
    Player p2 = new Player("p2", false, false);
    Play play = new Play(1, p1, p2, null);

    //Asserts
    assertPlay(play, p1, p2, 1);
    // System.out.println(play.toString());
  }

  @Test
  public void multiPlayTest() {

    Player p1 = new Player("p1", false, true);
    Player p2 = new Player("p2", false, true);
    int iteration = 10;

    Play[] playArray = new Play[iteration];
    for (int x = 0; x < iteration; x++) {
      playArray[x] = new Play(x + 1, p1, p2, null);
    }
    for (Play play : playArray) {
      assertPlay(play, p1, p2, iteration);
      //System.out.println(play.toString());
    }
  }

  @Test
  public void remotePlayTest() throws IOException {
    String url = "http://localhost:8081/myserver?choice=Rock";
    // Server set up
    BlockingQueue<String> choiceQueue = new LinkedBlockingQueue<>(1);
    MyHttpHandler choiceHandler = new MyHttpHandler(choiceQueue, httpUtils.PROMPT_CHOICE);
    MyHttpServer httpServer = new MyHttpServer(null, choiceHandler);
    httpServer.start();

    new Thread(() -> remoteProcess(httpServer, choiceHandler, choiceQueue)).start();
    new Thread(() -> httpUtils.sendHttpCall(url, "POST")).start();
  }

  private void remoteProcess(MyHttpServer server, MyHttpHandler handler,
      BlockingQueue<String> queue) {
    Player p1 = new Player("p1", false, true);
    Player p2 = new Player("p2", true, true);
    Play play = new Play(1, p1, p2, queue);
    assertPlay(play, p1, p2, 1);

    server.close();
    handler.close();
  }

  private void assertPlay(Play play, Player p1, Player p2, int iteration) {
    // result not empty
    Assert.assertFalse(play.toString().isEmpty());

    // one of them has to win or is a draw game.
    Assert.assertTrue(
        (p1.getWinCounter() > 0) || (p2.getWinCounter() > 0) || (p1.getDrawCounter() > 0
            && p2.getDrawCounter() > 0));

    // the sum of p1 win's counter, p2 win's counter and drawCounter of one of them must be 1.
    Assert.assertEquals(p1.getWinCounter() + p2.getWinCounter() + p1.getDrawCounter(), iteration);
    Assert.assertEquals(p1.getWinCounter() + p2.getWinCounter() + p2.getDrawCounter(), iteration);
  }
}
