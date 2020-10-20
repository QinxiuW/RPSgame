package RPSgame.demo;

public class Game {

  private Player p1;

  private Player p2;

  // 1.Rock 2.Paper 3.Scissors
  public Game(Player p1, Player p2) {
    this.p1 = p1;
    this.p2 = p2;
  }

  public void play() {
    // TODO: choose maybe null
    String p1Choose = p1.getChoose();
    String p2Choose = p2.getChoose();
    int result = compareChooses(p1Choose, p2Choose);
    updateResult(result);
    showResultMsg(result, p1Choose, p2Choose);
  }

  // [-1]p1 wins, [0]draw, [1]p2 wins
  private int compareChooses(String p1Choose, String p2Choose) {
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

  private void updateResult(int result) {
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

  private void showResultMsg(int result, String p1Choose, String p2Choose) {
    String msg = "["+this.p1.getName() + "] has chosen: [" + p1Choose + "]\n" +
        "["+this.p2.getName() + "] has chosen: [" + p2Choose + "]\n";
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
    System.out.println(msg);
  }

}


