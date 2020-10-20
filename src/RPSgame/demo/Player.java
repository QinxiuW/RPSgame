package RPSgame.demo;

public class Player {

  // 1.Rock 2.Paper 3.Scissors
  public final static String[] COMPLETE_CHOICE_LIST = {"Rock", "Paper", "Scissors"};
  public final static String[] ROCK_CHOICE_LIST = {"Rock"};

  private String name;

  private String[] chooseList;

  private int winCounter;

  private int drawCounter;

  public Player(String name, boolean isFair) {
    this.name = name;
    this.winCounter = 0;
    this.drawCounter = 0;
    this.chooseList = (isFair) ? COMPLETE_CHOICE_LIST : ROCK_CHOICE_LIST;
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
