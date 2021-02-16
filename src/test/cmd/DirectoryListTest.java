package test.cmd;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import cmd.DirectoryList;
import files.Directory;
import files.File;
import files.TxtFile;

public class DirectoryListTest extends TestSetUp {
  /**
   * test get directory
   */
  @Test
  public void testDirectoryList1() {
    Directory d = DirectoryList.getDirectory("d1/d3");
    assertEquals("d3", d.getName());
  }

  /**
   * test get file
   */
  @Test
  public void testDirectoryList2() {
    File d = DirectoryList.getFile("d1/file2.txt");
    assertEquals("file2.txt", d.getName());
  }

  /**
   * test getTxtfile
   */
  @Test
  public void testDirectoryList3() {
    TxtFile d = DirectoryList.getTxtFile("d1/file3.txt");
    assertEquals("file3.txt", d.getName());
  }

  /**
   * test pathExists
   */
  @Test
  public void testDirectoryList4() {
    Boolean b = DirectoryList.pathExists("/d1/d3");
    assertEquals(true, b);
  }

  /**
   * test getFilesOf
   */
  @Test
  public void testDirectoryList5() {
    ArrayList<Directory> al = DirectoryList.getList();
    Directory dir = null;

    for (Directory d : al) {
      if (d.getName().equals("d1")) {
        dir = d;
      }
    }
    ArrayList<File> f = DirectoryList.getFilesOf(dir);
    String result = "";
    for (File ff : f) {
      result += ff.getName();
    }
    assertEquals("d3" + "file1.txt" + "file2.txt" + "file3.txt" + "file4.txt", result);
  }

  /**
   * test findDirOn
   */
  @Test
  public void testDirectoryList6() {
    ArrayList<Directory> al = DirectoryList.getList();
    Directory dir = null;
    Directory dir2 = null;
    for (Directory d : al) {
      if (d.getName().equals("d1")) {
        dir = d;
      }
      if (d.getName().equals("d3")) {
        dir2 = d;
      }
    }
    assertEquals(dir2, DirectoryList.findDirOn(dir, "d3"));
  }
  /**
   * Test reDirect
   * @throws Exception 
   * 
   */
  @Test
  public void testDirectoryList7() throws Exception {
    DirectoryList.reDirect("one test", ">>", "/file1");
    TxtFile f = DirectoryList.getTxtFile("/file1");
    assertEquals("File1 - Content"+"one test", f.getContent());
  }
}
