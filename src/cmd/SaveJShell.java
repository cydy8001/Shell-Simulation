package cmd;

import java.util.ArrayList;
import cmd.History;
import java.io.*;

/**
 * this class implements command saveJShell
 */
public class SaveJShell extends Command {
  private String symbol = "";
  private String outfilepath = "";
  int para = -1;
  int idxsym = -1;

  /**
   * Main part of SaveJShell, Save the past input commands to a file
   */
  @Override
  public void execute() {
    try {
      if (getParameters() == null) {
        System.out.println("Please enter input Path");
        return;
      } else {
        String[] input = getParameters().split(" ");
        if (input.length > 1) {
          System.out.println("Too many Inputs");
          return;
        }
        String content = run();
        File f = new File(input[0]);
        boolean flag = f.createNewFile();
        if (flag) {
          FileWriter writer = new FileWriter(input[0], true);
          writer.write(content);
          writer.close();
        } else {
          FileWriter writer = new FileWriter(input[0], false);
          writer.write(content);
          writer.close();
        }
      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }

  public String run() throws Exception {
    String ins = "";
    for (int i = 0; i < History.cmds.size(); i++) {
      ins += History.cmds.get(i) + "\r\n";
    }
    ins = ins.trim() + " " + symbol + " " + outfilepath;
    return ins;
  }

  public String toString() {
    return "saveJShell FILE\n" + "Save the previous input commands in the file\n"
        + "on the disk as the input. Overwrites the current file if the file\n"
        + "already exists. Otherwise create one.";
  }
} // Class SaveJShell
