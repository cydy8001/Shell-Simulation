package test.cmd;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import cmd.ChangeDirectory;
import cmd.List;
import cmd.PrintWorkingDirectory;
import cmd.Pushd;
import cmd.Remove;
import files.Directory;

public class PushdTest extends TestSetUp {

  /**
   * pushd d1
   */
  @Test
  public void testPushd1() {
    Pushd pushd = new Pushd();
    //PrintWorkingDirectory pwd = new PrintWorkingDirectory();
    pushd.setParameters("d1");
    pushd.execute();
    ArrayList<Directory> stack = pushd.stack;
    assertEquals("", stack.get(0).getName());
  }
  /**
   * pushd d1
   * pwd
   */
  @Test
  public void testPushd2() {
    ChangeDirectory cd = new ChangeDirectory();
    cd.setParameters("..");
    cd.execute();
    Pushd pushd = new Pushd();
    PrintWorkingDirectory pwd = new PrintWorkingDirectory();
    pushd.setParameters("d1");
    pushd.execute();
    pwd.execute();
    assertEquals("/d1/\r\n", outContent.toString());
  }
  /**
   * To string test
   */
  @Test
  public void testToString() {
    Pushd pushd = new Pushd();
    String expected = "pushd DIR\n" + "Pushd saves the current directory onto the directory\n"
        + "stack and set the current directory to the DIR given\n"
        + "by the user. The saved directories can be returned \n" + "at any time by popd.";
    assertEquals(expected, pushd.toString());
  }

}
