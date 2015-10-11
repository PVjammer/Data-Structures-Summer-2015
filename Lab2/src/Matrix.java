/**Matrix
 * ________
 * @author Nicholas C. Burnett
 * Version: 1.00
 * Date: 7/14/2015
 * 
 *  A class used to create matrices and house the matrix operations
 *
 */
import java.math.*;
import java.io.*;

public class Matrix {
/**
 * Class to hold the matrix as a 2 dimensional array and perform matrix operations	
 */
	private int rows;					// The number of rows in the matrix
	private int cols;					// The number of columns in the matrix
	private int[][] matrix;				// 2-Dimensional array of integers
	
	public Matrix(){
		/**
		 * Default constructor
		 * Creates a matrix object of no size.
		 * Used by other constructors.
		 */
		matrix = new int[rows][cols];
	}
	
	public Matrix(int n){
		/**
		 * Constructor
		 * Creates a matrix of dimension n with all zero values
		 */
		rows = n;
		cols = n;
		// Create a new Matrix
		new Matrix();
		// Set all values to zero
		this.allZero();
	}
	
	public Matrix(int n, int[][]values){
		/**
		 * Constructor
		 * Creates a matrix of dimension n with the values specified
		 * by "values"
		 */
		rows = n;
		cols = n;
		// Create a new matrix
		new Matrix();
		// set the values of the matrix
		this.setValues(values);
	}
	
	public int getRows(){
		/**
		 * getRows() returns the number of rows in the Matrix
		 *  
		 * Param: 
		 * 
		 * Output:
		 * int Rows - the number of rows
		 */
		return rows;
	}
	
	public int getCols(){
		/**
		 * getCols() returns the number of columns in the Matrix
		 *  
		 * Param: 
		 * None
		 * 
		 * Output:
		 * int cols - the number of columns
		 */
		return cols;
	}
	
	public void print(){
		/**
		 * print() prints a formatted matrix to the command line
		 *  
		 * Param: 
		 * None
		 * 
		 * Output:
		 * None - Prints the matrix
		 */
		for(int i = 0; i<rows; i++){
			for(int j=0;j<cols;j++)
				System.out.print(matrix[i][j]+" ");
			System.out.println();
		}	
		System.out.println();
	}
	
	public void filePrint(PrintWriter output){
		/**
		 * print() prints a formatted matrix to a file
		 *  
		 * Param: 
		 * None
		 * 
		 * Output:
		 * None - Prints the matrix
		 */
		for(int i = 0; i<rows; i++){
			for(int j=0;j<cols;j++)
				output.print(matrix[i][j]+" ");
			output.println();
		}	
		output.println();
	}
	
	public void fPrintDet(PrintWriter output){
		/**
		 * print() prints the determinant of the matrix to a file
		 *  
		 * Param: 
		 * None
		 * 
		 * Output:
		 * None - Prints the determinant
		 */
		output.print("Determinant = " + Matrix.det(this));
		output.println();
	}
	
	public void setValues(int[][] values){
		/**
		 * setValues() sets the values of a matrix to those specified
		 * by the array values[][]
		 *  
		 * Param: 
		 * values[][] - a 2 dimensional array of matrix values
		 * 
		 * Output:
		 * Sets the values of the matrix
		 */
		matrix = values;
	}
	
	private void allZero(){
		/**
		 * setValues() sets all the values of a matrix to zero
		 *  
		 * Param: 
		 * 
		 * Output:
		 * Sets the values of the matrix to zero
		 */
		int[][]zeros = new int[rows][cols];
		
		for(int i=0; i<rows; i++)
			for(int j =0;j<cols;j++)
				zeros[i][j]=0;
		this.setValues(zeros);
	}
	
	public int dim(){
		/**
		 * dim() returns the dimension of the square matrix
		 *  
		 * Param: 
		 * None
		 * 
		 * Output:
		 * rows - The number of rows/columns in the matrix 
		 */
		return rows;
	}
	
	public int getValue(int n, int m){
		/**
		 * getValue() returns the value at a given index of the matrix
		 *  
		 * Param: 
		 * int m - the row index
		 * int n - the column index
		 * 
		 * Output:
		 * matrix[m][n] - returns the value at the specified location
		 */
		return matrix[n][m];
	}
	
	public int[][] getValues(){
		/**
		 * getValues() returns the values of a matrix to an integer array
		 *  
		 * Param: 
		 * 
		 * Output:
		 * matrix returns the 2D array of integers comprising the matrix
		 */
		return matrix;
	}
	
	public void setValue(int n, int m, int value){
		/**
		 * setValue() sets the value of at a specific matrix location
		 *  
		 * Param: 
		 * int n - row index
		 * int m - column index
		 * value - value to be inserted
		 * 
		 * Output:
		 * None - updates matrixx
		 */
		matrix[n][m]=value;
	}
	
	public static double det(Matrix a){
		/**
		 * Static method det() calculates the determinant of the matrix recursively
		 *  
		 * Param: 
		 * Matrix a - the matrix whose determinant will be calculated
		 * 
		 * Output:
		 * double value of the determinant
		 */
		int cofactor;								// The cofactor
		
		Matrix minor = new Matrix(a.getRows()-1);	// A minor of the matrix (type Matrix) 
		
		double result=0;
		int row = 0;
		int col = 0;
		
		// Stop case, if the array is 1 x 1, return the value 
		if(a.dim()==1)
			return a.getValue(0, 0);
		else{
			// Calculate determinant
			for(int i = 0;i<a.getRows();i++){
				// Store cofactor
				cofactor = a.getValue(0,i);
				row = 0;
				col = 0;
				for(int j=1;j<a.getRows();j++){
					col=0;												
						for(int k = 0;k<a.getCols();k++ ){
							if(k!=i){
								// Store minor
								minor.setValue(row,col,a.getValue(j, k));
								col++;
							} // End if
						} // End For k
						row++;
				} // End For j
				// Construct determinant
				if(i%2==0)
					result += cofactor*det(minor);
				else
					result -= cofactor*det(minor);				 
			} // End For i 
			
			return result;
		} // End Else
	} // End det(a)
}
