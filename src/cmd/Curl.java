package cmd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import files.TxtFile;
/**
 * this class implements the command curl
 */
public class Curl<T> extends Command {
  /**
   * the main code of curl, download the file from the URL
   */
  public void execute() {
    StringBuffer file = new StringBuffer();
    try {
      URL url = new URL(parameters);
      URLConnection connection = url.openConnection();
      BufferedReader reader =
          new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String line = null;
      while ((line = reader.readLine()) != null)
        file.append(line + "\r\n");
      reader.close();
    } catch (Exception e) {
      System.out.println("Can't get the file");
    }

    String name = parameters.substring(parameters.lastIndexOf('/') + 1);
    name = DeleteInvalidChar(name);
    TxtFile txtfile = new TxtFile(name, file.toString());
    DirectoryList.getCurrent().addFile(txtfile);
  }
  
  /**
   * check whether the name of the file contains invalid chars
   * @param name, name of the file
   * @return the name of the file after deleting invalid chars 
   */
  private String DeleteInvalidChar(String name) {
    name = name.replace(".", "");
    name = name.replace("/", "");
    name = name.replace(" ", "");
    name = name.replace("!", "");
    name = name.replace("@", "");
    name = name.replace("#", "");
    name = name.replace("$", "");
    name = name.replace("%", "");
    name = name.replace("^", "");
    name = name.replace("&", "");
    name = name.replace("*", "");
    name = name.replace("(", "");
    name = name.replace(")", "");
    name = name.replace("{", "");
    name = name.replace("}", "");
    name = name.replace("~", "");
    name = name.replace("|", "");
    name = name.replace(">", "");
    name = name.replace("<", "");
    name = name.replace("?", "");
    return name;
  }
  public String toString() {
    return "Retrieve the file at that URL\r\n"
        + "and add it to the current working directory.";
  }
}
