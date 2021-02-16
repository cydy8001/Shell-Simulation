package test.cmd;

import static org.junit.Assert.*;
import org.junit.Test;
import cmd.DirectoryList;
import cmd.Echo;
import files.TxtFile;

public class EchoTest extends TestSetUp {
	
	/**
	 *  test overwriting file1 to "123"
	 */
	@Test
	public void test1execute() {
		Echo echo = new Echo();
		echo.setParameters("\"123\" > file1");
		echo.execute();
		assertEquals(((TxtFile) DirectoryList.getFile("/file1")).getContent(), "123");
	}
		
	/**
	 * test appending file1 with "123"
	 */
	@Test
	public void test2execute() {
		Echo echo = new Echo();
		echo.setParameters("\"123\" >> file1");
		echo.execute();
		assertEquals(((TxtFile) DirectoryList.getFile("/file1")).getContent()
				, "123123");
	}
	
	/**
	 * test printing the input string using echo
	 */
	@Test
	public void test3execute() {
		Echo echo = new Echo();
		echo.setParameters("\"123456\"");
		echo.execute();
		assertEquals(outContent.toString(), "123456\r\n");
	}
	
	/**
	 * test the output when given an invalid input
	 */
	@Test
	public void test4execute() {
      Echo echo = new Echo();
      echo.setParameters("\"123456\" >>");
      echo.execute();
      assertEquals(outContent.toString(), "Illegal arguments.\r\n");
	}
}
