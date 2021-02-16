package test.cmd;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import cmd.DirectoryList;
import cmd.History;
import cmd.List;
import cmd.PrintWorkingDirectory;

public class HistoryTest extends TestSetUp {
  @Before
  public void reSet() {
    History.cmds = new ArrayList<String>();
  }
  @After
  public void destroy() {
    History.cmds = new ArrayList<String>();
  }

  @Test
  public void testHistory1() {

    List ls = new List();
    PrintWorkingDirectory pwd = new PrintWorkingDirectory();
    History history = new History();
    ls.execute();
    pwd.execute();
    outContent.reset();
    history.cmds.add("ls");
    history.cmds.add("pwd");
    history.cmds.add("history");
    history.execute();

    assertEquals("0 : ls\n" + "1 : pwd\n" + "2 : history\r\n", outContent.toString());
  }
  @Test
  public void testHistory2() {

    List ls = new List();
    PrintWorkingDirectory pwd = new PrintWorkingDirectory();
    History history = new History();
    ls.execute();
    pwd.execute();
    outContent.reset();
    history.cmds.add("ls");
    history.cmds.add("pwd");
    history.cmds.add("history 1000");
    history.setParameters("1000");
    history.execute();

    assertEquals("0 : ls\n" + "1 : pwd\n" + "2 : history 1000\r\n", outContent.toString());
  }
  @Test
  public void testHistory3() {

    List ls = new List();
    PrintWorkingDirectory pwd = new PrintWorkingDirectory();
    History history = new History();
    ls.execute();
    pwd.execute();
    outContent.reset();
    history.cmds.add("ls");
    history.cmds.add("pwd");
    history.cmds.add("history 1");
    history.setParameters("1");
    history.execute();

    assertEquals("2 : history 1\r\n", outContent.toString());
  }
}
