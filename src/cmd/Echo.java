package cmd;

import java.util.ArrayList;
import files.TxtFile;
import files.Directory;

/**
 * This class implements the functions of command echo.
 */
public class Echo<T> extends Command {
  /**
   * The main code of echo. Append or override the file based on the input.
   */
  public void execute() {
    int count = 0;
    // Separate the arguments given by the user
    String inputstr = "";
    String symbol = "";
    String outfile = "";
    String path = "";
    int indicator = 0;
    if (parameters != null) {
      try {
        inputstr = parameters.substring(parameters.indexOf('"') + 1, parameters.lastIndexOf('"'));
      } catch (Exception e){
        System.out.println("Illegal string.");
      }
  	  for (String para : parameters.substring(parameters.lastIndexOf('"') + 1)
  			  .split(" ")) {
        if (!para.equals("")) {
          if (symbol.equals("")) {
            symbol = para;
          } else if(path.equals("")){
            path = para;
          }
            count++;
        }
      }
  	  outfile = getOutfileName(path);
      for (int i = 1; i < (inputstr.length() - 1); i++) {
        if (inputstr.charAt(i) == '"') {
          indicator++;
        }
      }
      // identify illegal arguments
      if (count > 2 || count == 1) {
        System.out.println("Illegal arguments.");
      } else if (indicator > 1) {
        System.out.println("Illegal string.");
      }
      // identify the instruction given by user
      else if (symbol.equals(">>")) {
    	  append(path, outfile, inputstr);
      } else if (symbol.equals(">")) {
    	  overwrite(path, outfile, inputstr);
      } else {
        System.out.println(inputstr);
      }
    } 
  }
  
  private String getOutfileName(String path) {
    int lastIdxOfSlash = path.lastIndexOf('/');
    String name = "";
    if (lastIdxOfSlash != -1) {
      name = path.substring(lastIdxOfSlash + 1);
    } else {
      name = path;
    }
    return name;
  }
  /**
   * append the input string to outfile, create a new file if the outfile
   * does not exist
   * @param path
   * @param inputstr
   */
  private void append(String path, String outfile, String inputstr)
  {
    if(DirectoryList.getTxtFile(path)!=null)
    {
      DirectoryList.getTxtFile(path).setContent(
          DirectoryList.getTxtFile(path).getContent() + inputstr);
    }
    else
    {
      int lastIdxOfSlash = path.lastIndexOf('/');
      if (lastIdxOfSlash != -1) {
        String parentDir = path.substring(0, lastIdxOfSlash);
        TxtFile newfile = new TxtFile(outfile, DirectoryList.getDirectory(parentDir), 
            inputstr);
        DirectoryList.getDirectory(parentDir).addFile(newfile);
      }
      else {
        TxtFile newfile = new TxtFile(outfile, inputstr);
        DirectoryList.getCurrent().addFile(newfile);
      }
    }
  }
  
  /**
   * overwrite outfile to input string, create a new file if the outfile
   * does not exist
   * @param path
   * @param inputstr
   */
  private void overwrite(String path, String outfile, String inputstr)
  {
    if(DirectoryList.getTxtFile(path)!=null)
    {
      DirectoryList.getTxtFile(path).setContent(inputstr);
    }
    else
    {
      int lastIdxOfSlash = path.lastIndexOf('/');
      if (lastIdxOfSlash != -1) {
        String parentDir = path.substring(0, lastIdxOfSlash);
        TxtFile newfile = new TxtFile(outfile, DirectoryList.getDirectory(parentDir), 
            inputstr);
        DirectoryList.getDirectory(parentDir).addFile(newfile);
      }
      else {
        TxtFile newfile = new TxtFile(outfile, inputstr);
        DirectoryList.getCurrent().addFile(newfile);
      }
    }
  }
  
  
  /**
   * Manual call toString method to return the doc of Echo.
   * 
   * @return documentation of Echo.
   */
  public String toString() {

    return "echo STRING [> OUTFILE]\n" + "If OUTFILE is not provided, print STRING on the shell.\n"
        + "Otherwise, put STRING into file OUTFILE. STRING \n"
        + "is a string of characters surrounded by double \n"
        + "quotation marks. This creates a new file if OUTFILE\n"
        + "does not exists and erases the old contents if OUTFILE\n"
        + "already exists. In either case, the only   thing in \n" + "OUTFILE should be STRING.\n"
        + "\n" + "echo STRING [>> OUTFILE]\n"
        + "Like the previous command, but appends instead of\n" + "overwrites.";
  }
}
