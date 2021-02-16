package cmd;

import java.util.ArrayList;

/**
 * This class implements the functions of command history.
 */
public class History extends Command {
  public static ArrayList<String> cmds = new ArrayList<String>();
  private String symbol = "";
  private String outfilepath = "";
  int para = -1;
  int idxsym = -1;

  /**
   * Main part of History, print the past input commands.
   */
  @Override
  public void execute() {
    try {
      if (this.getParameters() == null) {
        para = cmds.size();
        this.run();
      } else {
        String[] paras = parameters.split("\\s+");
        for (int i = 0; i < paras.length; i++) {
          if (paras[i].equals(">>")) {
            symbol = ">>";
            idxsym = i;
            if (i + 1 < paras.length) {
              outfilepath = paras[i + 1];
              break;
            }
          }
          if (paras[i].equals(">")) {
            symbol = ">";
            idxsym = i;
            if (i + 1 < paras.length) {
              outfilepath = paras[i + 1];
              break;
            }
          }
        }
        if (idxsym > 1) {
          System.out.println("illegal parameters");
          throw new Exception();
        } else if (idxsym == -1 || symbol.equals("")) {
          para = Integer.parseInt(paras[0]);
        }else if(idxsym == 1) {
          para = Integer.parseInt(paras[0]);
        }
        
        if (para > cmds.size()) {
          para = cmds.size();
        }
        this.run();
      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }

  public void run() throws Exception {
    String ins = "";
    for (int i = cmds.size() - para; i < cmds.size(); i++) {
      ins += i + " : " + cmds.get(i) + "\n";
    }
    Echo echo = new Echo();
    ins = '"' + ins.trim() + '"' + " " + symbol + " " + outfilepath;
    echo.setParameters(ins);
    echo.execute();
  }

  /**
   * Manual calls toString to get the doc of history.
   * 
   * @return doc of history
   */
  public String toString() {
    return "history [number]\n" + "This command will print out recent commands, one command\n"
        + "per line.";
  }
}
