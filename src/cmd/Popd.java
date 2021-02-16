package cmd;

/**
 * This class implements the functions of command echo.
 */
public class Popd extends Command {
  /**
   * Main code of popd. Change the current directory to the last directory in stack and remove the
   * directory from stack
   */
  public void execute() {
    DirectoryList.setCurrent(super.gettop());
    super.pop();
  }

  /**
   * Manual call toString method to return the doc of Popd.
   * 
   * @return documentation of Popd.
   */
  public String toString() {
    return "popd\n" + "Remove the top entry from the directory stack, and cd\n"
        + "into it. The removal must be consistent as per the LIFO\n"
        + "behavior of  a stack.  The popd command removes the top\n"
        + "most directory from the directory stack and makes it\n"
        + "the current working directory.  If there is no directory\n"
        + "onto the stack, then give appropriate error message.";
  }
} // Class Popd
