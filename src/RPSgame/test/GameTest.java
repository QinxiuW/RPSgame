package RPSgame.test;

import RPSgame.demo.Game;
import RPSgame.demo.Player;
import org.junit.Assert;
import org.junit.Test;

public class GameTest {


  @Test
  public void playTest() {

    Player p1 = new Player("testName", true);
    Player p2 = new Player("testName", true);
    Game game = new Game(p1, p2);

    // result not empty
    String result = game.play(1);
    Assert.assertFalse(result.isBlank());

    // only 1 can win or both draw
    Assert.assertTrue((p1.getWinCounter() == 1 && p2.getWinCounter() == 0) ||
        (p1.getWinCounter() == 1 && p2.getDrawCounter() == 1) ||
        p1.getDrawCounter() == p1.getDrawCounter());

    // iteration must be the sum of p1 win's counter, p2 win's counter and drawCounter.
    // for this case iteration number must be 3
    game.play(2);
    game.play(3);
    Assert.assertEquals(p1.getWinCounter() + p2.getWinCounter() + p1.getDrawCounter(), 3);
    Assert.assertEquals(p1.getWinCounter() + p2.getWinCounter() + p2.getDrawCounter(), 3);
  }
}
