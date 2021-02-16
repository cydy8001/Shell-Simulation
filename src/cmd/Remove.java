package cmd;

import files.Directory;

public class Remove extends Command {
  public void execute() {
    try {
      if (this.getParameters() == null) {
        throw new Exception("No soure DIR");
      }
      String[] paras = this.getParameters().trim().split("\\s+");
      this.run(paras);
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }

  }

  public void run(String[] paras) throws Exception {
    for (String para : paras) {
      Directory d = null;
      try {
        d = DirectoryList.getDirectory(para);
      } catch (Exception ex) {
        System.out.println("You can only remove DIR");
        return;
      }

      if (d == null) {
        throw new Exception("Dir not found");
      }
      Directory current = DirectoryList.getCurrent();
      while (current.parent != null) {
        if (current.parent == d) {
          throw new Exception("You cannot remove parent dir of current working dir");
        }
        current = current.parent;
      }
      DirectoryList.removeDirectory(d);
    }

  }

  public String toString() {
    return "removes the DIR from the file system. This also removes all the children of DIR (i.e. it acts\r\n"
        + "recursively)";
  }
}
