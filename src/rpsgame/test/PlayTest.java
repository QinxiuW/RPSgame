package rpsgame.test;

import org.junit.Assert;
import org.junit.Test;
import rpsgame.demo.Play;
import rpsgame.demo.Player;

public class PlayTest {

  @Test
  public void singlePlayTest() {

    Player p1 = new Player("p1", true);
    Player p2 = new Player("p2", true);
    Play play = new Play(1, p1, p2);

    //Asserts
    assertPlay(play, p1, p2);
  }

  private void assertPlay(Play play, Player p1, Player p2) {
    // result not empty
    Assert.assertTrue(!play.toString().isEmpty());

    // one of them has to win or is a draw game.
    Assert.assertTrue(
        (p1.getWinCounter() > 0) || (p2.getWinCounter() > 0) || (p1.getDrawCounter() > 0
            && p2.getDrawCounter() > 0));

    // the sum of p1 win's counter, p2 win's counter and drawCounter of one of them must be 1.
    Assert.assertEquals(p1.getWinCounter() + p2.getWinCounter() + p1.getDrawCounter(), 1);
    Assert.assertEquals(p1.getWinCounter() + p2.getWinCounter() + p2.getDrawCounter(), 1);
  }
}
