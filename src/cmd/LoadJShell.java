package cmd;

import driver.Interpreter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class LoadJShell extends Command {
  private String symbol = "";
  private String outfilepath = "";
  String[] input;
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
        if(History.cmds.size()> 1) {
          System.out.println("You cannnot run this except at the beginning");
          throw new Exception();
        }
        input = getParameters().split("\\s+");
        if (input.length != 1) {
          System.out.println("Wrong Inputs");
          return;
        }
        this.run();
      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }

  public void run() throws Exception {
    String ins = "";
    File file = new File(input[0]);
    FileReader fr = new FileReader(file);
    char [] a = new char[3000];
    fr.read(a);
    for(char c: a) {
      ins += c;
    }
    String[] ins2 = ins.split("\n");
    for(String s: ins2){
      Interpreter in = new Interpreter(s.trim());
      in.getCmd().execute();
    }
  }

}
