package rpsgame.demo;

public class Play {

  private Player p1;
  private Player p2;
  private int result;
  private int id;

  public Play(int id,Player p1, Player p2) {
    this.id = id;
    this.p1 = p1;
    this.p2 = p2;
    start();
  }

  private void start() {

    // get choice
    p1.setLastChoice(p1.getChoice());
    p2.setLastChoice(p2.getChoice());

    // compare both choices
    this.result=  compareChoices(p1.getLastChoice(), p2.getLastChoice());

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
    msg = msg.concat("[" + this.p1.getName() + "] has chosen: [" + this.p1.getLastChoice() + "]\n"
        + "[" + this.p2.getName() + "] has chosen: [" + this.p2.getLastChoice() + "]\n");
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
