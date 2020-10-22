//package rpsgame.demo;
//
//import java.io.IOException;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.atomic.AtomicReference;
//import rpsgame.server.MyHttpHandler;
//import rpsgame.server.MyHttpServer;
//import rpsgame.common.CommonUtils;
//
//public class Game {
//
//  private final Player p1;
//
//  private final Player p2;
//
//  private final Play[] plays;
//
//  /**
//   * Constructor.
//   *
//   * @param p1        {@link Player} first player.
//   * @param p2        {@link Player} second player.
//   * @param iteration {@code int} number of iterations.
//   */
//  public Game(Player p1, Player p2, int iteration) {
//    this.p1 = p1;
//    this.p2 = p2;
//    this.plays = new Play[iteration];
//    startPlays(iteration);
//  }
//
//
//  private void startPlays(int iteration) {
//
//    for (int x = 0; x < iteration; x++) {
//      Play play = new Play(x + 1, this.p1, this.p2, this.p1.getRdmChoice(), this.p2.getRdmChoice());
//      this.plays[x] = play;
//    }
//  }
//
//  private void standalonePlay(int iteration) {
//    for (int x = 0; x < iteration; x++) {
//      Play play = new Play(x + 1, this.p1, this.p2, this.p1.getRdmChoice(), this.p2.getRdmChoice());
//      this.plays[x] = play;
//    }
//  }
//
//  private void remotePlay(int iteration) throws IOException, InterruptedException {
//    boolean isFinished = false;
//    int idx = 0;
//    // Server set up
//    BlockingQueue<String> queue = new LinkedBlockingQueue<>(1);
//    MyHttpHandler httpHandler = new MyHttpHandler(queue);
//    MyHttpServer httpServer = new MyHttpServer(httpHandler);
//    httpServer.start();
//
//    // Active waiting
//    for (int x = 0; x < iteration; x++) {
//      System.out.println("Game ["+x+1+"] waiting for remote player's choice");
//      var response = new AtomicReference<>("");
//      while (response.get().isBlank()) {
//        response.set(queue.take());
//        //  Thread.sleep(500);
//      }
//
//      var responseMap = CommonUtils.getQueryMap(response.get());
//      Play play = new Play(x + 1, this.p1, this.p2, this.p1.getRdmChoice(), responseMap.get("player"));
//      this.plays[x] = play;
//    }
//
//  }
//
////  private boolean anyRemotePlayer(){
////    return this.p1.getIsRemote() || this.p2.getIsRemote();
////  }
//
//  private Player getRemotePlayer() {
//    if (this.p1.getIsRemote()) {
//      return this.p1;
//    } else if (this.p2.getIsRemote()) {
//      return this.p2;
//    }
//    return null;
//  }
//
//
//  @Override
//  public String toString() {
//    String msg = "";
//    for (Play play : this.plays) {
//      msg = msg.concat(play.toString());
//    }
//    msg = msg.concat(finalResultInfo(this.p1, this.p2));
//    return msg;
//  }
//
//  private String finalResultInfo(Player p1, Player p2) {
//    return "\n\n===================\n FINAL RESULT \n===================\n"
//        + p1.toString() + "\n" + p2.toString();
//  }
//
//}
//
//
