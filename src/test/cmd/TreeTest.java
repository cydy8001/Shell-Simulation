package test.cmd;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import cmd.Tree;
import cmd.Remove;

public class TreeTest extends TestSetUp {

  @Test
  public void test1Tree() {
    Tree tree = new Tree();
    tree.setParameters("a");
    tree.execute();
    assertEquals("There should be no input\r\n", outContent.toString());
  }

  @Test
  public void test2Tree() {
    Tree tree = new Tree();
    Remove rm = new Remove();
    rm.setParameters("/d1");
    rm.execute();
    tree.execute();
    assertEquals("\\\r\n" + "	d2\r\n" + "	file1\r\n" + "	file2\r\n" + "	file3\r\n",
        outContent.toString());
  }

  @Test
  public void test3Tree() {
    Tree tree = new Tree();
    tree.execute();
    assertEquals("\\\n" + "    d2\n" + "    d1\n" + "        d3\n"
        + "            d3file1\n" + "            d3file2\n" + "        file1.txt\n"
        + "        file2.txt\n" + "        file3.txt\n" + "        file4.txt\n"
        + "    file1\n" + "    file2\n" + "    file3\r\n", outContent.toString());
  }

  @Test
  public void testToString() {
    Tree tree = new Tree();
    assertEquals("tree\n" + "From the root directory ('\\') display the entire filesystem\n"
        + "as a tree. For every level of the tree, indent by a tab character", tree.toString());
  }
}
