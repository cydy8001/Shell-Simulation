package cmd;

import files.Directory;

/**
 * This class implements the functions of command mkdir.
 */
public class MakeDirectory extends Command {
  /**
   * Helper function of MakeDirectory, check for valid input names.
   * 
   * @return Boolean which tells if input name is valid.
   */
  private boolean check(String str) {
    if (str.isEmpty())
      return false;
    for (int i = 0; i < str.length(); i++) {
      if (!(Character.isLetterOrDigit(str.charAt(i)) || str.charAt(i) == '/')) {
        return false;
      }
    }
    return true;
  }

  /**
   * Main part of mkdir, evaluate the inputs and make the directories
   */
  @Override
  public void execute() {
    // Separate input into different names
    try {
      String[] names = parameters.split(" ", 0);
      Directory dir;
      String path = "";
      String dirname = "";
      for (String name : names) {
        // Check for valid name
        if (check(name)) {
        	if(name.indexOf('/')==-1) {
        		dir = new Directory(name, DirectoryList.getCurrent());
        		// Check if the path already exists
        		if (DirectoryList.getDirectory(name) != null) {
        			System.out.println("Path already exists");
        			return;
        		}
        	}
        	else {
        		path = name.substring(0, name.lastIndexOf('/')+1);
        		dirname = name.substring(name.lastIndexOf('/')+1);
        		dir = new Directory(dirname, DirectoryList.getDirectory(path));
        	}
        	DirectoryList.addDir(dir);
        } else {
        	if (name.isBlank())
        		continue;
        	System.out.println("Illegal name");
        	return;
        }
      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }

  /**
   * Manual call toString method to return the doc of MakeDirectory.
   * 
   * @return documentation of MakeDirectory.
   */
  @Override
  public String toString() {
    return "mkdir DIR1 DIR2 ..." + "This command takes in several arguments\n"
    	+ "as names of directories. Create directories, each of \n"
    	+ "which may be relative to the current directory or may \n"
    	+ "be a full path. If creating the previous directory\n"
    	+ "results in an error, donnot proceed with the rest of \n"
    	+ "the input and return the error.";
  }
} // Class MakeDirectory
