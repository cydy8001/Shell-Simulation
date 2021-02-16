package files;

public class File {

  public String name;
  public Directory parent = null;

  public File(String name, Directory parent) {
    this.name = name;
    this.parent = parent;

  }

  /**
   * get name of file
   * 
   * @return string name
   */
  public String getName() {
    return this.name;
  }

  /**
   * set name of file
   * 
   * @param n as string
   */
  public void setName(String n) {
    this.name = n;
  }

  /**
   * get parent
   * 
   * @return parent as directory
   */
  public Directory getParent() {
    return this.parent;
  }

  /**
   * set parent
   * 
   * @param p directory
   */
  public void setParent(Directory p) {
    this.parent = p;
  }

  public String getPath() {
    return getParentPath() + "/" + name;
  }

  public String getParentPath() {
    if (parent == null || parent.getName().equals("")) {
      return "";
    }

    // recursive parentPath call
    return parent.getParentPath() + "/" + parent.getName();
  }
}
