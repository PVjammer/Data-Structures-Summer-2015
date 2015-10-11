/**Data
 * ________
 * @author Nicholas C. Burnett
 * Version: 1.00
 * Date: 7/14/2015
 * 
 *  A data structure used to house an unspecified number of matrices and 
 *  coordinate the formatting of the input and output
 *
 */
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Data {
/**
 * Class to hold the matricies and determinants for the purposes of passing them back and forth between methods
 */
	private static int DEFAULT_SIZE = 10;	// Default size of the array of matrices
	private Matrix[] items;					// Array of matrices
	private int size;						// Size of the array of matrices
	private int top = -1;					// Current position within the array
	
	public Data(){
		/**
		 * Default constructor
		 * Makes an array of matrices of size (DEFAULT_SIZE)
		 */
		size = DEFAULT_SIZE;
		items = new Matrix[size];
	}
	
	public Data(int n){
		/**
		 * Constructor used to make a data structure of size n.
		 * Creates an array of n matrices
		 */
		size = n;
		items = new Matrix[size];
	}
	
	public void addMatrix(int n){
		/**
		 * addMatrix() creates a new matrix of dimension n with all zeros
		 * 
		 * Param: 
		 * int n - the dimension of the matrix to be created
		 * 
		 * Output:
		 * None - matrix of zeros created
		 */
		top++;
		items[top] = new Matrix(n);
	}
	
	public void addMatrix(int n, int[][]values){
		/**
		 * addMatrix() creates a new matrix of dimension n with the 
		 * values specified in "values[][]"
		 * 
		 * Param: 
		 * int n - the dimension of the matrix to be created
		 * int[][] values - a 2D array of integers to be used to create
		 * the matrix
		 * 
		 * Output:
		 * None - matrix created
		 */
		top++;
		if(top == items.length-1)
			expandArray();
		items[top] = new Matrix(n);
		items[top].setValues(values);
	}
	
	public Matrix getMatrix(int n){
		/**
		 * getMatrix() returns the matrix at position n so that matrix methods may be 
		 * used within the data structure
		 * 
		 * Param: 
		 * int n - the position of the matrix to be returned
		 * 
		 * Output:
		 * items[n] - The matrix at position "n"
		 */
		return items[n];
	}
	
	public boolean isEmpty(int n){
		/**
		 * isEmpty() returns true if there is no matrix at position n
		 * 
		 * Param: 
		 * int n - the position of the matrix to be checked
		 * 
		 * Output:
		 * boolean isEmpty - boolean indicating if there is a matrix at position "n"
		 */
		if (getMatrix(n)==null)
			return true;
		else 
			return false;
	}
	
	public int numMatrix(){
		/**
		 * numMatrix() returns the number of matrices stored in the data structure
		 * 
		 * Param: 
		 * None
		 * 
		 * Output:
		 * count - the number of matrices
		 */
		int count =0;				//  Counter to count the number of matrices
		while(!this.isEmpty(count))
			count++;
		return count;
	}
	
	public void readData(BufferedReader input)
			throws IOException
	{
		/**
		 * readData takes as input an BufferedReader created by IOMethods
		 * and stores the matrices in the array "items"
		 * 
		 * Param: 
		 * BufferedReader input - a BufferedReader containing the input to
		 * be read in
		 * 
		 * Output:
		 * None - The matrices are saved into the array "items"
		 */
		
		// Read in the first line
		String nums = input.readLine();
		
		// Assume that the matrix is square
		boolean isSquare = true;			// bool to show if matrix is square or not
		int x;
			
			// Keep reading from the file while there is input
			while(nums!=null){
				
				// Discount letters as valid input
				// Skip over the matrices that follow invalid input
				if (Pattern.matches("[a-zA-Z ]+", nums) || nums.length()>2){
					System.out.println("\""+nums+ "\""+" is an invalid Entry, skipping to next line");
					System.out.println();
				}
				else{
					// Assume matrix is square to start
					isSquare = true;
					// parse input based on spaces
					StringTokenizer t = new StringTokenizer(nums, " "); 
					
					// Read in the dimension of the array
					int n = Integer.parseInt(t.nextToken()); 
					// Only non-zero and non-negative numbers are valid for dimension
					if(n>0){
						
						int[][]values = new int[n][n];
						// Read in the lines equal to the dimension
						for(int i = 0; i < n; i++){
							// Read line and create tokenizer
							nums = input.readLine();
							StringTokenizer h = new StringTokenizer(nums, " ");
							// Read in each number on the line
							for(int j = 0; j <= n; j++)
								// Go one extra place (j==n) to see if the matrix is not square
								// Integer parsing encapsulated by try/catch to look for too few or
								// too many columns
								try{
									// store value
									x = Integer.parseInt(h.nextToken());
									
									// Matrix is not valid if there are j or more columns
									if (j==n){
										System.out.println("The Matrix is not square and will "+
												"be skipped");
										isSquare = false;
									}
									else
										values[i][j] = x;
								}catch(NoSuchElementException e){
									if (j<n){
										// Matrix is not valid if there are less than j-1 columns
										System.out.println("The Matrix is not square and will "+
												"be skipped");
										// Flag the matrix as not square
										isSquare = false;
									} // End If
							} // End Catch
						} // End For Loop
						// Only add the matrix if it is square
						if(isSquare == true)
							this.addMatrix(n,values);
					}
				}
				// Read in the next line (dimension)
				nums = input.readLine();
			} // End While
		//}
	}
	
	public void writeData(String outfile) throws IOException{
		/**
		 * writeData takes as input an output filename and prints 
		 * the matrix and its determinant to a file
		 * 
		 * Param: 
		 * String outFile - the name of the file to be written to
		 * 
		 * Output:
		 * None - The matrices and determinants are written to a file
		 */
		int count = 0;
		
		// PrintWriter created by IOMethods.writeData using outfile
		PrintWriter output = IOMethods.writeData(outfile);
		
		// Print the data to a file
		while (!this.isEmpty(count)){
			output.println("Matrix #" + (count+1));
			this.getMatrix(count).filePrint(output);
			this.getMatrix(count).fPrintDet(output);
			output.println();
			output.println();
			count++;
		}
		// Close the file
		output.close();
	}
	
	public void print(int n){
		/**
		 * print() prints a matrix at position "n" (in standard format) 
		 * to the command line
		 * 
		 * Param: 
		 * int n - the position of the matrix to be printed
		 * 
		 * Output:
		 * None - The matrix is printed to the command line
		 */
		items[n].printVals();
	}
	
	public void printAll(){
		/**
		 * printAll() prints all of the matrices, and their determinants, to 
		 * the command line 
		 * 
		 * Param: 
		 * None
		 * 
		 * Output:
		 * None - All matrices and determinants are printed to the 
		 * command line
		 */
		int n = 0;
		
		// Print the data to the command line
		while(items[n]!=null){
			System.out.println("Matrix #" + (n+1));
			this.getMatrix(n).printVals();
			System.out.println("Determinant = "+Matrix.det(this.getMatrix(n)));
			System.out.println();
			System.out.println();
			n++;
		}
			
	}
	
	private void expandArray(){
		/**
		 * expandArray() doubles the size of the array of matrices to allow for 
		 * additional matrices to be stored
		 * 
		 * Param: 
		 * None
		 * 
		 * Output:
		 * None - The array size is doubled
		 */
		//System.out.println("Expanding Array!!");
		Matrix[] temp = new Matrix[2*size];
		for (int i = 0; i<items.length; i++)
			temp[i] = items[i];		
		//temp = items;
		size = 2*size;
		items = new Matrix[size];
		items = temp;
		//System.out.println(items.length);
	}
}
