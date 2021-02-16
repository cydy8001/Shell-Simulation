package test.cmd;

import static org.junit.Assert.*;
import org.junit.Test;
import cmd.ChangeDirectory;
import cmd.PrintWorkingDirectory;

import cmd.ChangeDirectory;
import cmd.PrintWorkingDirectory;
import cmd.DirectoryList;


public class ChangeDirectoryTest extends TestSetUp {


  @Test
  public void testtoString() {
    ChangeDirectory cd = new ChangeDirectory();
    assertEquals(
        "cd DIR\n" + "Change directory to DIR, which may be relative to the \n"
            + "current directory or may be a full path. As with\n"
            + "Unix,  .. means a parent directory and a  . means\n"
            + "the current directory. The directory must be /, the\n"
            + "forward slash. The foot of the file system\n" + "is a single slash: /",
        cd.toString());
  }

  @Test
  public void testCd1() {
    PrintWorkingDirectory pwd = new PrintWorkingDirectory();
    ChangeDirectory cd = new ChangeDirectory();
    cd.setParameters("d1/d3");
    cd.execute();
    pwd.execute();
    assertEquals("/d1/d3/\r\n", outContent.toString());
  }

  @Test
  public void testCd2() {
    ChangeDirectory cd = new ChangeDirectory();
    PrintWorkingDirectory pwd = new PrintWorkingDirectory();
    cd.setParameters("/d1/");
    cd.execute();
    pwd.execute();
    assertEquals("/d1/\r\n", outContent.toString());
  }

  @Test
  public void testparent() {
    ChangeDirectory cd = new ChangeDirectory();
    PrintWorkingDirectory pwd = new PrintWorkingDirectory();
    cd.setParameters("/d1/d3");
    cd.execute();
    cd.setParameters("..");
    cd.execute();
    pwd.execute();
    assertEquals("/d1/\r\n", outContent.toString());
  }



}
