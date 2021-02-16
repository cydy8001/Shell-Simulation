package test.cmd;

import static org.junit.Assert.*;
import org.junit.Test;
import cmd.Exit;

public class ExitTest {
  /**
   * to string test
   */
  @Test
  public void toStringtest() {
    Exit exit = new Exit();
    assertEquals("exit\n" + "Quit the program", exit.toString());
  }
  /**
   * exit
   */
  @Test
  public void exittest1() {
    Exit exit = new Exit();
    exit.execute();
    assertEquals(true, exit.exit);
  }
}
