package RPSgame.demo;

import RPSgame.server.MyHttpHandler;
import RPSgame.server.MyHttpServer;
import RPSgame.utils.CommonUtils;
import RPSgame.utils.TextUtils;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

public class RPSconsole {


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
        break;
      case 2:
        CommonUtils.outputFile("result.txt", this.resultMsg);
        break;
    }
    TextUtils.goodByeText();
    System.exit(0);
  }


  public void fairMode() {
    // players set up
    Player p1 = new Player("Elisa", true);
    Player p2 = new Player("Juan", true);
    gameProcess(p1, p2);
  }

  public void unfairMode() {
    // players set up
    Player p1 = new Player("Elisa", true);
    Player p2 = new Player("Juan", false);
    gameProcess(p1, p2);
  }

  public void remoteMode() throws IOException, InterruptedException {
    // Server set up
    BlockingQueue<String> queue = new LinkedBlockingQueue<>(1);
    MyHttpHandler httpHandler = new MyHttpHandler(queue);
    MyHttpServer httpServer = new MyHttpServer(httpHandler);

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


  private void gameProcess(Player p1,Player p2){

    Game game = new Game(p1, p2);

    for(int x=1 ; x<GAME_NUMBER+1; x++){
      String result = game.play(x);
      this.resultMsg = this.resultMsg.concat(result);
    }
    this.resultMsg = this.resultMsg.concat(finalResultInfo(p1,p2));
  }

  private String finalResultInfo(Player p1,Player p2) {
    String msg ="\n\n===================\n FINAL RESULT \n===================\n[" + p1.getName() + "]"
        + " wins: " + p1.getWinCounter() + " draws: " + p1
        .getDrawCounter() + "\n[" + p2.getName() + "]" + " wins: " + p2.getWinCounter()
        + " draws: " + p2
        .getDrawCounter();
    return msg;
  }
}

