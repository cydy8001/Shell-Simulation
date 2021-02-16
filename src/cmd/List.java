package cmd;

import java.util.ArrayList;
import java.util.Stack;

import files.Directory;
import files.File;
import files.TxtFile;

public class List<T> extends Command {
  private String symbol = "";
  private String outfilepath = "";
  private boolean r = false;
  private boolean pa = false;
  private boolean filpa = false;
  int idxsym = -1;
  int idxfi = -1;

  @Override
  public void execute() {
    try {
      if (parameters == null) {
        this.run();
        return;
      }
      String[] paras = parameters.split("\\s+");
      if (paras[0].equals("-R")) {
        r = true;
      }
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

      if (r) {
        if (idxsym == -1) {
          if (paras.length == 2) {
            idxfi = 1;
            if (!DirectoryList.pathExists(paras[idxfi])) {
              System.out.println("wrong path");
              throw new Exception();
            }
          }
          if (paras.length == 1) {

          }
        } else if (idxsym != -1) {
          if (idxsym == 1) {

          }
          if (idxsym == 2) {
            idxfi = 1;
            if (!DirectoryList.pathExists(paras[idxfi])) {
              System.out.println("wrong path");
              throw new Exception();
            }
          }
        }
      } else {
        if (idxsym == -1) {
          if (paras.length == 1) {
            idxfi = 0;
            if (!DirectoryList.pathExists(paras[idxfi])) {
              System.out.println("wrong path");
              throw new Exception();
            }
          }
        } else if (idxsym != -1) {
          if (idxsym == 0) {
          }
          if (idxsym == 1) {
            idxfi = 0;
            if (!DirectoryList.pathExists(paras[idxfi])) {
              System.out.println("wrong path");
              throw new Exception();
            }
          }
        }
      }
      this.run(paras);
    } catch (Exception ex) {
      System.out.println("Some errors");
    }
  }

  public void run() {
    listOn(DirectoryList.getCurrent());
  }

  public void run(String[] args) throws Exception {

    String ins = "";
    if (!r) {
      Echo echo = new Echo();
      if (idxfi == -1) {
        this.run();
        return;
      } else if (idxfi != 1) {
        File f = DirectoryList.getFile(args[0].trim());
        if (f instanceof Directory) {
          ArrayList<File> al = DirectoryList.getFilesOf((Directory) f);
          for (File t : al) {
            ins += t.getName() + "\n";
            // DirectoryList.reDirect(t.getName(), symbol, outfilepath);
          }
        }
        if (f instanceof TxtFile) {
          ins += f.getName() + "\n";
          // DirectoryList.reDirect(f.getName(), symbol, outfilepath);
        }
        echo.setParameters('"' + ins.trim() + '"' + " " + symbol + " " + outfilepath);
        echo.execute();
      }
    } else {
      if (idxfi == -1) {
        File f = DirectoryList.getCurrent();
        outhelper(f);
      } else if (idxfi == 1) {
        File f = DirectoryList.getFile(args[1].trim());
        outhelper(f);
      }
    }
  }

  private void outhelper(File f) throws Exception {
    Echo echo = new Echo();
    String ins = "";
    if (f instanceof Directory) {
      Stack<Directory> st = new Stack();
      st.push((Directory) f);
      while (st.size() != 0) {
        Directory onedir = st.pop();
        ins += onedir.getPath() + ": " + "\n";
        // DirectoryList.reDirect(onedir.getPath() + ": ", symbol, outfilepath);
        ArrayList<File> al = DirectoryList.getFilesOf(onedir);
        for (File fi : al) {
          if (fi instanceof Directory) {
            ins += fi.getName() + "\n";
            // DirectoryList.reDirect(fi.getName(), symbol, outfilepath);
            st.push((Directory) fi);
          }
          if (fi instanceof TxtFile) {
            ins += fi.getName() + "\n";
            // DirectoryList.reDirect(fi.getName(), symbol, outfilepath);
          }
        }
      }
    }
    if (f instanceof TxtFile) {
      ins += f.getName() + "\n";
      // DirectoryList.reDirect(f.getName(), symbol, outfilepath);
    }
    echo.setParameters('"' + ins.trim() + '"' + " " + symbol + " " + outfilepath);
    echo.execute();
  }

  public void listOn(Directory targetDirectory) {
    // Listing both directories and files
    String ins = "";
    for (File file : DirectoryList.getFilesOf(targetDirectory)) {
      ins += file.getName() + "\n";
    }
    Echo echo = new Echo();
    echo.setParameters('"' + ins.trim() + '"' + " " + symbol + " " + outfilepath);
    echo.execute();
  }

  public String toString() {
    return "If no paths are given, print the contents (file or directory) of the current directory, with a new line following\n"
        + "each of the content (file or directory).\n"
        + "Otherwise, for each path p, the order listed:\n" + "If p specifies a file, print p\n"
        + "If p specifies a directory, print p, a colon, then the contents of that directory, then an extra new line.\n"
        + "If p does not exist, print a suitable message.";
  }
}
