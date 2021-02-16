package cmd;

import java.util.ArrayList;
import files.Directory;

/**
 * This class is the parent class of all the commands.
 */
public class Command {
  public String cmd;
  protected String parameters;
  public static ArrayList<Directory> stack = new ArrayList<>();
  protected static int num_stack = 0;

  public void setParameters(String p) {
    parameters = p;
  }

  public String getParameters() {
    if (parameters == null) {
      return null;
    }
    if (parameters.length() > 0) {
      return parameters;
    } else {
      return null;
    }
  }

  public void execute() {}; // Override in subclass

  protected String getLastDir(String path) {
    if (path.lastIndexOf("/") > -1) {
      path = path.substring(path.lastIndexOf("/") + 1);
    }

    return path;
  }

  protected String getLastFile(String path) {
    if (path.lastIndexOf("/") > -1) {
      path = path.substring(path.lastIndexOf("/") + 1);
    }

    return path;
  }

  /**
   * Getting the last file name parent
   * 
   * @param path
   * @return String - file name
   */
  protected Directory getLastFileParent(String path) {
    Directory parent = DirectoryList.getCurrent();

    if (path.lastIndexOf("/") > -1) {
      path = path.substring(0, path.lastIndexOf("/"));
      return DirectoryList.getDirectory(path);
    }

    return parent;
  }

  public void push(Directory d) {
    stack.add(d);
    num_stack++;
  }

  public Directory gettop() {
    if (num_stack > 0) {
      return stack.get(num_stack - 1);
    }
    return null;
  }

  public void pop() {
    if (num_stack > 0) {
      stack.remove(num_stack - 1);
    }
    num_stack--;
  }
}
