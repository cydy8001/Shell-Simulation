package files;

import cmd.DirectoryList;

public class TxtFile extends File {

  private String content;

  public TxtFile(String name, Directory parent, String content) {
    super(name, parent);
    this.content = content;
  }

  public TxtFile(String name, String content) {
    this(name, DirectoryList.getCurrent(), content);
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getContent() {
    return this.content;
  }

  @Override
  public String toString() {
    return this.content;
  }
}
