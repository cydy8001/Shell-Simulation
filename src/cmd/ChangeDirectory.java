package cmd;

import cmd.Command;
import cmd.DirectoryList;

/**
 * This class implements the function of cd.
 */
public class ChangeDirectory<T> extends Command {
  int idxsym = -1;

  /**
   * The main code of ChangeDirectory. Set the current working directory to the one given.
   */
  public void execute() {
    if (getParameters() == null) {
      System.out.println("You should provide a path");
    }
    try {
      String[] paras = getParameters().split("\\s+");
      for (int i = 0; i < paras.length; i++) {
        if (paras[i].equals(">") || paras[i].equals(">>")) {
          idxsym = i;
        }
      }
      if (idxsym == 0) {
        System.out.println("You need to provide a path");
        throw new Exception();
      }
      if (!DirectoryList.setPath(paras[0])) {
        System.out.println("No such directory");
      } else {
        try {
          DirectoryList.setPath(paras[0]);
        } catch (Exception ex) {
          System.out.println("Command not found");
        }
      }
    } catch (

    Exception ex) {
      System.out.println(ex.getMessage());
    }
  }

  /**
   * Manual calls toString to get the doc of cd.
   * 
   * @return Documentation of cd.
   */

  public String toString() {
    return "cd DIR\n" + "Change directory to DIR, which may be relative to the \n"
        + "current directory or may be a full path. As with\n"
        + "Unix,  .. means a parent directory and a  . means\n"
        + "the current directory. The directory must be /, the\n"
        + "forward slash. The foot of the file system\n" + "is a single slash: /";
  }

}
