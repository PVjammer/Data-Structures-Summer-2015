/**
 * Class to house the input & output methods used to read from
 * and write to a file. 
 * 
 * (This class was intended to perform all of the I/O operations
 * however the implementation of the main program required a portion
 * of the I/O functions to be performed in the main program. 
 */
import java.io.*; 

public class IOMethods {
	/**
	 * No Class Variables
	 */
	public static PrintWriter writeData(String outFilename) 
			throws IOException { 
		/**
		 * writeData creates a PrintWriter for the data structure to write its 
		 * output to
		 * 
		 * Param: 
		 * String outFilename - the name of the file to be written to
		 * 
		 * Output:
		 * PrintWriter output - a PrintWriter to be used by the Data.writeData() method
		 * to write to a file
		 */
		try { 
			PrintWriter output = new PrintWriter(new 
					FileWriter(outFilename));
			//output.close();
			return output;
		} // End Try
		catch(IOException exception) { 
			System.out.print("Error: " + exception); 
			System.exit(1);
			return null;
		} // End Catch
		
	}
	
	public static Data readInfo(String inFilename){
		/**
		 * ReadData method reads the filename, creates a buffered reader, and 
		 * returns a data structure of matrices.
		 * 
		 * Param: 
		 * String inFilename - the name of the input file (.txt)
		 * 
		 * Output:
		 * Data Matrices - A data structure of matrices read in from the file
		 */
		Data Matrices = new Data();
		
		// file input encapsulated by try/catch statement
		try { 
			// Read file and create BufferedReader
			BufferedReader input = new BufferedReader(new 
					FileReader(inFilename));
					 
			// Create data structure from input file
			Matrices.readData(input);
			
			// Close input file
			input.close(); 
			
			// Return Data Structure 
			return Matrices;
			
		} catch (IOException exception) { 
			// Exit program and return null if there is an exception
			System.out.print("Error: " + exception); 
			System.exit(1); 
			return Matrices;
		}
	}
}
