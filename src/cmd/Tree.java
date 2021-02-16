package cmd;

import files.Directory;
import files.File;
/**
 * this class implements command Tree
 */
public class Tree extends Command{
    /**
     * main code of tree, prints all directories recursively starting from root.
     */
	public void execute(){
		if(parameters != null) {
			System.out.println("There should be no input");
			return;
		}
		Directory dir = DirectoryList.getRoot();
		System.out.println("\\");
		printrec(dir, 1);
	}

    /**
     * Prints recursively of all files starting at directory dir, 
     * with spaces of number length in front. Length increases by 1
     * for every level of directory
     */
	private void printrec(Directory dir, int depth){
	    for (File file : DirectoryList.getFilesOf(dir)) { 
	        printwithspace(file.getName(), depth);	        
	        if(file instanceof Directory){
	        	Directory d = DirectoryList.findExistingDir(file.getName());
	        	printrec(d, depth+1);
	        }
	    }  
	} 
    /**
     * Print the name with spaces of length n in front.
     */
	private void printwithspace(String name, int length) {
    	for(int i = 0; i < length; i++) {
    		System.out.print("	");
    	}
		System.out.println(name);
	}
	
	public String toString() {
		return "tree\n" + "From the root directory ('\\') display the entire filesystem\n"
				+ "as a tree. For every level of the tree, indent by a tab character";
	}	
}//Class Tree
