package cmd;

/**
 * This class is for man command
 * 
 * @author cheng173
 */

import driver.Interpreter;

public class Manual extends Command {
  private String symbol = "";
  private String outfilepath = "";
  int idxsymb = -1;
  /**
   * Override parent class
   */
  @Override
  public void execute() {
    try {
      if (this.getParameters() == null) {
        throw new Exception("Empty argument");
      }
      String[] paras = getParameters().trim().split("\\s+");
      
      for (int i = 0; i < paras.length; i++) {
        if (paras[i].equals(">")) {
          symbol = ">";
          idxsymb = i;
          if (paras.length > i + 1) {
            outfilepath = paras[i + 1];
          }
          break;
        }
        if (paras[i].equals(">>")) {
          symbol = ">>";
          idxsymb = i;
          if (paras.length > i + 1) {
            outfilepath = paras[i + 1];
          }
          break;
        }
      }
      if (paras.length != 1 && idxsymb == -1) {
        System.out.println("Number of argument is not 1");
        throw new Exception();
      }
      if (idxsymb != -1 && idxsymb > 1) {
        System.out.println("Number of argument is not 1");
        throw new Exception();
      }
      if (idxsymb == -1 && paras.length > 1) {
        System.out.println("Number of argument is not 1");
        throw new Exception();
      }
      this.run(paras);
    } catch (Exception e) {
      System.out.println("Command not found");
    }
  };

  /**
   * Runs for man
   * 
   * @throws Exception
   */
  public void run(String[] args) throws Exception {
    Class c = Class.forName("cmd." + Interpreter.nametoClass(args[0]));
    Command cmd = (Command) c.getDeclaredConstructor().newInstance();
    Echo echo = new Echo();
    echo.setParameters('"' + cmd.toString() + '"' + " " + symbol + " " + outfilepath);
    echo.execute();
  }



  @Override
  public String toString() {
    return "man CMD[CMD2 ...]\n" + "Print documentation for CMD (s)";
  }
}
