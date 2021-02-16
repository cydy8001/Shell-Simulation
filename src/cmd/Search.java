package cmd;

import java.util.ArrayList;
import java.util.Stack;

import files.Directory;

/**
 * this class implements command search
 */
public class Search extends Command{
    /**
     * main code of search, search the file with given paths
     */
	public void execute(){
		ArrayList<String> paths = new ArrayList<String>();
		boolean searchDir = searchForDir(parameters);
		String name = getNameToSearch(parameters);
		paths = getArgPath(parameters);
		Stack<Directory> dirs = new Stack<>();
		for(String dir : paths)
		{
			
			dirs.add(DirectoryList.getDirectory(dir));

		}
	    while (dirs.size() != 0) {
	      Directory instantdir = dirs.pop();
	      if (DirectoryList.getDirsOf(instantdir) != null) {
	        for (Directory d : DirectoryList.getDirsOf(instantdir)) {
	          dirs.add(d);
	        }
	      }
	      if(searchDir && (instantdir.getPath().equals(name) || instantdir.getName().equals(name)))
          {
        	  System.out.println(instantdir.getPath());
          }
	      else
	      {
	    	  if(instantdir.findFile(name) != null)
	    	  {
	    		  System.out.println(instantdir.findFile(name).getPath());
	    	  }
	      }
	    }
		
	}
	
	/**
	 * extract the paths from the input string
	 * @param parameter, the user input
	 * @return an arraylist that contains all the paths from input  
	 */
	private ArrayList<String> getArgPath(String parameter)
	{
		ArrayList<String> paths = new ArrayList<String>();
		for (String para : parameters.split(" ")){
			if(para.charAt(0) == '-')
	        {
	        	break;
	        }
			if(!para.equals(""))
	        {
	        	paths.add(para);
	        }
		}
		return paths;
	}
	
	/**
	 * check directory or file is searched
	 * @param parameter, user input
	 * @return true if directory is searched, false if file is searched
	 */
	private boolean searchForDir(String parameter)
	{
		int indicator = 0;
		for (String para : parameters.split(" ")){
	        if(para.charAt(0) == '-')
	        {
	        	indicator = 1;
	        }
	        else if(indicator == 1)
	        {
	        	if(para.equals("d"))
	        	{
	        		return true;
	        	}
	        }
		}
		return false;
	}
	
	/**
	 * get the name of the file or directory we want to search for
	 * @param parameter, user input
	 * @return the name of the file or directory
	 */
	private String getNameToSearch(String parameter)
	{
		for(int i = parameters.split(" ").length - 1; i > -1; i--)
		{
			if(parameters.split(" ")[i] != null)
			{
				return parameters.split(" ")[i];
			}
		}
		return null;
	}
	public String toString() {
	  return " search path ... -type [f|d] -name expression.";
	}
}
