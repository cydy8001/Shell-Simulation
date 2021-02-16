package test.cmd;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import cmd.ChangeDirectory;
import cmd.PrintWorkingDirectory;
import cmd.Pushd;
import cmd.Popd;
import files.Directory;

public class PopdTest extends TestSetUp {

  @Test
  public void test1() {
    Pushd pushd = new Pushd();
    ChangeDirectory cd = new ChangeDirectory();
    PrintWorkingDirectory pwd = new PrintWorkingDirectory();
    Popd popd = new Popd();
    cd.setParameters("/");
    cd.execute();
    pushd.setParameters("d1");
    pushd.execute();
    popd.execute();
    pwd.execute();
    assertEquals("/", outContent.toString());
  }

  @Test
  public void test2() {
	  Popd popd = new Popd();
	  popd.execute();
	  assertEquals(null, outContent.toString());
  }
  /**
   * To string test
   */
  @Test
  public void testToString() {
    Popd popd = new Popd();
    assertEquals("popd\n" + "Remove the top entry from the directory stack, and cd\n"
            + "into it. The removal must be consistent as per the LIFO\n"
            + "behavior of  a stack.  The popd command removes the top\n"
            + "most directory from the directory stack and makes it\n"
            + "the current working directory.  If there is no directory\n"
            + "onto the stack, then give appropriate error message.", popd.toString());
  }

}
