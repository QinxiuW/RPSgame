package RPSgame.demo;



import RPSgame.server.MyHttpHandler;
import RPSgame.server.MyHttpServer;
import RPSgame.utils.Utils;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Main {

  final static int GAME_NUMBER = 10;

  public static void main(String[] args) throws IOException, InterruptedException {
    BlockingQueue<String> queue = new LinkedBlockingQueue<>(1);
//    LinkedList<String> q  =  new LinkedList<String>();
    MyHttpHandler httpHander = new MyHttpHandler(queue);
    MyHttpServer httpServer = new MyHttpServer(httpHander);

    start(queue);
//    remoteMode(queue );
    httpServer.close();
    System.exit(0);
  }


  public static void welcomeText(){
    System.out.println("Welcome to RPS game");
    System.out.println("Choice one mode please:");
    System.out.println(" *[1]fair mode");
    System.out.println(" *[2]unfair mode");
    System.out.println(" *[3]remote mode");
  }


  public static void start(BlockingQueue<String> queue) throws InterruptedException {
// 1.Rock 2.Paper 3.Scissors
    welcomeText();
    int input = Utils.getNumberInputWithLimit(3);
    switch (input) {
      case 1:
        fairMode();
        break;
      case 2:
        unfairMode();
        break;
      case 3:
        remoteMode(queue);
        break;
    }
  }


  public static void fairMode() {
    String[] chooseList = {"Rock", "Paper", "Scissors"};
    Player p1 = new Player("Elisa", chooseList);
    Player p2 = new Player("Juan", chooseList);

    Game game = new Game(p1, p2);
    for (int x = 1; x < GAME_NUMBER + 1; x++) {
      System.out.println("\n===================");
      System.out.println("GAME " + x);
      System.out.println("===================\n");
      game.play();
    }
    printFinalResult(p1, p2);
  }

  public static void unfairMode() {
    String[] chooseList = {"Rock", "Paper", "Scissors"};
    String[] onlyRockList = {"Rock"};
    Player p1 = new Player("Elisa", chooseList);
    Player p2 = new Player("Juan", onlyRockList);
    Game game = new Game(p1, p2);
    for (int x = 1; x < GAME_NUMBER + 1; x++) {
      System.out.println("\n===================");
      System.out.println("GAME " + x);
      System.out.println("===================\n");
      game.play();
    }
    printFinalResult(p1, p2);
  }

  public static void remoteMode(BlockingQueue<String> queue) throws InterruptedException {

    Boolean received = false;
    String msg = null;
    System.out.println("waiting for remote player...");
    while (true) {
      msg = queue.take();
      if (msg != null) {
        received = true;
        System.out.println("remote player is connecting");
        break;
      }
    //  Thread.sleep(500);
    }
    String[] chooseList = {"Rock", "Paper", "Scissors"};
    Player p1 = new Player("Elisa", chooseList);
    Player p2 = new Player(msg, chooseList);
    Game game = new Game(p1, p2);
    for (int x = 1; x < GAME_NUMBER + 1; x++) {
      System.out.println("\n===================");
      System.out.println("GAME " + x);
      System.out.println("===================\n");
      game.play();
    }
    printFinalResult(p1, p2);
  }

  public static void remoteMode(LinkedList<String> queue) throws InterruptedException {

    Boolean received = false;
    String msg = null;
    System.out.println("waiting for remote player...");
    while (true) {
      msg = queue.poll();
      if (msg != null) {
        received = true;
        System.out.println("remote player is connecting");
        break;
      }
      //  Thread.sleep(500);
    }

    String[] chooseList = {"Rock", "Paper", "Scissors"};
    Player p1 = new Player("Elisa", chooseList);
    Player p2 = new Player(msg, chooseList);
    Game game = new Game(p1, p2);
    for (int x = 1; x < GAME_NUMBER + 1; x++) {
      System.out.println("\n===================");
      System.out.println("GAME " + x);
      System.out.println("===================\n");
      game.play();
    }
    printFinalResult(p1, p2);
  }



  public static void printFinalResult(Player p1, Player p2) {
    System.out.println("\n===================");
    System.out.println("FINAL RESULT");
    System.out.println("===================\n");
    System.out.println("[" + p1.getName() + "]" + " wins: " + p1.getWinCounter() + " draws: " + p1
        .getDrawCounter());
    System.out.println("[" + p2.getName() + "]" + " wins: " + p2.getWinCounter() + " draws: " + p2
        .getDrawCounter());
  }


}
