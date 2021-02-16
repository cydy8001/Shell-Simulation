// **********************************************************
// Assignment2:
// Student1: Linjun Wang
// UTORID user_name: wangl129
// UT Student #: 1005690389
// Author: Linjun Wang
//
// Student2:
// UTORID user_name: cheng173
// UT Student #: 1005785839
// Author: Yihang Cheng
//
// Student3: Hongkang Yu
// UTORID user_name: yuhongk1
// UT Student #: 1005687268
// Author: Hongkang Yu
//
// Student4:
// UTORID user_name: bhand102
// UT Student #: 1005727732
// Author: Naman Bhandari
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package driver;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import cmd.DirectoryList;
import cmd.Exit;
import cmd.History;
import exception.EmptyCommandException;
import exception.FalseCommandException;
import files.Directory;
import files.TxtFile;

/**
 * This class implements the functions of JShell.
 */
public class JShell {
  // Create an instance of JShell.
  private static JShell jshell = null;

  /**
   * constructor of JShell
   */
  public JShell() {
    DirectoryList.createDirectoryList(new Directory());
    // Adding the base file(s) that our system will start with

  }

  /**
   * Get the
   * 
   * @return the instance jshell in JShell class.
   */
  public static JShell getInstance() {
    if (jshell == null)
      jshell = new JShell();

    return jshell;
  }

  /**
   * Prompt user to enter command and arguments. Call interpreter to Identify the command and call
   * the methods in that command.
   * 
   * @param args, The input from user.
   * @throws ClassNotFoundException
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws InvocationTargetException
   * @throws NoSuchMethodException
   * @throws SecurityException
   * @throws EmptyCommandException
   * @throws FalseCommandException
   */
  public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException,
      NoSuchMethodException, SecurityException, EmptyCommandException, FalseCommandException {
    JShell.getInstance();
    // the user input
    Scanner sc = new Scanner(System.in);
    String input;

    while (Exit.exit == false) {

      System.out.print("/# ");
      input = sc.nextLine();
      History.cmds.add(input);
      input = input.trim();
      try {

        Interpreter in = new Interpreter(input);
        in.getCmd().execute();
      } catch (EmptyCommandException e) {
        System.out.println("Command is empty");
      } catch (FalseCommandException e) {
        System.out.println("Wrong command");
      }

    }
    sc.close();
    System.exit(0);
  }

}
