package cmd;

/**
 * This class implements the function of pushd.
 */
public class Pushd extends Command {
  /**
   * Main code of pushd. Save the current directory and change to the given one.
   */
  public void execute() {
    // count the number of arguments
    try {
      int count = 0;
      if (parameters == null) {
        System.out.println("Illegal arguments");
      } else {
        for (String para : parameters.split(" ")) {
          if (!para.equals("")) {
            count++;
          }
        }
        if (count == 1 && DirectoryList.getDirectory(parameters) != null) {
          super.push(DirectoryList.getCurrent());
          DirectoryList.setCurrent(DirectoryList.getDirectory(parameters));
        } else if (DirectoryList.getDirectory(parameters) == null) {
          System.out.println("No such directory");

        } else {
          System.out.println("Illegal arguments");
        }
      }
    }catch(Exception ex) {
      System.out.println(ex.getMessage());
    }
    
  }

  /**
   * Method for providing the documentation to Manual.
   * 
   * @return The documentation of pushd.
   */
  public String toString() {
    return "pushd DIR\n" + "Pushd saves the current directory onto the directory\n"
        + "stack and set the current directory to the DIR given\n"
        + "by the user. The saved directories can be returned \n" + "at any time by popd.";
  }
}
