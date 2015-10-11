/**CmdLine
 * ________
 * @author Nicholas C. Burnett
 * Version: 1.00
 * Date: 7/14/2015
 * 
 * This class houses the methods to be used to parse command line input and return the filenames
 * and boolean flags for debug mode and output echoing 
 *
 */
public class CmdLine {
	private static int numFiles = 0;			// The number of input files read in by the program
	private static boolean isDebug = false;		// Is the program being run in debug mode?
	private static String[] inFilenames;		// Array of filename strings
	private static String outFilename;			// String to hold the output file name
	private static boolean cmdEcho = false;		// Should the output be echoed on the command line? 
	
	public static void parseCmdLine (String[] args){
		/**
		 * parseCmdLine reads the String[] args and parses it based on the use of the 
		 * operators -i, -o, -e, & -d.  Updates class variables as a result.
		 * 
		 * Param: String[] args - An array of strings read in from the command line
		 * 
		 * Output:
		 * None - class variables are updated via this method 
		 */
		inFilenames = new String[10];
		int count = 0;
		String substr;
		for (int k =0;k<args.length;k++){
			/*Loop for reading in command line arguments.  Valid command line arguments are:
			 * "-d" : Runs the program in debug mode.  Overrides all other commands.
			 * "-i" : All subsequent strings are read as input filenames until a different
			 * command is encountered or there are no more arguments
			 * "-o" : The following string is read as an output filename
			 * "-e" - Echos the output to the command line as well
			 * Strings : Any string is valid if it follows a "-o" or a "-i"
			 */
				// If "-i" is read then the next strings are filenames
				if (args[k].equals("-i")){
					k++;
					count = 0;
					// Read in the following arguments as filenames until there are no more arguments
					// or "-o" or "-d" are encountered
					while (!args[k].equals("-o")&& !args[k].equals("-d") && k< args.length){
						// Increase the size of the array if the array is full
						if(count >= inFilenames.length)
							inFilenames = expandArray(inFilenames);
						substr = "";
						for(int jj = args[k].length()-4;jj<args[k].length();jj++)
							substr += args[k].charAt(jj);
						
						// Error checking to ensure that only .txt files are processed
						if(!substr.equals(".txt")){
							System.out.println("Invalid file type"
									+ " only .txt files are accepted!");
						}
						else if (args[k]=="-e")
							break;
						else{
						// Add filename to array
						inFilenames[count] = args[k];
						count++;
						}
						k++;		//Increment loop counter to move to next argument
						if(k>=args.length)
							break;
					} // End While Loop (inFilenames)
					count = 0;
					// Exit loop if there are no more args[]
					if(k>=args.length)
						break;
				} // End If Statement (inFilename)
				
				// Read string as the output filename if "-o" is encountered
				if (args[k].equals("-o")){
					k++;
					outFilename = args[k];
					
				} // End Else If Statement (outFilename)
				
				if(args[k].equals("-e")){
					cmdEcho=true;
					System.out.println("Echoing output to Command Line!");
					//break;
				} //End If Statement "Debug Mode"
				
				if(args[k].equals("-d")){
					isDebug=true;
					System.out.println("Debug Mode!");
					break;
				} //End If Statement "Debug Mode"
				
			} // End For Loop
	}
	
	public static int getNumFiles(){
		/**
		 * getNumFilesI() returns the number of valid input files to be read from 
		 * 
		 * Param: None
		 * 
		 * Output:
		 * numFiles - the number of valid input files
		 */
		return numFiles;
	}
	
	public static boolean isDebug(){
		/**
		 * isDebug() returns a boolean indicating whether the program is to
		 * be run in debug mode or not 
		 * 
		 * Param: None
		 * 
		 * Output:
		 * isDebug - boolean indicating whether the program should be run in debug mode
		 */
		return isDebug;
	}
	
	public static String[] inFiles(){
		/**
		 * inFiles() returns an array of input filename strings to be used by the 
		 * main program
		 * 
		 * Param: None
		 * 
		 * Output:
		 * inFilenames - an array of input filename strings
		 */
		return inFilenames;
	}
	
	public static String outFile(){
		/**
		 * outfile() returns an output filename string to be used by the 
		 * main program
		 * 
		 * Param: None
		 * 
		 * Output:
		 * outFileName - The output filename (string)
		 */return outFilename;
	}
	
	public static boolean isEcho(){
		return cmdEcho;
	}
	
	private static String[] expandArray(String[] s){
		/* A method used for expanding the size of an array
		 * once it has reached capacity.  Currently used to expand
		 * the input filename array so that more input files can be
		 * processed. 
		 * 
		 * Param:
		 * String[] s - The array of strings to be expanded
		 * 
		 * Output:
		 * String s2 - And array of double the size of s.  The first 
		 * s.length entries of s2 are identical to the entries of s. 
		 */
		String[]s2 = new String[s.length*2];
		for(int i = 0;i<s.length;i++)
			s2[i] = s[i];
		return s2;
	}
}
