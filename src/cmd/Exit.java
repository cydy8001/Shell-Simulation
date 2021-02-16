package cmd;

/**
 * This class implements the functions of command exit.
 */
public class Exit extends Command {

  public static boolean exit = false;

  /**
   * The main code of exit. Exit the JShell.
   */
  public void execute() {
    exit = true;
  }

  @Override
  /**
   * Manual calls toString to get the doc of exit.
   * 
   * @return doc of exit
   */
  public String toString() {
    return "exit\n" + "Quit the program";
  }

}
