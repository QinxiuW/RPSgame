//package rpsgame.test;
//
//
//import org.junit.Assert;
//import org.junit.Test;
//import rpsgame.demo.Game;
//import rpsgame.demo.Player;
//
//public class GameTest {
//
//  @Test
//  public void gameTest() {
//    // Arrange
//    int iteration = 10;
//    Player p1 = new Player("player1", true);
//    Player p2 = new Player("player2", true);
//
//    // Act
//    Game game = new Game(p1, p2, iteration);
//
//    // Asserts
//
//    // result is not empty
//    Assert.assertFalse(game.toString().isEmpty());
//
//    // the sum of p1 win's counter, p2 win's counter
//    // and drawCounter of one of them must be the number of iterations.
//    Assert.assertEquals(p1.getWinCounter() + p2.getWinCounter() + p1.getDrawCounter(), iteration);
//    Assert.assertEquals(p1.getWinCounter() + p2.getWinCounter() + p2.getDrawCounter(), iteration);
//  }
//}
