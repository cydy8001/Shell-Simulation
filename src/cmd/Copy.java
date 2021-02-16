package cmd;

import files.Directory;
import files.TxtFile;

public class Copy<T> extends Command {
  private String arg1 = "";
  private String arg2 = "";

  public void execute() {
    if (CountNumOfArg() != true) {
      System.out.println("Wrong number of arguments");
      return;
    }
    GetArgs();
    if (arg1.charAt(arg1.length() - 1) == '/') {
      arg1 = arg1.substring(0, arg1.length() - 1);
      if (DirectoryList.findDir(arg1)) {
        if (arg2.charAt(arg2.length() - 1) == '/') {
          arg2 = arg2.substring(0, arg2.length() - 1);
          if (DirectoryList.findDir(arg2)) {
            MoveDir1ToDir2();
          } else {
            RenameDir();
          }
        }
      }
    } else {
      if (DirectoryList.findFile(arg1)) {
        if (arg2.charAt(arg2.length() - 1) == '/') {
          arg2 = arg2.substring(0, arg2.length() - 1);
          if (DirectoryList.findDir(arg2)) {
            MoveFileToDir();
          } else {
            System.out.println("Given directory doesn't exist");
          }
        } else {
          if (DirectoryList.findFile(arg2)) {
            OverwriteArg2File();
          } else {
            RenameFile();
          }

        }
      }
    }
  }

  private void OverwriteArg2File() {
    Directory Arg1Parent = DirectoryList.getFile(arg1).getParent();
    Directory Arg2Parent = DirectoryList.getFile(arg2).getParent();
    TxtFile file = new TxtFile(DirectoryList.getFile(arg2).getName(), Arg2Parent,
        ((TxtFile) DirectoryList.getFile(arg1)).getContent());
    Arg1Parent.removeFile((TxtFile) DirectoryList.getFile(arg2));
    Arg2Parent.addFile(file);
  }

  private void MoveFileToDir() {
    TxtFile file = new TxtFile(DirectoryList.getFile(arg1).getName(),
        DirectoryList.getDirectory(arg2), ((TxtFile) DirectoryList.getFile(arg1)).getContent());
    DirectoryList.getDirectory(arg2).addFile(file);
  }

  private void MoveDir1ToDir2() {
    Directory Arg1Dir = DirectoryList.getDirectory(arg1);
    Directory Arg2Dir = DirectoryList.getDirectory(arg2);
    Directory dir = new Directory(Arg1Dir.getName(), Arg2Dir);
    DirectoryList.addDir(dir);
  }

  private void RenameDir() {
    int lastIdxOfSlash = arg2.lastIndexOf('/');
    if (lastIdxOfSlash != -1) {
      if (DirectoryList.findDir(arg2.substring(0, lastIdxOfSlash)) || lastIdxOfSlash == 0) {
        DirectoryList.getDirectory(arg1).setName(arg2.substring(lastIdxOfSlash + 1));
      }
    } else {
      DirectoryList.getDirectory(arg1).setName(arg2);
    }
  }

  private void RenameFile() {
    int lastIdxOfSlash = arg2.lastIndexOf('/');
    if (lastIdxOfSlash != -1) {
      if (DirectoryList.findDir(arg2.substring(0, lastIdxOfSlash)) || lastIdxOfSlash == 0) {
        DirectoryList.getFile(arg1).setName(arg2.substring(lastIdxOfSlash + 1));
      }
    } else {
      DirectoryList.getFile(arg1).setName(arg2);
    }
  }

  private void GetArgs() {
    for (String para : parameters.split(" ")) {
      if (!para.equals("")) {
        if (arg1.equals("")) {
          arg1 = para;
        } else if (arg2.equals("")) {
          arg2 = para;
        }
      }
    }
  }

  private boolean CountNumOfArg() {
    int count = 0;
    for (String para : parameters.split(" ")) {
      count++;
    }
    if (count != 2) {
      return false;
    } else {
      return true;
    }
  }

  public String toString() {
    return " Like mv, but dont remove OLDPATH. If OLDPATH\n"
        + "is a directory, recursively copy the contents.";
  }
}
