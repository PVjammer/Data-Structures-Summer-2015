/**
 * Class to house the input & output methods used to read from
 * and write to a file. 
 */
import java.io.*; 
import java.util.*;

public class IOMethods {
/**
 * No Class Variables
 */
	public static int countInput(String filename) 
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
		
		try{
			BufferedReader input = new BufferedReader(new 
				FileReader(filename));
		
			while (input.readLine() != null)
				count++;
		}catch(IOException exception){
			System.out.println("Error");
			System.exit(1);
		}
		return count;
	}
	
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
	
	public static void writeData(String outFilename, String infile, int[] data) 
			throws IOException { 
	/**
	 * writeData creates a PrintWriter for the data structure to write its 
	 * output to
	 * 
	 * Param: 
	 * String outFilename - the name of the file to be written to
	 * int[] data - the array of integers (sorted) to be written to a file
	 * 
	 * Output:
	 * PrintWriter output - a PrintWriter to be used by the Data.writeData() method
	 * to write to a file
	 */
		try { 
			PrintWriter output = new PrintWriter(new 
					FileWriter(outFilename));
			output.println("Filename: " + infile);
			output.println();
			output.println("Sorted Data");
			for( int i = 0 ; i < data.length ; i++)
				output.println(data[i]);
			output.close();
			//return output;
		} // End Try
		catch(IOException exception) { 
			System.out.print("Error: " + exception); 
			System.exit(1);
			//return null;
		} // End Catch
	}
	public static int[] readInfo(String inFilename){
	/**
	 * 
	 */
		int n; 
		//String [] data;
		int[] nums = new int[10];
		int y;
		int count=0;
		String x;
		
		// file input encapsulated by try/catch statement
		try { 
			// Read file and create BufferedReader
			BufferedReader input = new BufferedReader(new 
					FileReader(inFilename));
			n = countInput(inFilename);
			nums = new int [n];
			x = input.readLine();
			//System.out.println("Starting");
			LinkedList numbers = new LinkedList();
			while(count<n){
				//System.out.println(x);
				StringTokenizer t = new StringTokenizer(x," ");
				while(true){
					try{
						y = Integer.parseInt(t.nextToken());
						numbers.addFile(y);
					}catch(NoSuchElementException e){
						break;
					}
				}
				//nums[count] = Integer.parseInt(x);
				
				count++;
				x = input.readLine();
			}
			nums = numbers.output();
			// Close input file
			input.close(); 
			
			// Return Data Structure 
			
			
		} catch (IOException exception) { 
			// Exit program and return null if there is an exception
			System.out.print("Error: " + exception); 
			System.exit(1); 
		
		}
		return nums;
	}
	
	public static String[] getFiles(final File folder) {
	/**
	 * http://stackoverflow.com/questions/1844688/read-all-files-in-a-folder
	 */
	    int count = 0;
	    String ext;
	    FileList files = new FileList();
	    String[] fileList;
		for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            getFiles(fileEntry);
	        } else {
	        	ext = getExt(fileEntry.getName());
	            if(!ext.equals(".dat")&&!ext.equals(".txt")){
	            	Util.println(fileEntry.getName() + " not imported. ");
	            	Util.println(ext + " files are not supported.");
	            }
	            else{
	            	//System.out.println(fileEntry.getName());
	            	files.addFile(fileEntry.getName());
	            }
	        }
	    }
		//System.out.println(files.isEmpty());
		return files.output();	
	}
	public static String getExt(String filename){
		String ext = "";
		for (int i = filename.length()-4;i<filename.length();i++)
			ext += filename.charAt(i);
		return ext;
	}
}
