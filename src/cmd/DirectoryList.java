package cmd;

import java.util.ArrayList;
import java.util.Stack;

import files.Directory;
import files.File;
import files.TxtFile;

// This class stores all directories into arraylist
public class DirectoryList {
  private static ArrayList<Directory> list = new ArrayList<>();
  private static DirectoryList self;
  private static Directory current;
  private static Directory root;
  private static Directory relativeCurrent;

  private DirectoryList(Directory root) {
    DirectoryList.root = root;
    DirectoryList.current = root;
    DirectoryList.relativeCurrent = root;
    list = new ArrayList<>();
    list.add(root);

  }

  /**
   * create directorylist
   * 
   * @param root
   * @return direcotyrlist
   */
  public static DirectoryList createDirectoryList(Directory root) {
    if (!(self instanceof DirectoryList)) {
      self = new DirectoryList(root);
    }

    return self;
  }

  /**
   * get list
   * 
   * @return list
   */
  public static ArrayList<Directory> getList() {
    return list;
  }

  /**
   * get Root directory
   * 
   * @return root
   */
  public static Directory getRoot() {
    return root;
  }
  /**
   * set current, save current to relativeCurrent
   * 
   * @param directory
   */
  public static void setCurrent(Directory directory) {
    relativeCurrent = current;
    current = directory;
  }

  /**
   * get current
   * 
   * @return current
   */
  public static Directory getCurrent() {
    return DirectoryList.current;
  }

  /**
   * set relative current
   * 
   * @param directory
   */
  public static void setRelativeCurrent(Directory directory) {
    relativeCurrent = directory;
  }

  /**
   * get relativecurrent
   * 
   * @return
   */
  public static Directory getRelativeCurrent() {
    return DirectoryList.relativeCurrent;
  }

  /**
   * add directory
   * 
   * @param directory
   */
  public static void addDir(Directory directory) {
    for (File file : getFilesOf(directory.getParent())) {
      if (file.getName().equals(directory.getName())) {
        return;
      }
    }

    getList().add(directory);

  }

  /**
   * Create Txtfile by given path and create directory at the same time
   * 
   * @param path
   * @throws Exception
   */
  public static void createTxtFile(String path) throws Exception {
    String[] names = path.trim().split("/");
    for (int i = 0; i < names.length; i++) {
      if (names[i] == ".") {
        continue;
      }
      if (names[i] == "..") {
        throw new Exception("No such file");
      }

    }
  }

  /**
   * Setting path to current if exists
   *
   * @param path - String
   * @return true if was possible to set path
   */
  public static boolean setPath(String path) {
    Directory directory = DirectoryList.getDirectory(path);

    if (directory != null) {
      DirectoryList.setCurrent(directory);

      return true;
    }

    return false;
  }

  /**
   * get directory by path
   * 
   * @param path
   * @return directory
   */
  public static Directory getDirectory(String path) {
    if (path == null) {
      return null;
    }
    if (path.equals("")) {
      return current;
    }
    if (path.charAt(0) == '/') {
      DirectoryList.setRelativeCurrent(root);
      path = path.substring(1);
    }

    String[] directories = path.split("/");

    for (String directory : directories) {
      if (directory.equals(".")) {
        continue;
      }
      Directory virtual = DirectoryList.getRelativeCurrent();
      Directory parent = virtual.getParent();

      if (directory.equals("..")) {

        if (parent == null) {
          DirectoryList.setRelativeCurrent(DirectoryList.getCurrent());
          return null;
        }

        DirectoryList.setRelativeCurrent(parent);
      } else {

        Directory currentDirectory;
        currentDirectory = DirectoryList.findDirOn(virtual, directory);

        if (currentDirectory == null) {
          DirectoryList.setRelativeCurrent(DirectoryList.getCurrent());
          return null;
        }

        DirectoryList.setRelativeCurrent(currentDirectory);
      }

    }

    Directory directoryFound = DirectoryList.getRelativeCurrent();
    DirectoryList.setRelativeCurrent(DirectoryList.getCurrent());

    return directoryFound;
  }

  /**
   * Getting file by given path
   *
   * @param path
   * @return found File or null
   */
  public static File getFile(String path) {
    Directory parent;
    String filename = path;
    parent = DirectoryList.getCurrent();

    if (path.contains("/")) {
      filename = path.substring(path.lastIndexOf("/") + 1);
      path = path.substring(0, path.lastIndexOf("/"));
      parent = getDirectory(path);
      if (parent == null) {
        return null;
      }
    }
    for (File file : DirectoryList.getFilesOf(parent)) {
      if (file.getName().equals(filename)) {
        return file;
      }
    }
    return null;

  }
  
  /**
   * get the txt file with the given path
   * @param path of the file
   * @return txt file
   */
  public static TxtFile getTxtFile(String path) {
    Directory parent;
    String filename = path;
    parent = DirectoryList.getCurrent();

    if (path.contains("/")) {
      filename = path.substring(path.lastIndexOf("/") + 1);
      path = path.substring(0, path.lastIndexOf("/"));
      parent = getDirectory(path);
    }

    for (File file : DirectoryList.getFilesOf(parent)) {
      if (file.getName().equals(filename)) {
        return (TxtFile) file;
      }
    }

    return null;

  }

  /**
   * Return true if path exists
   *
   * @param path
   * @return true for existing
   */
  public static Boolean pathExists(String path) {
    if (!path.contains("/")) {
      ArrayList<File> al = getFilesOf(current);
      for (File fl : al) {
        // System.out.println("fl name:" + fl.getName()+"pathname:"+ path);
        if (fl.getName().equals(path)) {
          return true;
        }
      }
      return false;
    }
    String[] directories = path.split("/");
    File file = getFile(path);

    if (file == null) {
      return false;
    } else {
      for (Directory dir : DirectoryList.getList()) {
        if (file.getParent() == dir) {
          return true;
        }
      }
      return false;
    }
  }

  /**
   * Get txtfiles and directories of parent
   *
   * @param parent
   * @return list of files
   */
  public static ArrayList<File> getFilesOf(Directory parent) {

    ArrayList<File> files = new ArrayList<>();
    for (Directory d : getList()) {
      Directory dParent = d.getParent();
      if (dParent == null) {
        continue;
      }

      if (d.getParent().getName().equals(parent.getName())) {
        files.add(d);
      }
    }

    for (TxtFile t : parent.getFilesindir()) {
      files.add(t);
    }

    return files;
  }

  /**
   * Return the list of directories inside parent directory
   * 
   * @param parent
   * @return
   */
  public static ArrayList<Directory> getDirsOf(Directory parent) {

    ArrayList<Directory> dirs = new ArrayList<>();
    for (Directory d : getList()) {
      Directory dParent = d.getParent();
      if (dParent == null) {
        continue;
      }

      if (dParent == parent) {
        dirs.add(d);
      }
    }
    if (dirs.size() == 0) {
      return null;
    }
    return dirs;
  }

  /**
   * Return the directory in current if this directory has same name with directoryName
   *
   * @param current
   * @param directoryName
   * @return (Directory) d or null
   */
  public static Directory findDirOn(Directory current, String directoryName) {
    for (File d : DirectoryList.getFilesOf(current)) {
      if (d.getName().equals(directoryName)) {
        return (Directory) d;
      }
    }
    return null;
  }

  /**
   * Remove the directory and the subdirectories
   * 
   * @param dir
   * @throws Exception
   */
  public static void removeDirectory(Directory dir) throws Exception {
    Stack<Directory> dirs = new Stack<>();
    dirs.add(dir);
    while (dirs.size() != 0) {
      Directory instantdir = dirs.pop();
      if (getDirsOf(instantdir) != null) {
        for (Directory d : getDirsOf(instantdir)) {
          dirs.add(d);
        }
      }
      getList().remove(instantdir);
    }
  }

  /**
   * Redirect content by given symbol to the stdout by the outfilepath
   * 
   * @param content
   * @param symbol
   * @param outfilepath
   * @throws Exception
   */
  public static void reDirect(String content, String symbol, String outfilepath) throws Exception {
    File f = null;
    if (!symbol.equals("")) {
      if(outfilepath.equals("")) {
        System.out.println(content);
        return;
      }
      else if (!pathExists(outfilepath)) {
        throw new Exception("No such path");
      }
      f = getFile(outfilepath);
      if (!(f instanceof TxtFile)) {
        throw new Exception("Wrong path");
      }
    }
    if (symbol.equals("")) {
      System.out.println(content);
    }
    if (symbol.equals(">")) {
      ((TxtFile) f).setContent(content);
    }
    if (symbol.equals(">>")) {
      ((TxtFile) f).setContent(((TxtFile) f).getContent() + content);
    }

  }

  /**
   * find the file with the given name
   * 
   * @param name of the file we want to find
   * @return true if we can find the file, false otherwise
   */
  public static boolean findFile(String nameOfFile) {
    ArrayList<Directory> a = DirectoryList.getList();
    for (int i = 0; i < DirectoryList.getList().size(); i++) {
      for (TxtFile file : a.get(i).getFilesindir()) {
        if (file.getName().equals(nameOfFile) || file.getPath().equals(nameOfFile)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * find the file with given name
   * @param nameOfFile
   * @return  file
   */
  public static TxtFile findExistingFile(String nameOfFile) {
    ArrayList<Directory> a = DirectoryList.getList();
    for (int i = 0; i < DirectoryList.getList().size(); i++) {
      for (TxtFile file : a.get(i).getFilesindir()) {
        if (file.getName().equals(nameOfFile) || file.getPath().equals(nameOfFile)) {
          return file;
        }
      }
    }
    return null;
  }

  /**
   * find the directory with the given path
   * 
   * @param name of the directory we want to find
   * @return true if we can find nameOfDir, otherwise false
   */
  public static boolean findDir(String pathOfDir) {
    ArrayList<Directory> a = DirectoryList.getList();
    for (int i = 0; i < DirectoryList.getList().size(); i++) {
      if (a.get(i).getName().equals(pathOfDir) || a.get(i).getPath().equals(pathOfDir)) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * find the directory with given name
   * @param pathOfDir
   * @return directory
   */
  public static Directory findExistingDir(String pathOfDir) {
    ArrayList<Directory> a = DirectoryList.getList();
    for (int i = 0; i < DirectoryList.getList().size(); i++) {
      if (a.get(i).getName().equals(pathOfDir) || a.get(i).getPath().equals(pathOfDir)) {
        return a.get(i);
      }
    }
    return null;
  }

  /**
   * Return the directory if find, null otherwise
   * 
   * @param directory
   * @return
   */
  public Directory findDirectory(Directory directory) {

    for (Directory dir : getList()) {
      if (dir == directory) {
        return dir;
      }
    }
    return null;
  }

  /**
   * reset the directorylist
   */
  public static void destroy() {
    self = null;
    list = new ArrayList<>();
    current = null;
    relativeCurrent = null;

  }
}
