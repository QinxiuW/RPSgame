package rpsgame.demo;

public class Play {

  private int id;
  private Player p1;
  private Player p2;
  private String p1Choice;
  private String p2Choice;
  private int result;

  /**
   * Constructor.
   *
   * @param id       {@code int} id of each play.
   * @param p1       {@link Player} first player.
   * @param p2       {@link Player} second player.
   * @param p1Choice {@code String} the random choice of the first player.
   * @param p2Choice {@code String} the random choice of the second player.
   */
  public Play(int id, Player p1, Player p2, String p1Choice, String p2Choice) {
    this.id = id;
    this.p1 = p1;
    this.p2 = p2;
    this.p1Choice = p1Choice;
    this.p2Choice = p2Choice;
    start();
  }

  private void start() {
    // compare both choices
    this.result = compareChoices(this.p1Choice, this.p2Choice);

    // update Players regarding to the result.
    updatePlayersCounter();
  }

  private int compareChoices(String p1Choice, String p2Choice) {
    if (p1Choice.equals("Rock") && p2Choice.equals("Scissors")
        || p1Choice.equals("Scissors") && p2Choice.equals("Paper")
        || p1Choice.equals("Paper") && p2Choice.equals("Rock")) {
      return -1;
    } else if (p2Choice.equals("Rock") && p1Choice.equals("Scissors")
        || p2Choice.equals("Scissors") && p1Choice.equals("Paper")
        || p2Choice.equals("Paper") && p1Choice.equals("Rock")) {
      return 1;
    }
    return 0;
  }

  private void updatePlayersCounter() {
    switch (this.result) {
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


  @Override
  public String toString() {
    String msg = "\n\n===================\n Game" + this.id + "\n===================\n";
    msg = msg.concat("[" + this.p1.getName() + "] has chosen: [" + p1Choice + "]\n"
        + "[" + this.p2.getName() + "] has chosen: [" + p2Choice + "]\n");
    switch (this.result) {
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
}
