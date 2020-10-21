package rpsgame.demo;


import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;
import rpsgame.server.MyHttpHandler;
import rpsgame.server.MyHttpServer;
import rpsgame.utils.CommonMessage;
import rpsgame.utils.CommonUtils;

public class GameConsole {


  public static String OUTPUT_FILE_PATH = "result.txt";
  static int GAME_NUMBER = 10;

  String resultMsg = "";

  /**
   * Initialize the game according to the mode selected by the user. There're 3 modes: fair mode,
   * unfair mode, remote mode.
   */
  public void start() {
    CommonMessage.welcomeText();
    CommonMessage.modeChoiceText();
    // the option range is 1-3: [1]fair mode,[2]unfair mode,[3]remote mode
    int input = CommonUtils.getNumberInputWithLimit(3);

    switch (input) {
      case 1:
        System.out.println("you have chosen fair mode");
        fairMode();
        break;
      case 2:
        System.out.println("you have chosen unfair mode");
        unfairMode();
        break;
      case 3:
        System.out.println("you have chosen remote mode");
        try {
          remoteMode();
        } catch (IOException | InterruptedException e) {
          e.printStackTrace();
        }
        break;
      default:
        break;
    }
  }

  /**
   * End the game and select the option to get the result. There're 2 options: console, file.
   */
  public void end() {
    CommonMessage.outputChoiceText();
    // the option range is 1-2: [1]Console,[2]File
    int input = CommonUtils.getNumberInputWithLimit(2);

    switch (input) {
      case 1:
        System.out.println("you have chosen [1]Console");
        System.out.println(this.resultMsg);
        break;
      case 2:
        System.out.println("you have chosen [2]File");
        CommonUtils.outputFile(OUTPUT_FILE_PATH, this.resultMsg);
        break;
      default:
        break;
    }
    CommonMessage.goodByeText();

  }

  private void fairMode() {
    // players set up
    Player p1 = new Player("Elisa", true);
    Player p2 = new Player("Juan", true);
    gameProcess(p1, p2);
  }

  private void unfairMode() {
    // players set up
    Player p1 = new Player("Elisa", true);
    Player p2 = new Player("Juan", false);
    gameProcess(p1, p2);
  }

  private void remoteMode() throws IOException, InterruptedException {
    // Server set up
    BlockingQueue<String> queue = new LinkedBlockingQueue<>(1);
    MyHttpHandler httpHandler = new MyHttpHandler(queue);
    MyHttpServer httpServer = new MyHttpServer(httpHandler);
    httpServer.start();

    // Active waiting
    System.out.println("waiting for remote player...");
    var response = new AtomicReference<>("");
    while (response.get().isBlank()) {
      response.set(queue.take());
      //  Thread.sleep(500);
    }

    var responseMap = CommonUtils.getQueryMap(response.get());

    Player p1 = new Player("Elisa", true);
    Player p2 = new Player(responseMap.get("player"), true);
    gameProcess(p1, p2);

    // stop the server
    httpServer.close();
  }

  private void gameProcess(Player p1, Player p2) {

    Game game = new Game(p1, p2);

    for (int x = 1; x < GAME_NUMBER + 1; x++) {
      String result = game.play(x);
      this.resultMsg = this.resultMsg.concat(result);
    }
    this.resultMsg = this.resultMsg.concat(finalResultInfo(p1, p2));
  }

  private String finalResultInfo(Player p1, Player p2) {
    return "\n\n===================\n FINAL RESULT \n===================\n[" + p1.getName() + "]"
        + " wins: " + p1.getWinCounter() + " draws: " + p1
        .getDrawCounter() + "\n[" + p2.getName() + "]" + " wins: " + p2.getWinCounter()
        + " draws: " + p2
        .getDrawCounter();
  }
}

