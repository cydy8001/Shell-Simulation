package test.cmd;

import static org.junit.Assert.*;
import org.junit.Test;
import cmd.Concatenate;
import cmd.Manual;

public class ManualTest extends TestSetUp {
  /**
   * man cd
   */
  @Test
  public void testMan1() {
    Manual man = new Manual();
    man.setParameters("cd");
    man.execute();
    assertEquals(
        "cd DIR\n" + "Change directory to DIR, which may be relative to the \n"
            + "current directory or may be a full path. As with\n"
            + "Unix,  .. means a parent directory and a  . means\n"
            + "the current directory. The directory must be /, the\n"
            + "forward slash. The foot of the file system\n" + "is a single slash: /\r\n",
        outContent.toString());
  }

  /**
   * man ls >
   */
  @Test
  public void testMan2() {
    Manual man = new Manual();
    man.setParameters("ls");
    man.execute();
    assertEquals(
        "If no paths are given, print the contents (file or directory) of the current directory, with a new line following\n"
            + "each of the content (file or directory).\n"
            + "Otherwise, for each path p, the order listed:\n" + "If p specifies a file, print p\n"
            + "If p specifies a directory, print p, a colon, then the contents of that directory, then an extra new line.\n"
            + "If p does not exist, print a suitable message.\r\n",
        outContent.toString());
  }

  /**
   * man pwd
   */
  @Test
  public void testMan3() {
    Manual man = new Manual();
    Concatenate cat = new Concatenate();
    man.setParameters("pwd    >    file1");
    man.execute();
    cat.setParameters("file1");
    cat.execute();
    assertEquals(
        "pwd\n" + "Print the current working directory (including the whole\n" + "path).\r\n",
        outContent.toString());
  }

  /**
   * to String man
   */
  @Test
  public void toStringmantest() {
    Manual man = new Manual();
    assertEquals("man CMD[CMD2 ...]\n" + "Print documentation for CMD (s)", man.toString());
  }

}
