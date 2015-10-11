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
	public static int countInput(BufferedReader input) 
			throws IOException{
		/*
		 * countInput method counts the number of strings in the
		 * language string.
		 * 
		 * Param: 
		 * BufferedReader input - the file which houses all of the 
		 * language strings
		 * 
		 * Output:
		 * int count - The number of strings in the file
		 */
		int count = 0;
		while (input.readLine() != null){
			count++;
		}
		return count;
	}
	
	public static void writeData(String[] result, PrintWriter output) 
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
		int index; 
		for (index=0; index<result.length; index++) { 
			if(result[index]!=null)
				output.println(result[index]);
			
		} 
		
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
	
	public static String[] readData(BufferedReader input, int size) 
			throws IOException { 
		/*
		 * readData method reads the language strings from the input 
		 * file and returns an array of strings.
		 * 
		 * Param: 
		 * BufferedReader input - the file which houses all of the 
		 * language strings
		 * int size - The number of strings in the input file
		 * 
		 * Output:
		 * String[] script - The array of language strings read in 
		 * from the file
		 */
		String script[] = new String[size];
		int index; 
		for (index=0; index<script.length; index++) { 
			script[index] = input.readLine(); 
		} 
			return script; 
	}
	
}
