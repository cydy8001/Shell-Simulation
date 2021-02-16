package test.cmd;

import static org.junit.Assert.*;
import org.junit.Test;
import cmd.Search;

public class SearchTest extends TestSetUp{
  
  /**
   * test searching for a directory from one path
   */
  @Test
  public void test1execute() {
    Search search = new Search();
    search.setParameters("d1 -type d d3");
    search.execute();
    assertEquals(outContent.toString(), "/d1/d3\r\n");
  }
  
  /**
   * test searching for a directory from 2 paths
   */
  @Test
  public void test2execute() {
    Search search = new Search();
    search.setParameters(". /d1 -type d d3");
    search.execute();
    assertEquals(outContent.toString(), "/d1/d3\r\n"
        + "/d1/d3\r\n");
  }
  
  /**
   * test searching for a file
   */
  @Test
  public void test3execute() {
    Search search = new Search();
    search.setParameters("/d1 -type f d3file1");
    search.execute();
    assertEquals(outContent.toString(), "/d1/d3/d3file1\r\n");
  }
  
  /**
   * test searching for a non-existing file and printing nothing
   */
  @Test
  public void test4execute() {
    Search search = new Search();
    search.setParameters("/d1 -type f filexyz");
    search.execute();
    assertEquals(outContent.toString(), "");
  }
}
