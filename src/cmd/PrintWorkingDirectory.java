package cmd;

public class PrintWorkingDirectory extends Command {
  private String symbol = "";
  private String outfilepath = "";
  int idxsym = -1;

  /**
   * Print the path of the current directory.
   */
  public void execute() {
    try {
      if (getParameters() != null) {
        String[] paras = parameters.split("\\s+");
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
      }
      if(idxsym != -1 && idxsym != 0) {
        System.out.println("No parameters needed");
        throw new Exception();
      }
      if(idxsym == -1 && getParameters() != null) {
        System.out.println("No parameters needed");
        throw new Exception();
      }
      String ins = "";
      Echo echo = new Echo();
      if (DirectoryList.getCurrent().getPath().equals("/")) {
        ins = DirectoryList.getCurrent().getPath();
        echo.setParameters('"' + ins.trim() + '"' + " " + symbol + " " + outfilepath);
        echo.execute();
      } else {
        ins = DirectoryList.getCurrent().getPath() + "/";
        echo.setParameters('"' + ins.trim() + '"' + " " + symbol + " " + outfilepath);
        echo.execute();
      }
    } catch (Exception ex) {
      System.out.println("Some errors");
    }
  }

  public String toString() {
    return "pwd\n" + "Print the current working directory (including the whole\n" + "path).";
  }
}// class pwd
