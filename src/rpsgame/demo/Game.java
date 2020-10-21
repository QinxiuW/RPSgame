package rpsgame.demo;

public class Game {

  private final Player p1;

  private final Player p2;

  private final Play[] plays;

  public Game(Player p1, Player p2, int iteration) {
    this.p1 = p1;
    this.p2 = p2;
    this.plays = new Play[iteration];
    startPlays(iteration);
  }

  private void startPlays(int iteration) {

    for (int x = 0; x < iteration; x++) {
      Play play = new Play(x + 1, this.p1, this.p2, this.p1.getRdmChoice(), this.p2.getRdmChoice());
      this.plays[x] = play;
    }
  }

  @Override
  public String toString() {
    String msg = "";
    for (Play play : this.plays) {
      msg = msg.concat(play.toString());
    }
    msg = msg.concat(finalResultInfo(this.p1, this.p2));
    return msg;
  }

  private String finalResultInfo(Player p1, Player p2) {
    return "\n\n===================\n FINAL RESULT \n===================\n"
        + p1.toString() + "\n" + p2.toString();
  }

}


