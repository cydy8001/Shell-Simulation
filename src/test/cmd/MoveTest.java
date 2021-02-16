package test.cmd;

import static org.junit.Assert.*;
import org.junit.Test;
import cmd.DirectoryList;
import cmd.Move;
import cmd.Search;

public class MoveTest extends TestSetUp{
  /**
   * test moving one directory inside of another
   */
  @Test
  public void test1execute() {
    Move move = new Move();
    move.setParameters("/d1/ /d2/");
    move.execute();
    Search search = new Search();
    search.setParameters("/d2 -type d d1");
    search.execute();
    assertEquals(outContent.toString(), "/d2/d1\r\n");
  }
  
  /**
   * test moving a file inside of a directory
   */
  @Test
  public void test2execute() {
    Move move = new Move();
    move.setParameters("file1 /d2/");
    move.execute();
    Search search = new Search();
    search.setParameters("/d2 -type f file1");
    search.execute();
    assertEquals(outContent.toString(), "/d2/file1\r\n");
  }
  
  /**
   * test moving one file to a non-existing file, which is renaming
   */
  @Test
  public void test3execute() {
    Move move = new Move();
    move.setParameters("file1 filexyz");
    move.execute();
    Search search = new Search();
    search.setParameters(". -type f filexyz");
    search.execute();
    assertEquals(outContent.toString(), "/filexyz\r\n");
  }
  
  /**
   * test moving a directory inside of a file
   */
  @Test
  public void test4execute() {
    Move move = new Move();
    move.setParameters("d1/ file1");
    move.execute();
    assertEquals(outContent.toString(), "Cannot move a directory inside of a file.\r\n");
  }
  
  /**
   * test moving a directory to a new directory
   */
  @Test
  public void test5execute() {
    Move move = new Move();
    move.setParameters("d1/ dir1/");
    move.execute();
    Search search = new Search();
    search.setParameters(". -type d dir1");
    search.execute();
    assertEquals(outContent.toString(), "/dir1\r\n");
  }
  
  /**
   * testing moving one file into another
   */
  @Test
  public void test6execute() {
    Move move = new Move();
    move.setParameters("file1 file2");
    move.execute();
    assertEquals(DirectoryList.getTxtFile("file2").getContent(),
        "File1 - Content");
  }
  
  
  
  
  
}
