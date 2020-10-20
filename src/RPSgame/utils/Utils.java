package RPSgame.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Utils {

  public static void outPutFile(String pathName, String data) {
    try {
      File file = new File(pathName);

      //if file doesnt exists, then create it
      if (!file.exists()) {
        file.createNewFile();
      }

      //true = append file
      FileWriter fileWriter = new FileWriter(file.getName());
      BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
      bufferWriter.write(data);
      bufferWriter.close();
      System.out.println("Done");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }



  public static int getNumberInput() {
    Scanner input = new Scanner(System.in);
    while(!(input.hasNextInt())) {
      System.out.println("Your input is invalid, please try again");
      input.next();
    }
    return input.nextInt();
  }

  public static int getNumberInputWithLimit(int maxNumber) {
    int number = getNumberInput();
    while(number > maxNumber || number < 0){
      System.out.println("Your input is out of range, please try again");
      number = getNumberInput();
    }
    return number;
  }

  public static Map<String, String> getQueryMap(String query) {
    String[] params = query.split("&");
    Map<String, String> map = new HashMap<String, String>();

    for (String param : params) {
      String name = param.split("=")[0];
      String value = param.split("=")[1];
      map.put(name, value);
    }
    return map;
  }
}
