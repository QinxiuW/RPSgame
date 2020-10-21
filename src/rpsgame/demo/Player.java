package rpsgame.demo;

public class Player {

  // 1.Rock 2.Paper 3.Scissors
  public static String[] COMPLETE_CHOICE_LIST = {"Rock", "Paper", "Scissors"};
  public static String[] ROCK_CHOICE_LIST = {"Rock"};

  private String name;

  private String[] choiceList;

  private int winCounter;

  private int drawCounter;

  private String lastChoice;

  /**
   * Constructor.
   *
   * @param name   {@code String}
   * @param isFair {@code boolean}
   */
  public Player(String name, boolean isFair) {
    this.name = name;
    this.winCounter = 0;
    this.drawCounter = 0;
    this.choiceList = (isFair) ? COMPLETE_CHOICE_LIST : ROCK_CHOICE_LIST;
  }

  /**
   * get choice randomly an choice from choiceList.
   *
   * @return {@code String}
   */
  public String getRdmChoice() {
    int randNumber = (int) (Math.random() * 10) % 3;
    // unfair case
    if (choiceList.length == 1) {
      return this.choiceList[0];
    }
    // fair case: random choose
    return this.choiceList[randNumber];
  }


  public String getName() {
    return this.name;
  }

  public String[] getChoiceList() {
    return this.choiceList;
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

  public void setLastChoice(String choice){
    this.lastChoice = choice;
  }

  public String getLastChoice(){
    return this.lastChoice;
  }

  @Override
  public String toString() {
    return "[" + this.name + "] wins:" + this.winCounter + "  draws:" + this.drawCounter;
  }
}
