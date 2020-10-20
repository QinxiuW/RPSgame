package RPSgame.test;


import RPSgame.demo.Player;
import java.util.Arrays;
import org.junit.Test;

public class PlayerTest {


  @Test
  public void getNameTest() {
    Player p = new Player("testName", true);
    assert (p.getName().equals("testName"));
  }

  @Test
  public void getChooseListTest() {
    Player fairPlayer = new Player("fairPlayer", true);
    Player unfairPlayer = new Player("unfairPlayer", false);

    assert (fairPlayer.getChooseList().equals(Player.COMPLETE_CHOICE_LIST));
    assert (unfairPlayer.getChooseList().equals(Player.ROCK_CHOICE_LIST));
  }

  @Test
  public void winCounterTest() {
    Player p = new Player("testName", true);
    assert (p.getWinCounter() == 0);

    int number = 3;
    p.setWinCounter(number);
    assert (p.getWinCounter() == number);
  }

  @Test
  public void drawCounterTest() {
    Player p = new Player("testName", true);
    assert (p.getDrawCounter() == 0);

    int number = 3;
    p.setDrawCounter(number);
    assert (p.getDrawCounter() == number);
  }

  @Test
  public void getChoiceTest() {
    // fair case
    Player p1 = new Player("testName1", true);
    for (int x = 0; x < 10; x++) {
      assert (Arrays.stream(Player.COMPLETE_CHOICE_LIST).anyMatch(p1.getChoice()::equals));
    }

    // unfair case
    Player p2 = new Player("testName2", false);
    for (int x = 0; x < 10; x++) {
      assert (Arrays.stream(Player.ROCK_CHOICE_LIST).anyMatch(p2.getChoice()::equals));
    }
  }



}
