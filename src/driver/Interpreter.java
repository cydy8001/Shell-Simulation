package driver;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import cmd.Command;
import exception.EmptyCommandException;
import exception.FalseCommandException;

/**
 * This class interprets the input from user.
 */
public class Interpreter {
  // store the command extracted
  private Command cmd;

  /**
   * Call extractCommand() and extractParameters() to get command and parameters
   * 
   * @param input from user
   * @throws InstantiationException
   * @throws ClassNotFoundException
   * @throws IllegalAccessException
   * @throws EmptyCommandException
   * @throws FalseCommandException
   * @throws IllegalArgumentException
   * @throws InvocationTargetException
   * @throws NoSuchMethodException
   * @throws SecurityException
   */
  public Interpreter(String input)
      throws InstantiationException, ClassNotFoundException, IllegalAccessException,
      EmptyCommandException, FalseCommandException, IllegalArgumentException,
      InvocationTargetException, NoSuchMethodException, SecurityException {

    String command = Interpreter.extractCommand(input);
    String parameters = Interpreter.extractParameters(command, input);

    this.cmd = this.getCommand(command);
    this.cmd.setParameters(parameters);

  }

  /**
   * Extract the command.
   * 
   * @param command, the input of user
   * @return the command we want
   */
  public static String extractCommand(String command) {
    int spaceIndex = command.indexOf(" ");

    if (spaceIndex > -1) {
      command = command.substring(0, command.indexOf(" ")).trim();
    }

    return command;
  }

  /**
   * Extract the arguments from input.
   * 
   * @param command
   * @param input from user
   * @return arguments of the command
   */
  public static String extractParameters(String command, String input) {
    int afterCommand = command.length();
    String parameters = input.substring(afterCommand).trim();

    if (!parameters.equals("")) {
      return parameters;
    }

    return null;
  }

  /**
   * Get the command.
   * 
   * @return Command
   */
  public Command getCmd() {
    return cmd;
  }

  /**
   * Give the command to the Command cmd inside interpreter.
   * 
   * @param command
   * @return Command
   * @throws EmptyCommandException
   * @throws FalseCommandException
   * @throws ClassNotFoundException
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws InvocationTargetException
   * @throws NoSuchMethodException
   * @throws SecurityException
   */
  private Command getCommand(String command)
      throws EmptyCommandException, FalseCommandException, ClassNotFoundException,
      InstantiationException, IllegalAccessException, IllegalArgumentException,
      InvocationTargetException, NoSuchMethodException, SecurityException {

    if (command.equals("")) {
      throw new EmptyCommandException();
    }

    if (!Interpreter.verify(command)) {
      throw new FalseCommandException();
    }
    command = nametoClass(command);
    Class c = Class.forName("cmd." + command);
    return (Command) c.getDeclaredConstructor().newInstance();
  }

  /**
   * Find the Class corresponding to the input command.
   * 
   * @param command
   * @return the name of the class
   */
  public static String nametoClass(String command) {
    if (command.equals("exit")) {
    	return "Exit";
    }
    if (command.equals("mkdir")) {
    	return "MakeDirectory";
    }
    if (command.equals("cd")) {
    	return "ChangeDirectory";
    }
    if (command.equals("ls")) {
    	return "List";
    }
    if (command.equals("pwd")) {
    	return "PrintWorkingDirectory";
    }
    if (command.equals("pushd")) {
    	return "Pushd";
    }
    if (command.equals("popd")) {
    	return "Popd";
    }
    if (command.equals("history")) {
    	return "History";
    }
    if (command.equals("cat")) {
    	return "Concatenate";
    }
    if (command.equals("echo")) {
    	return "Echo";
    }
    if (command.equals("man")) {
    	return "Manual";
    }
    if (command.equals("rm")) {
    	return "Remove";
    }
    if (command.equals("mv")) {
    	return "Move";
    }
    if (command.equals("curl")) {
    	return "Curl";
    }
    if (command.equals("cp")) {
    	return "Copy";
    }
    if (command.equals("search")) {
        return "Search";
    }
    if (command.equals("tree")) {
        return "Tree";
    }
    if (command.equals("saveJShell")) {
    	return "SaveJShell";
    }
    if (command.equals("loadJShell")) {
      return "LoadJShell";
  }
    return null;
  }

  /**
   * To see if the input is one of the command we have.
   * 
   * @param commmand
   * @return boolean
   */
  public static boolean verify(String commmand) {
    ArrayList<String> commands = new ArrayList<>();

    commands.add("exit");
    commands.add("mkdir");
    commands.add("cd");
    commands.add("ls");
    commands.add("pwd");
    commands.add("pushd");
    commands.add("popd");
    commands.add("history");
    commands.add("cat");
    commands.add("echo");
    commands.add("man");
    commands.add("rm");
    commands.add("mv");
    commands.add("curl");
    commands.add("cp");
    commands.add("search");
    commands.add("tree");
    commands.add("saveJShell");
    commands.add("loadJShell");
    return commands.contains(commmand);
  }
}
