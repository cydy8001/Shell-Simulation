package cmd;

import files.Directory;
import files.TxtFile;
/**
 * This class implements command move.
 */
public class Move extends Command {

  private String arg1 = "";
  private String arg2 = "";
  /**
   * the main part of the command
   */
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

            MoveDir1ToDir2(arg1, arg2);
          } else {
            RenameDir();
          }
        }
        else {
          System.out.println("Cannot move a directory inside of a file.");
        }
      }
    } else {
      if (DirectoryList.findFile(arg1)) {
        if (arg2.charAt(arg2.length() - 1) == '/') {
          arg2 = arg2.substring(0, arg2.length() - 1);
          if (DirectoryList.findDir(arg2)) {
            MoveFileToDir(arg1, arg2);
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
  
  /**
   * overwrite the second file with the first one
   */
  private void OverwriteArg2File() {
    Directory Arg1Parent = DirectoryList.getFile(arg1).getParent();
    Directory Arg2Parent = DirectoryList.getFile(arg2).getParent();
    TxtFile file = new TxtFile(DirectoryList.getFile(arg2).getName(), Arg2Parent,
        ((TxtFile) DirectoryList.getFile(arg1)).getContent());
    Arg1Parent.removeFile((TxtFile) DirectoryList.getFile(arg1));
    Arg1Parent.removeFile((TxtFile) DirectoryList.getFile(arg2));
    Arg2Parent.addFile(file);
  }
  
  /**
   * move the file to the given directory
   * @param filestr, name of the file
   * @param dirstr, name of the directory
   */
  private void MoveFileToDir(String filestr, String dirstr) {
    TxtFile file =
        new TxtFile(DirectoryList.getFile(filestr).getName(), DirectoryList.getDirectory(dirstr),
            ((TxtFile) DirectoryList.getFile(filestr)).getContent());
    DirectoryList.getDirectory(dirstr).addFile(file);
    DirectoryList.getFile(filestr).getParent().removeFile((TxtFile) DirectoryList.getFile(filestr));
  }
  /**
   * move the first directory inside the second one
   * @param dir1str, name of the first directory
   * @param dir2str, name of the second directroy
   */
  private void MoveDir1ToDir2(String dir1str, String dir2str) {
    Directory Arg1Dir = DirectoryList.findExistingDir(dir1str);
    Arg1Dir.setParent(DirectoryList.getDirectory(dir2str));
  }
  
  /**
   * rename the directory
   */
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
  
  /**
   * rename the file
   */
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
  
  /**
   * split the input string into arguments
   */
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
  
  /**
   * count the number of arguments and identify whether it's legal 
   * @return true if the number of arguments is legal, false otherwise
   */
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
    return " Move item OLDPATH to NEWPATH. Both OLD-\r\n"
        + "PATH and NEWPATH may be relative to the current directory or may be full paths.";
  }
}
