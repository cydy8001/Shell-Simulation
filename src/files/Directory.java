package files;

import java.util.ArrayList;

public class Directory extends File {

  public ArrayList<TxtFile> filesindir = new ArrayList<TxtFile>();

  public Directory() {
    super("", null);
  }

  public Directory(String name, Directory parent) {
    super(name, parent);
  }

  /**
   * add file
   * 
   * @param f is the txtfile
   */
  public void addFile(TxtFile f) {
    if (findFile(f.getName()) == null) {
      filesindir.add(f);
    } else {
      System.out.println("File already exists");
    }
  }

  /**
   * remove file
   * 
   * @param f
   * @return true if removed
   */
  public boolean removeFile(TxtFile f) {
    File toBeRemoved = findFile(f.getName());
    if (toBeRemoved != null) {
      return filesindir.remove(toBeRemoved);
    }

    return false;
  }

  /**
   * return the list of files
   * 
   * @return filesindir
   */
  public ArrayList<TxtFile> getFilesindir() {
    return filesindir;
  }

  /**
   * Return file if find
   * 
   * @param filename
   * @return null if not find otherwise file
   */
  public TxtFile findFile(String filename) {

    for (TxtFile file : filesindir) {
      if (file.getName().equals(filename)) {
        return file;
      }
    }
    return null;
  }

}// class directory
