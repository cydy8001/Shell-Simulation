package test.driver;

import static org.junit.Assert.*;
import java.lang.reflect.InvocationTargetException;
import org.junit.Test;
import driver.Interpreter;
import exception.EmptyCommandException;
import exception.FalseCommandException;

public class InterpreterTest {
  /**
   * test extractCommand
   * @throws InstantiationException
   * @throws ClassNotFoundException
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws InvocationTargetException
   * @throws NoSuchMethodException
   * @throws SecurityException
   * @throws EmptyCommandException
   * @throws FalseCommandException
   */
  @Test
  public void testInterpreter1() throws InstantiationException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, EmptyCommandException, FalseCommandException {
    Interpreter ip = new Interpreter("cd     /d1/d3    >      file1");
    String command = ip.extractCommand("cd     /d1/d3    >      file1");
    assertEquals("cd", command);
        
  }
  /**
   * test e
   * @throws InstantiationException
   * @throws ClassNotFoundException
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws InvocationTargetException
   * @throws NoSuchMethodException
   * @throws SecurityException
   * @throws EmptyCommandException
   * @throws FalseCommandException
   */
  @Test
  public void testInterpreter2() throws InstantiationException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, EmptyCommandException, FalseCommandException {
    Interpreter ip = new Interpreter("cd     /d1/d3    >      file1");
    String command = ip.extractCommand("cd     /d1/d3    >      file1");
    String parameters = ip.extractParameters(command, "cd     /d1/d3    >      file1");
    assertEquals("/d1/d3    >      file1", parameters);
        
  }

}
