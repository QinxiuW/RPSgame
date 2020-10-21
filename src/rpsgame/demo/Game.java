package rpsgame.demo;

public class Game {

  private final Player p1;

  private final Player p2;

  public Game(Player p1, Player p2) {
    this.p1 = p1;
    this.p2 = p2;
  }

  /**
   * process the game and return the result.
   *
   * @param gameId {@code int}
   * @return {@code String}
   */
  public String play(int gameId) {

    // init Players
    String p1Choice = p1.getChoice();
    String p2Choice = p2.getChoice();

    // compare the choices
    int result = compareChoices(p1Choice, p2Choice);

    // update the result
    updateResultCounter(result);

    return getResultInfo(gameId, result, p1Choice, p2Choice);
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

  private String getResultInfo(int iteration, int result, String p1Choice, String p2Choice) {

    String msg = "\n\n===================\n Game" + iteration + "\n===================\n";
    msg = msg.concat("[" + this.p1.getName() + "] has chosen: [" + p1Choice + "]\n"
        + "[" + this.p2.getName() + "] has chosen: [" + p2Choice + "]\n");
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
      default:
        break;
    }
    return msg;
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
      default:
        break;
    }
  }
}


