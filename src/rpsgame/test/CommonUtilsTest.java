package rpsgame.test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import org.junit.Assert;
import org.junit.Test;
import rpsgame.common.CommonUtils;

public class CommonUtilsTest {

  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @Test
  public void inputTest() {

    ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
    System.setIn(in);
    int result = CommonUtils.getNumberInput();
    Assert.assertEquals(result, 1);
  }

  @Test
  public void getNumberInputWithLimitTest() {
    ByteArrayInputStream in = new ByteArrayInputStream("2".getBytes());
    System.setIn(in);
    int result = CommonUtils.getNumberInputWithLimit(3);
    Assert.assertEquals(result, 2);
  }

  @Test
  public void getQueryMapTest() {
    String query = "name=Pep&age=18";
    var resultMap = CommonUtils.getQueryMap(query);

    Assert.assertEquals(resultMap.get("name"), "Pep");
    Assert.assertEquals(resultMap.get("age"), "18");
  }

  @Test
  public void outPutFileTest() {
    System.setOut(new PrintStream(outputStreamCaptor));
    CommonUtils.outputFile("test", "data");
    Assert.assertEquals("Output file action Done", outputStreamCaptor.toString()
        .trim());
    System.setOut(standardOut);
    // delete file
    File f = new File("test");
    if (f.exists()) {
      var result = f.delete();
      Assert.assertTrue(result);
    }
  }
}
