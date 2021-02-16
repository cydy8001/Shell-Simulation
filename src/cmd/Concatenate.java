package cmd;

import files.Directory;
import files.TxtFile;

/**
 * This class implements the function of cat.
 */
public class Concatenate<T> extends Command {
  private String symbol = "";
  private String outfilepath = "";
  int idxsym = -1;
  /**
   * The main code of Concatenate. This method finds the given file and print it.
   */

  @Override
  public void execute() {
    if (getParameters() == null) {
      System.out.println("File is not given");
      return;
    }
    try {
      String[] paras = getParameters().split("\\s+");
      for (int i = 0; i < paras.length; i++) {
        if (paras[i].equals(">")) {
          symbol = ">";
          idxsym = i;
          if (paras.length > i + 1) {
            outfilepath = paras[i + 1];
            break;
          }
        }
        if (paras[i].equals(">>")) {
          symbol = ">>";
          idxsym = i;
          if (paras.length > i + 1) {
            outfilepath = paras[i + 1];
            break;
          }
        }
      }
      this.run(paras);
    } catch (Exception ex) {
      ex.getMessage();
    }

  }
  public void run(String []args) {
    for (int i = 0; i < args.length; i++) {
      if(i == idxsym) {
        return;
      }
      String fileTarget = getLastFile(args[i]);
      Directory parentDirectory = getLastFileParent(args[i]);
      TxtFile txtfile = parentDirectory.findFile(fileTarget);
      if (txtfile instanceof TxtFile) {
        Echo echo = new Echo();
        echo.setParameters('"' + txtfile.getContent().trim() + '"' + " " + symbol + " " + outfilepath);
        echo.execute();
      } else {
        System.out.println("File is not found");
        return;
      }
    }
  }
  /**
   * Manual calls this method to get the documentation of cat.
   * 
   * @return The documentation of cat.
   */

  @Override
  public String toString() {
    return "cat FILE1 [FILE2 ...]\n" + "Display the contents of FILE1 and other files\n"
        + "(i.e. File2 ....) concatenated in the shell.\n"
        + "You may want to use three line breaks to separate\n"
        + "the contents of one file from the other file.\n";
  }

}
