/*
 * Class to house the input & output methods used to read from
 * and write to a file. 
 * 
 * (This class was intended to perform all of the I/O operations
 * however the implementation of the main program required a portion
 * of the I/O functions to be performed in the main program. 
 */
import java.io.*; 
import java.util.*;

public class IOMethods {
	/*
	 * No class variables
	 */
	public static String parseCmdLine (String args){
		return null;
	}
	
	public static PrintWriter writeData(String outFilename) 
			throws IOException { 
		/*
		 * writeData method writes the formatted language strings/output
		 * to the output file
		 * 
		 * Param: 
		 * String[] result - An array of formatted language strings with 
		 * results
		 * PrintWriter output - The output file to be written to
		 * 
		 * Output:
		 * The strings in "result" are written to the output file
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
	
	public static void writeHeader(String result, PrintWriter output) 
			throws IOException {
		/*
		 * writeHeader method writes the formatted column headers
		 * to the output file
		 * 
		 * Param: 
		 * String result - An array of formatted column headers
		 * PrintWriter output - The output file to be written to
		 * 
		 * Output:
		 * The string "result" is written to the output file
		 */
		output.println(result);
	}
	
	public static Data readInfo(String inFilename){
		Data Matricies = new Data();
		
		try { 
			BufferedReader input = new BufferedReader(new 
					FileReader(inFilename));
					 
			
			Matricies.readData(input);
			
			input.close(); 
			return Matricies;
		} catch (IOException exception) { 
		System.out.print("Error: " + exception); 
		System.exit(1); 
		return Matricies;
		}
	}
}
