package RPSgame.demo;

public class Player {

  private String name;
  // 1.Rock 2.Paper 3.Scissors
  private String[] chooseList;

  private int winCounter;

  private int drawCounter;

  public Player(String name, String[] chooseList) {
    this.name = name;
    this.chooseList = chooseList;
    this.winCounter = 0;
    this.drawCounter = 0;
  }

  public String getName() {
    return this.name;
  }

  public String[] getChooseList() {
    return this.chooseList;
  }

  public int getWinCounter() {
    return winCounter;
  }

  public int getDrawCounter() {
    return drawCounter;
  }

  public void setWinCounter(int winCounter) {
    this.winCounter = winCounter;
  }

  public void setDrawCounter(int drawCounter) {
    this.drawCounter = drawCounter;
  }

  public String getChoice() {
    int randNumber = (int) (Math.random() * 10) % 3;
    // unfair case
    if (chooseList.length == 1) {
      return this.chooseList[0];
    }
    // fair case: random choose
    return this.chooseList[randNumber];
  }
}
