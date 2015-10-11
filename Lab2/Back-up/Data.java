
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Data {
/**
 * Class to hold the matricies and determinants for the purposes of passing them back and forth between methods
 */
	private static int DEFAULT_SIZE = 10;	// Default size of the array of matricies
	private Matrix[] items;					// Array of matricies
	private int size;
	private int top = -1;
	
	public Data(){
		size = DEFAULT_SIZE;
		items = new Matrix[size];
	}
	
	public Data(int n){
		size = n;
		items = new Matrix[size];
	}
	
	public void addMatrix(int n){
		top++;
		items[top] = new Matrix(n);
	}
	
	public void addMatrix(int n, int[][]values){
		//System.out.println(n+ "  "+ this.numMatrix());
		top++;
		//System.out.println(top+ " = "+items.length);
		//System.out.print(" " +items.length);
		if(top == items.length-1)
			expandArray();
		items[top] = new Matrix(n, values);
	}
	
	public Matrix getMatrix(int n){
		return items[n];
	}
	
	public boolean isEmpty(int n){
		if (getMatrix(n)==null)
			return true;
		else 
			return false;
	}
	
	public int numMatrix(){
		int count =0;
		while(!this.isEmpty(count))
			count++;
		return count;
	}
	
	public void readData(BufferedReader input)
			throws IOException
	{
		String nums = input.readLine();
		System.out.println(nums);
		//if (Pattern.matches("[a-zA-Z]+", nums) || nums.length()>2)
			//System.out.println("\""+nums+ "\""+" is an invalid Entry, skipping to next line");
		//else{
			while(nums!=null){
				if (Pattern.matches("[a-zA-Z ]+", nums) || nums.length()>2){
					System.out.println("\""+nums+ "\""+" is an invalid Entry, skipping to next line");
					System.out.println();
				}
				else{
					
					StringTokenizer t = new StringTokenizer(nums, " "); 
					
					int n = Integer.parseInt(t.nextToken()); 
					if(n>0){
						
						int[][]values = new int[n][n];
						for(int i = 0; i < n; i++){
							nums = input.readLine();
							StringTokenizer h = new StringTokenizer(nums, " "); 
							for(int j = 0; j < n; j++)
								values[i][j] = Integer.parseInt(h.nextToken());
							try{
								if(h.nextToken()!="")
									System.out.println("Non Square Matrix!");
									System.exit(1);
							}catch(NoSuchElementException e){
								// I know that I'm not supposed to use a blank catch statement,
								// but it I'm actually exiting on the try.
							}
						} // End For Loop
						
						
						// ALternate Method
						/*
						 * int[][]values = new int[n][n];
						int count = 0;
						int x;
						int count2 =0;
						while (count <n){
							nums = input.readLine();
							StringTokenizer h = new StringTokenizer(nums, " ");
							x = Integer.parseInt(h.nextToken());
							while(){
									values[count][count2] = x;
									count2++;
									x = Integer.parseInt(h.nextToken());
							}
						}*/
						
						this.addMatrix(n,values);
					}
				}
				nums = input.readLine();
			} // End While
		//}
	}
	
	public void writeData(String outfile) throws IOException{
		int count = 0;
		
		PrintWriter output = IOMethods.writeData(outfile);
		while (!this.isEmpty(count)){
			output.println("Matrix #" + (count+1));
			this.getMatrix(count).filePrint(output);
			this.getMatrix(count).fPrintDet(output);
			output.println();
			output.println();
			count++;
		}
		output.close();
	}
	
	public void print(int n){
		items[n].print();
	}
	
	private void expandArray(){
		System.out.println("Expanding Array!!");
		Matrix[] temp = new Matrix[2*size];
		for (int i = 0; i<items.length; i++)
			temp[i] = items[i];		
		//temp = items;
		size = 2*size;
		items = new Matrix[size];
		items = temp;
		System.out.println(items.length);
	}
}
