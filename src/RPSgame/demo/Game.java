package RPSgame.demo;

public class Game {

  private final Player p1;

  private final Player p2;

  private String resultInfo;

  public Game(Player p1, Player p2) {
    this.p1 = p1;
    this.p2 = p2;
    this.resultInfo = "";
  }

  /**
   * Play the game regard to the given number of iterations and update the result information.
   *
   * @param iteration {@code int}
   */
  public void play(int iteration) {
    for (int idx = 1; idx < iteration + 1; idx++) {
      // get choice of 2 players
      String p1Choice = p1.getChoice();
      String p2Choice = p2.getChoice();
      // compare the choices
      int result = compareChoices(p1Choice, p2Choice);
      // update the result
      updateResult(idx, result, p1Choice, p2Choice);
    }
    addFinalResultInfo();
  }

  /**
   * result information getter
   *
   * @return {@code String}
   */
  public String getResultInfo() {
    return this.resultInfo;
  }

  // [-1]p1 wins, [0]draw, [1]p2 wins
  private int compareChoices(String p1Choose, String p2Choose) {
    if (p1Choose.equals("Rock") && p2Choose.equals("Scissors")
        || p1Choose.equals("Scissors") && p2Choose.equals("Paper")
        || p1Choose.equals("Paper") && p2Choose.equals("Rock")) {
      return -1;
    } else if (p2Choose.equals("Rock") && p1Choose.equals("Scissors")
        || p2Choose.equals("Scissors") && p1Choose.equals("Paper")
        || p2Choose.equals("Paper") && p1Choose.equals("Rock")) {
      return 1;
    }
    return 0;
  }

  private void updateResult(int iteration, int result, String p1Choice, String p2Choice) {
    this.resultInfo = this.resultInfo
        .concat("\n===================\n Game" + iteration + "\n===================\n");
    updateResultCounter(result);
    updateResultMsg(result, p1Choice, p2Choice);
  }

  private void updateResultCounter(int result) {
    switch (result) {
      case -1:
        this.p1.setWinCounter(this.p1.getWinCounter() + 1);
        break;
      case 0:
        this.p1.setDrawCounter(this.p1.getDrawCounter() + 1);
        this.p2.setDrawCounter(this.p2.getDrawCounter() + 1);
        break;
      case 1:
        this.p2.setWinCounter(this.p2.getWinCounter() + 1);
        break;
    }
  }

  private void updateResultMsg(int result, String p1Choose, String p2Choose) {
    String msg = "[" + this.p1.getName() + "] has chosen: [" + p1Choose + "]\n" +
        "[" + this.p2.getName() + "] has chosen: [" + p2Choose + "]\n";
    switch (result) {
      case -1:
        msg = msg.concat("the winner is: [" + this.p1.getName() + "]");
        break;
      case 0:
        msg = msg.concat("the game was a draw");
        break;
      case 1:
        msg = msg.concat("the winner is: [" + this.p2.getName() + "]");
        break;
    }
    // TODO: if result != -1 ,0,1
    this.resultInfo = this.resultInfo.concat(msg);
  }

  private void addFinalResultInfo() {
    String msg ="\n\n===================\n FINAL RESULT \n===================\n[" + p1.getName() + "]"
        + " wins: " + p1.getWinCounter() + " draws: " + p1
        .getDrawCounter() + "\n[" + p2.getName() + "]" + " wins: " + p2.getWinCounter()
        + " draws: " + p2
        .getDrawCounter();
    this.resultInfo=  this.resultInfo.concat(msg);
  }
}


