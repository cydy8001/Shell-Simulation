package test.cmd;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmd.Command;
import cmd.List;
import cmd.PrintWorkingDirectory;
import cmd.DirectoryList;
import driver.Interpreter;
import driver.JShell;
import exception.EmptyCommandException;
import exception.FalseCommandException;
import files.Directory;
import files.TxtFile;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ListTest extends TestSetUp {



  @After
  public void destroy() {
    DirectoryList.destroy();
  }

  /**
   * Test ls
   * 
   */
  @Test
  public void testList1() {
    List ls = new List();
    ls.execute();
    assertEquals("d1\n" + "d2\n" + "file1\n" + "file2\n" + "file3\r\n",
        outContent.toString());
  }

  /**
   * Test ls file1
   * 
   */
  @Test
  public void testList2() {
    List ls = new List();
    ls.setParameters("file1");
    ls.execute();
    assertEquals("file1\r\n", outContent.toString());
  }

  /**
   * ls -R
   * 
   */
  @Test
  public void testList3() {
    List ls = new List();
    ls.setParameters("-R");
    ls.execute();
    assertEquals("/: \n"
        + "d1\n"
        + "d2\n"
        + "file1\n"
        + "file2\n"
        + "file3\n"
        + "/d2: \n"
        + "/d1: \n"
        + "d3\n"
        + "file1.txt\n"
        + "file2.txt\n"
        + "file3.txt\n"
        + "file4.txt\n"
        + "/d1/d3: \n"
        + "d3file1\n"
        + "d3file2\r\n", outContent.toString());
  }

  /**
   * ls d1/d3
   * 
   */
  @Test
  public void testList4() {
    List ls = new List();
    ls.setParameters("d1/d3");
    ls.execute();
    assertEquals("d3file1\n" + "d3file2\r\n", outContent.toString());
  }

  /**
   * To string test
   */
  @Test
  public void testToString() {
    List ls = new List();
    String expected =
        "If no paths are given, print the contents (file or directory) of the current directory, with a new line following\n"
            + "each of the content (file or directory).\n"
            + "Otherwise, for each path p, the order listed:\n" + "If p specifies a file, print p\n"
            + "If p specifies a directory, print p, a colon, then the contents of that directory, then an extra new line.\n"
            + "If p does not exist, print a suitable message.";
    assertEquals(expected, ls.toString());
  }

}
