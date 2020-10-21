package rpsgame.test;


import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import rpsgame.demo.Player;

public class PlayerTest {


  @Test
  public void getNameTest() {
    Player p = new Player("testName", true);
    Assert.assertEquals(p.getName(), "testName");
  }

  @Test
  public void getChooseListTest() {
    Player fairPlayer = new Player("fairPlayer", true);
    Player unfairPlayer = new Player("unfairPlayer", false);

    Assert.assertTrue(fairPlayer.getChoiceList().equals(Player.COMPLETE_CHOICE_LIST));
    Assert.assertTrue(unfairPlayer.getChoiceList().equals(Player.ROCK_CHOICE_LIST));
  }

  @Test
  public void winCounterTest() {
    Player p = new Player("testName", true);
    assert (p.getWinCounter() == 0);

    int number = 3;
    p.setWinCounter(number);
    Assert.assertEquals(p.getWinCounter(), number);
  }

  @Test
  public void drawCounterTest() {
    Player p = new Player("testName", true);
    assert (p.getDrawCounter() == 0);

    int number = 3;
    p.setDrawCounter(number);
    Assert.assertEquals(p.getDrawCounter(), number);
  }

  @Test
  public void getChoiceTest() {
    // fair case
    Player p1 = new Player("testName1", true);
    for (int x = 0; x < 10; x++) {
      Assert
          .assertTrue(Arrays.stream(Player.COMPLETE_CHOICE_LIST).anyMatch(p1.getChoice()::equals));
    }

    // unfair case
    Player p2 = new Player("testName2", false);
    for (int x = 0; x < 10; x++) {
      Assert
          .assertTrue(Arrays.stream(Player.ROCK_CHOICE_LIST).anyMatch(p2.getChoice()::equals));
    }
  }


}
