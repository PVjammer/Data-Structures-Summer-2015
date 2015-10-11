import java.math.*;
import java.io.*;

public class Matrix {
/**
 * Class to hold the matrix as a 2 dimensional array and perform matrix operations	
 */
	private int rows;
	private int cols;
	private int[][] matrix;
	
	// public Matrix(int n, int[][]values)
	
	public Matrix(int n){
		rows = n;
		cols = n;
		new Matrix();
		this.allZero();
	}
	
	public Matrix(int n, int[][]values){
		rows = n;
		cols = n;
		new Matrix();
		this.setValues(values);
	}
	
	public Matrix(){
		matrix = new int[rows][cols];
	}
	
	public int getRows(){
		return rows;
	}
	
	public int getCols(){
		return cols;
	}
	
	public void print(){
		for(int i = 0; i<rows; i++){
			for(int j=0;j<cols;j++)
				System.out.print(matrix[i][j]+" ");
			System.out.println();
		}	
		System.out.println();
	}
	
	public void filePrint(PrintWriter output){
		for(int i = 0; i<rows; i++){
			for(int j=0;j<cols;j++)
				output.print(matrix[i][j]+" ");
			output.println();
		}	
		output.println();
	}
	
	public void fPrintDet(PrintWriter output){
		this.print();
		output.print("Determinant = " + Matrix.det(this));
		output.println();
	}
	
	public void setValues(int[][] values){
		matrix = values;
	}
	
	private void allZero(){
		int[][]zeros = new int[rows][cols];
		
		for(int i=0; i<rows; i++)
			for(int j =0;j<cols;j++)
				zeros[i][j]=0;
		this.setValues(zeros);
	}
	
	public int dim(){
		return rows;
	}
	
	public int getValue(int n, int m){
		return matrix[n][m];
	}
	
	public void setValue(int n, int m, int value){
		matrix[n][m]=value;
	}
	
	public static float det(Matrix a){
		int cofactor;
		
		Matrix minor = new Matrix(a.getRows()-1);
		
		float result=0;
		int row = 0;
		int col = 0;
		
		if(a.dim()==1)
			return a.getValue(0, 0);
		else{
			for(int i = 0;i<a.getRows();i++){
				cofactor = a.getValue(0,i);
				row = 0;
				col = 0;
				for(int j=1;j<a.getRows();j++){
					col=0;												
						for(int k = 0;k<a.getCols();k++ ){
							if(k!=i){
								minor.setValue(row,col,a.getValue(j, k));
								col++;
							} // End if
						} // End For k
						row++;
				} // Ebd For j
				if(i%2==0)
					result += cofactor*det(minor);
				else
					result -= cofactor*det(minor);				 
			} // End For i 
			
			return result;
		} // End Else
	} // End det(a)
}
