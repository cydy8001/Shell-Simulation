package test.cmd;

import static org.junit.Assert.*;
import org.junit.Test;
import cmd.PrintWorkingDirectory;
import cmd.ChangeDirectory;

public class PrintWorkingDirectoryTest extends TestSetUp {

	
	@Test
	public void testtoString() {
		PrintWorkingDirectory pwd = new PrintWorkingDirectory();
		assertEquals("pwd\n" + "Print the current working directory (including the whole\n" 
		+ "path).", pwd.toString());
	}

	@Test
	public void test1() {
		PrintWorkingDirectory pwd = new PrintWorkingDirectory();
		pwd.execute();
		assertEquals("/", outContent.toString());
	}
  
	@Test
	public void test2() {
		PrintWorkingDirectory pwd = new PrintWorkingDirectory();
		ChangeDirectory cd = new ChangeDirectory();
		cd.setParameters("/d1/d3");
		cd.execute();
		pwd.execute();
		assertEquals("/d1/d3/", outContent.toString());
	}
}
