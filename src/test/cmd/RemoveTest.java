package test.cmd;

import static org.junit.Assert.*;
import org.junit.Test;
import cmd.ChangeDirectory;
import cmd.List;
import cmd.Remove;

public class RemoveTest extends TestSetUp {
  /**
   * rm d1 ls
   */
  @Test
  public void testRemove1() {
    Remove rm = new Remove();
    List ls = new List();
    rm.setParameters("d1");
    rm.execute();
    ls.execute();
    assertEquals("d2\r\n" + "file1\r\n" + "file2\r\n" + "file3\r\n", outContent.toString());
  }

  /**
   * rm d1/d3 ls d1
   */
  @Test
  public void testRemove2() {
    Remove rm = new Remove();
    List ls = new List();
    rm.setParameters("d1/d3");
    rm.execute();
    ls.setParameters("d1");
    ls.execute();
    assertEquals("file1.txt\r\n" + "file2.txt\r\n" + "file3.txt\r\n" + "file4.txt\r\n",
        outContent.toString());
  }

  /**
   * rm d1/d3 
   * ls d1
   */
  @Test
  public void testRemove3() {
    Remove rm = new Remove();
    List ls = new List();
    rm.setParameters("d1/d3");
    rm.execute();
    ls.setParameters("d1");
    ls.execute();
    assertEquals("file1.txt\r\n" + "file2.txt\r\n" + "file3.txt\r\n" + "file4.txt\r\n",
        outContent.toString());
  }

  /**
   * cd d1/d3 
   * rm /d1
   */
  @Test
  public void testRemove4() {
    Remove rm = new Remove();
    ChangeDirectory cd = new ChangeDirectory();
    cd.setParameters("d1/d3");
    cd.execute();
    rm.setParameters("/d1");
    try {
      rm.execute();
    }catch (RuntimeException re) {
      assertEquals("You cannot remove parent dir of current working dir", re.getMessage());
    }
   
  }

  /**
   * To string test
   */
  @Test
  public void testToString() {
    Remove rm = new Remove();
    String expected =
        "removes the DIR from the file system. This also removes all the children of DIR (i.e. it acts\r\n"
            + "recursively)";
    assertEquals(expected, rm.toString());
  }

}
