/**Lab1Driver
 * ________
 * Author: Nicholas C. Burnett
 * Version: 1.00
 * Date: 6/30/2015
 * 
 * This program reads one or more text files of strings and determines which
 * language each string belongs to through utilization of a custom built stack.
 * 
 * Key Features:
 *  - Can accept multiple input files
 *  - Dynamically expanding stacks (allowing for the processing of excessively
 *  long string
 *  - Robust error checking
 *  - Six total languages (see the "Translate" class for an explanation of each
 *  - Modular design (more languages can be added easily)
 *
 * 
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Lab1Driver {
	
	static boolean isDebug = false;

	public static void main(String[] args) {
		
		final String DEFAULT_INPUT = 
				"NBLab1_input.txt";					// Default input file for debug mode
		final String DEFAULT_OUTPUT = 
				"NBoutput.txt";						// Default output file for debug mode
		final int MAX_STRINGS = 50;					// The maximum number of strings in a file
		final int numLang = Translate.getnumLang();	// The number of languages checked against
		String inFilename[] = new String[10];		// Initial size of the input filename array
		String outFilename ="output.txt";			// Initial size of the output filename array
		String langStr;								// String to hold the Uppercase version of input
		int arraySize=MAX_STRINGS;					// Size of the array of language strings									
		int count;									// Counter variable used in parsing of args[]
		String[] languages = null;					// String array of language strings
		int[] result;								// Int array of processing results (1's & 0's)
		String[][] final_output = null;				// 2-Dimensional array to hold formatted output  
		String substr ="";
		
		/* The Driver only expects and handles one input argument.
		 * The argument can be a filename or "-d" which will run the
		 * program in debug mode with hardcoded filenames.
		 */
		if(args.length == 0){
			System.out.println("No parameters detected...Aborting Program.");
			System.exit(1);
		} // End If (No parameters)
		
		for (int k =0;k<args.length;k++){
		/*Loop for reading in command line arguments.  Valid command line arguments are:
		 * 
		 * "-d" : Runs the program in debug mode.  Overrides all other commands.
		 * 
		 * "-i" : All subsequent strings are read as input filenames until a different
		 * command is encountered or there are no more arguments
		 * 
		 * "-o" : The following string is read as an output filename
		 * 
		 * Strings : Any string is valid if it follows a "-o" or a "-i"
		 * 	
		 */
			// If "-i" is read then the next strings are filenames
			if (args[k].equals("-i")){
				k++;
				count = 0;
				// Read in the following arguments as filenames until there are no more arguments
				// or "-o" or "-d" are encountered
				while (!args[k].equals("-o")&& !args[k].equals("-d") && k< args.length){
					// Increase the size of the array if the array is full
					if(count >= inFilename.length)
						inFilename = expandArray(inFilename);
					substr = "";
					for(int jj = args[k].length()-4;jj<args[k].length();jj++)
						substr += args[k].charAt(jj);
					
					// Error checking to ensure that only .txt files are processed
					if(!substr.equals(".txt")){
						System.out.println("Invalid file type"
								+ " only .txt files are accepted!");
					}
					else{
					// Add filename to array
					inFilename[count] = args[k];
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
			
			if(args[k].equals("-d")){
				isDebug=true;
				System.out.println("Debug Mode!");
				break;
			} //End If Statement "Debug Mode"
			
		} // End For Loop
		if (isDebug==true){
			inFilename = new String[1];
			inFilename[0] = DEFAULT_INPUT;
			outFilename = DEFAULT_OUTPUT;
		} // End If
		
		/* Begin Meat & Potatoes
		 * 
		 * 
		 */
			
	    final_output = new String[inFilename.length][MAX_STRINGS];	
		for (int ii = 0; ii<inFilename.length;ii++){
			//System.out.println(inFilename[ii]);
			
			// Skip all iterations of the loop for which there is no filename
			if(inFilename[ii] == null)
				break;
			
			/*
			 * The code block below is used to determine the number of strings in the file
			 * and hence the size of the array that will be used to store them.  This was
			 * done so that the program could accommodate files of any length.  After the 
			 * incorporation of multi-file reading, this became largely obsolete given the
			 * need for a two dimensional output array of predetermined dimensions.  The
			 * variable arraySize is still used to determine if the output will be truncated
			 * by the program and allow the program to alert the user to this. 		   
			 */
			
			try { 
				BufferedReader input = new BufferedReader(new 
						FileReader(inFilename[ii]));	 
				 arraySize = IOMethods.countInput(input);
				 input.close(); 
			} catch (IOException exception) { 
			System.out.print("Error: " + exception); 
			System.exit(1); 
			} // End Try/Catch
			
			languages = new String[arraySize];
			//outputString = new String[arraySize];
			
			/* The input file is read into the "languages" array line by line, up
			 * to the constant "MAX_STRINGS."  Any additional strings will be discarded
			 * by the program.  Sorry :(  
			 * 
			 * The try/catch checks to ensure that the file is valid.
			 */ 
			try { 
				BufferedReader input = new BufferedReader(new 
						FileReader(inFilename[ii]));
				languages = IOMethods.readData(input, MAX_STRINGS);		 
				
				/* Only "MAX_STRING" strings can be processed from a single file.
				 * Provide a message to the user if the user if not all strings 
				 * are able to be processed so that they can correctly interpret the 
				 * output.
				 */
				if(arraySize > MAX_STRINGS){
					System.out.println("Some strings were not procesed..."
							+ "only "+ MAX_STRINGS + " strings can be processed "
							+ "per file");
				} // End If (Truncate Languages Array)
				input.close(); 
			} catch (IOException exception) { 
			System.out.print("Error: " + exception); 
			System.exit(1); 
			} // End Try/Catch
			
			/*
			 * Loop to process the strings contained within each file.  The strings within
			 * the "languages" array are first capitalized, then they are processed by the
			 * various methods of the "Tranlate" class (accessed via the public "calcLang"
			 * method.  The outpout of calcLang is a series of 1's and 0's, which is then 
			 * processed by the function "formatResult," which switches the 1's and 0's for
			 * Yes's and No's, while also formatting the results into columns and adding the
			 * initial strings.  The output of "formatResult" is stored in a 2-dimensional
			 * array "final_output" which holds an array of formatted strings for each file. 
			 */	
			for(int i = 0;i<languages.length;i++){	
				if(languages[i] != null){
					langStr = allCaps(languages[i]);
					result = Translate.calcLang(langStr,numLang);
					final_output[ii][i]= formatResult(result,languages[i],numLang);
				} // End If
				
			} // End For
			
		} // End For Loop
		
		
			
		/*  
		 * A header is created using the "formatHeader" function and then written to the
		 * output file.  Once the header has been written to the file each set of output
		 * strings is written to that same file.  The result is one combined output file for
		 * containing the results of all the strings (unless truncated) from all the input
		 * files.		 * 
		 */
		if (inFilename[0]!= null){
			//File output is encapsulated in try statement.
			try { 
				PrintWriter output = new PrintWriter(new 
						FileWriter(outFilename));
				// Add column headers to output file
				String header = formatHeader(numLang);
				IOMethods.writeHeader(header, output);
				// Write data from all input files (with results) to output file
				for(int h = 0; h<inFilename.length;h++)
					IOMethods.writeData(final_output[h], output);
				output.close(); 
			} // End Try
			catch(IOException exception) { 
				System.out.print("Error: " + exception); 
				System.exit(1); 
			} // End Catch
			System.out.println("Program Execution Completed");
		}
		else
			System.out.println("No strings were processed.  Program terminating.");
		// This signifies to the user that the program has finished and ensures that
		// there were no infinite loops
		
	} // End Main method
	
	/*
	 * Main methods below are used to format and process variables used by the main method 
	 */
	
	public static void printResult(int[] result, String s,int num){
		/* A method used for debugging to print the results of the translation
		 * to the console.
		 * 
		 * Param:
		 * int [] result - the array of integers representing the results of a 
		 * language string's translation
		 * String s - The initial language string that was translated
		 * int num - The number of language strings in a particular file
		 */
		String output = "";
		System.out.print(s);
		System.out.print("\t\t\t");
		for(int i=0;i<num; i++){
			System.out.print(" ");
			switch (result[i]){
				case 0: output = "No";
						break;
				case 1: output = "Yes";
						break;
			}
			System.out.print(output);
				
		}
		
		System.out.println("");
	}
	
	public static String formatResult(int[] result, String s,int num){
		/* A method used for formatting the results prior to being written to 
		 * a file.
		 * 
		 * Param:
		 * int [] result - the array of integers representing the results of a 
		 * language string's translation
		 * String s - The initial language string that was translated
		 * int num - The number of language strings in a particular file
		 * 
		 * Output:
		 * formatted - A string which consists of the initial language string,
		 * the results for each language, and separating tabs.
		 */
		String formatted = "";
		String output = "";
		formatted += s;
		// Strings longer than 7 characters have on less tab between them
		// and the first result.  It helps to line up results.
		if(s.length()>7)
			formatted += "\t\t";
		else
			formatted += "\t\t\t";
		// Swap out 0's and 1's for Yes's and No's
		for(int i=0;i<num; i++){
			switch (result[i]){
				case 0: output = "No";
						break;
				case 1: output = "Yes";
						break;
			}		
		formatted += output;
		formatted+="\t";
		}
		
		return formatted;
	}
	
	private static String formatHeader(int num){
		/* A method used for creating column headers for the top of 
		 * the output file. The headers are:
		 * "Language String" "L1" "L2" "L3" "L4" "L5" "L6"
		 *  
		 * Param:
		 * int num - The number of languages translated by the program
		 * 
		 * Output:
		 * String output - A string which consists of the column headers
		 * separated by tabs.
		 */
		String output = "";
		output+="Language String";
		output +="\t\t";
		for (int i=1;i<=num;i++)
			output+="L"+i+"\t";
		return output;
	}
	
	private static String allCaps(String s){
		/* A method used for converting a language string to all
		 * Uppercase letters.
		 * 
		 * Param:
		 * String s - The language string to be converted
		 * 
		 * Output:
		 * String caps - The language string converted entirely to
		 * Uppercase letters.
		 */
		String caps = "";
		caps = s.toUpperCase();
		return caps;
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
	
} // End Lab1Driver Class


