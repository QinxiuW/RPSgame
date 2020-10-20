package RPSgame.demo;

import RPSgame.server.MyHttpHandler;
import RPSgame.server.MyHttpServer;
import RPSgame.utils.CommonUtils;
import RPSgame.utils.TextUtils;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RPSconsole {


  final static String[] COMPLETE_CHOICE_LIST = {"Rock", "Paper", "Scissors"};
  final static String[] ROCK_CHOICE_LIST = {"Rock"};
  final static int GAME_NUMBER = 10;

  String resultMsg = "";

  public void start() {
    TextUtils.welcomeText();
    TextUtils.modeChoiceText();
    // the option range is 1-3: [1]fair mode,[2]unfair mode,[3]remote mode
    int input = CommonUtils.getNumberInputWithLimit(3);

    switch (input) {
      case 1:
        fairMode();
        break;
      case 2:
        unfairMode();
        break;
      case 3:
        try {
          remoteMode();
        } catch (IOException | InterruptedException e) {
          e.printStackTrace();
        }
        break;
    }
  }

  public void end() {
    TextUtils.outputChoiceText();
    // the option range is 1-2: [1]Console,[2]File
    int input = CommonUtils.getNumberInputWithLimit(2);

    switch (input) {
      case 1:
        System.out.println(this.resultMsg);
        System.exit(0);
        break;
      case 2:
        CommonUtils.outputFile("result.txt", this.resultMsg);
        System.exit(0);
        break;
    }
  }


  public void fairMode() {
    // players set up
    Player p1 = new Player("Elisa", COMPLETE_CHOICE_LIST);
    Player p2 = new Player("Juan", COMPLETE_CHOICE_LIST);
    gameProcess(p1, p2);
  }

  public void unfairMode() {
    // players set up
    Player p1 = new Player("Elisa", COMPLETE_CHOICE_LIST);
    Player p2 = new Player("Juan", ROCK_CHOICE_LIST);
    gameProcess(p1, p2);
  }

  public void remoteMode() throws IOException, InterruptedException {
    // Server set up
    BlockingQueue<String> queue = new LinkedBlockingQueue<>(1);
    MyHttpHandler httpHandler = new MyHttpHandler(queue);
    MyHttpServer httpServer = new MyHttpServer(httpHandler);

    // Active waiting
    System.out.println("waiting for remote player...");
    String response = "";
    while (response.isBlank()) {
      response = queue.take();
      //  Thread.sleep(500);
    }

    var map = CommonUtils.getQueryMap(response);

    Player p1 = new Player("Elisa", COMPLETE_CHOICE_LIST);
    Player p2 = new Player(map.get("player"), COMPLETE_CHOICE_LIST);
    gameProcess(p1, p2);

    // stop the server
    httpServer.close();
  }

  private void gameProcess(Player p1, Player p2) {
    // game set up
    Game game = new Game(p1, p2);
    // process the game and get result
    game.play(GAME_NUMBER);
    this.resultMsg = this.resultMsg.concat(game.getResultInfo());
//    System.out.println(resultMsg);
  }



}

