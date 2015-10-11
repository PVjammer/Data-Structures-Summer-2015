import java.io.IOException;

/**Lab2Driver
 * ________
 * Author: Nicholas C. Burnett
 * Version: 1.00
 * Date: 7/14/2015
 * 
 * This program reads one or more text files of matrices, where each matrix is 
 * preceded by an integer representing the matrix's dimension.
 * 
 * Key Features:
 *  - Can accept multiple input files
 *  - Dynamically expanding stacks (allowing for the processing of excessively
 *  long string
 *  - Robust error checking
 *  - Six total languages (see the "Translate" class for an explanation of each
 *  - Modular design (more languages can be added easily)
 * 
 */
public class Lab2Driver {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Variables
		final String DEFAULT_INPUT = 
				"NBLab2Input.txt";					// Default input file for debug mode
		final String DEFAULT_OUTPUT = 
				"NBLab2Output.txt";					// Default output file for debug mode
		String inFilename[];		// Initial size of the input filename array
		String outFilename ="output.txt";			// Initial size of the output filename array
		Data problem = new Data();					// Initialize Data Structure to hold matrices
		int file;									// Index for moving through filename array
		int count;									// Counter variable used in parsing of args[]
		boolean echo = false;						// Echo output to command line
		boolean isDebug = false;					// Run program in debug mode?
		
		Data master;
		
		/*
		 * The program can receive a number of parameters.  
		 *  "-d" - Runs the program in debug mode, using default input and output filenames
		 *  "-i" - Operator that designates the following strings as input files.
		 *  "-o" - Operator that designates the following string as an output file.
		 *  "-e" - Echos the output to the command line as well
		 */
		if(args.length == 0){
			System.out.println("No parameters detected...Aborting Program.");
			System.exit(1);
		} // End If (No parameters)
		
		// Parse command line input
		CmdLine.parseCmdLine(args);
		isDebug = CmdLine.isDebug();
		echo = CmdLine.isEcho();
		
		// Run in debug mode?
		//System.out.println(isDebug);
		if (isDebug==true){
			inFilename = new String[1];
			inFilename[0] = DEFAULT_INPUT;
			outFilename = DEFAULT_OUTPUT;
		} // End If
		else{
			// Save file names
			inFilename = new String[CmdLine.getNumFiles()];
			inFilename = CmdLine.inFiles();
			outFilename = CmdLine.outFile();
		}
		file = 0;
		master = new Data(inFilename.length);
		// Read input from files
		while(inFilename[file]!=null){
			// Call to IOMethods which creates a Buffered Reader and returns a data structure of matrices
			problem = IOMethods.readInfo(inFilename[file]);
			count = 0;
			
			// Combine various input files into one data structure (type Data) of matrices
			while(!problem.isEmpty(count)){
				master.addMatrix(problem.getMatrix(count).dim(), problem.getMatrix(count).getValues());
				count++;
			}
			file++;
			if(file==inFilename.length)
				break;
		}	
		
		// If "-e" is entered as an argument, echo all output to the command line
		if (echo){
			master.printAll();
		}
			
		// Out put is enclosed in try/catch statement
		try {
			// Call to the Data classes write-to-file method, which calls IOMethods to create a printWriter
			master.writeData(outFilename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Output to show that the program has completed running successfully
		System.out.println("Program Execution Complete");
	}
}
