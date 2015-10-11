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
	private Column root;
	private Column last;
	private int dim;
	private int numCols = 0;
	
	public Matrix(){
		//root = new Column();
		last = root;
	}
	
	public Matrix (int n){
		dim = n;
		//root = new Column();
		//if (n>1){
			for(int i = 0; i < n; i ++)
				addCol();
		//}
	}
	
	public void addCol(Column n){
		Column temp = n;
		if(numCols == 0){
			root = new Column();
			last = root;
			numCols++;
		}
		else{
			temp = new Column();
			last.setNext(temp);
			last = temp;
			last.setNext(root);
			numCols++;
		}
	}
	
	public void addCol(Node[] n){
		Column temp;
		if(numCols == 0){
			root = new Column();
			for (int i = 0; i < n.length;i++){
				root.addNode(n[i].getInfo());
				//Util.println("Adding: " + n[i].getInfo());
			}
			last = root;
			numCols=1;
		}
		else{
			temp = new Column();
			last.setNext(temp);
			for (int i = 0; i < n.length;i++){
				temp.addNode(n[i].getInfo());
				//Util.println("Adding: " + n[i].getInfo());
			}
			last = temp;
			last.setNext(root);
			numCols++;
		}
	}
	
	public void addCol(){
	/**
	 * addCol() adds a column (a linked list) to the matrix (a linked list)
	 * 
	 * Param: none
	 * 
	 * output: none
	 */
		Column temp;
		if(numCols == 0){
			root = new Column();
			last = root;
			numCols++;
		}
		else{
			temp = new Column();
			last.setNext(temp);
			last = temp;
			last.setNext(root);
			numCols++;
		}
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
		if(numCols == 0)
			Util.println("Nothing to print!");
		else{
			Column current = root;
			Node temp;
			temp = current.getNode();
			int count = 0;
			for( int i = 0; i < numCols; i++){
				
				for ( int j = 0; j < numCols; j++){
					while(count < i){
						temp = temp.getnext();
						count++;
					}
						
					output.print(temp.getInfo());
					output.print(" ");
					current = current.getNext();
					if(numCols>1)
						temp = current.getNode();
					count = 0;
				}
			output.println();
			
			}
		}
	}
	
	public void setValues(int values[][]){
		/**
		 * 
		 */
		//root = new Column();
		Column current = root;
		//Node temp;
		
		for( int i = 0; i < dim; i++){
			for ( int j = 0; j < dim; j++){
				current.addNode(values[i][j]);
				current = current.getNext();
			}
			
		}
	}
	
	public void printVals(){
		//Util.println("Printing...("+numCols+")");
		//Util.println(numCols);
		if(numCols == 0)
			Util.println("Nothing to print!");
		else{
			Column current = root;
			Node temp;
			temp = current.getNode();
			int count = 0;
			for( int i = 0; i < numCols; i++){
				
				for ( int j = 0; j < numCols; j++){
					while(count < i){
						temp = temp.getnext();
						count++;
					}
						
					Util.print(temp.getInfo());
					Util.print(" ");
					//temp = temp.getnext();
					current = current.getNext();
					if(numCols>1)
						temp = current.getNode();
					count = 0;
				}
			Util.println("");
			
			}
		}
	}
	public int[][] getValues(){
		int[][] vals = new int[numCols][numCols];
		if(numCols == 0)
			Util.println("No Values!");
		else{
			Column current = root;
			Node temp;
			temp = current.getNode();
			int count = 0;
			for( int i = 0; i < numCols; i++){
				
				for ( int j = 0; j < numCols; j++){
					while(count < i){
						temp = temp.getnext();
						count++;
					}
						
					vals[i][j] = temp.getInfo();
					current = current.getNext();
					if(numCols>1)
						temp = current.getNode();
					count = 0;
				}
			
			}
		}
		
		return vals;
	}
	
	public int dim(){
		return numCols;
	}
	public Node getNode(){
		
		return root.getNode();
	}
	
	private class Column{
		/**
		 * Column class
		 */
		private Node header;
		private Node last;
		private int length;
		private Column next;
		//private int colHeader;
		int numNodes = 0;
		
		private Column(){
			next = null;
			header = null;
		}
		
		private Column(int n){
			length = n;
			//setHeaders();
		}
		
	
		
		private void setNext(Column n){
			next = n;
		}
		
		private Column getNext(){
			return next;
		}
		
		private void addNode(int n){
			//Node p = new Node(n,null);
			//p.setInfo(n);
			//p.setNext(null);
			//System.out.println("I'm in!");
			if(isEmpty()){
				insertFirst(n);
				numNodes = 1;
			}
			else {
				insertAfter(n);
				numNodes++;
			}
		}
		
		public boolean isEmpty(){
			return header == null;
		}
		
		private void insertFirst(int x){
			Node q = new Node();
			q.setInfo(x);
			q.setNext(null);
			header = q;
			last = header;
		}
		
		private void insertAfter(int x){
			Node q = new Node();
			q.setInfo(x);
			q.setNext(null);
			last.setNext(q);
			last = q;
		}
		
		
		private void deleteFirst(){
			Node temp;
			
			if (!isEmpty()){
				temp = header.getnext();
				header.setNext(temp.getnext());
				temp.setNext(null);
			}
		}
		
		private Node getNode(){
			return header;
		}
		
	}
	
	public static double det(Matrix m){
	/**
	 * Static method det() calculates the determinant of the matrix recursively
	 *  
	 * Param: 
	 * Matrix a - the matrix whose determinant will be calculated
	 * 
	 * Output:
	 * double value of the determinant
	 */	
		//m.printVals();
		Node temp;
		Node[] nodeList; 
		Node cofactor;
		Column column;
		Column column2;
		Matrix minor;
		int x;
		// Matrix minor = new Matrix(a.getRows()-1);	// A minor of the matrix (type Matrix) 
		
		double result=0;
		int row = 0;
		int colCount = 0;
		column2 = m.root;
		int[] y = new int[4];
		if (m.numCols == 1)
			return (double) m.getNode().getInfo();
		else if(m.numCols==2){
			//return (double) m.getNode().getInfo();
			column = m.root;
			for(int i = 0; i < m.numCols;i++){
				y[i] = column.getNode().getInfo();
				y[i+2] = column.getNext().getNode().getnext().getInfo();
				column = column.getNext();
			}
			
			return y[0]*y[2] - y[1]*y[3];
		}
		else{
			for (int k = 0; k < m.numCols; k++){
				minor = new Matrix();
				column = m.root;
				cofactor = column2.getNode();
				//Util.println("Cofactor: " + cofactor.getInfo());
				temp = m.root.getNode();
				for(int i = 0; i < m.numCols; i ++){
					//temp = temp.getnext();
					temp = column.getNode();
					nodeList = new Node[column.numNodes-1];
					if(i ==k)
						Util.print("");//Util.println("Skipping Column: " + (colCount+1));
					else{
						for(int j = 0; j < m.numCols-1;j++){
							temp = temp.getnext();
							//Util.print(temp.getInfo());
							//Util.println("Updated temp: " + temp.getInfo());
							nodeList[j] = temp;
						}
						minor.addCol(nodeList);	
					}
					column = column.getNext();
				}
				//minor.printVals();
				x = cofactor.getInfo();
				if(k%2==0)
					result += x*det(minor);
				else
					result -= x*det(minor);
				column2 = column2.getNext();
				colCount++;
			}
		}
		return result;
	}
	
}
