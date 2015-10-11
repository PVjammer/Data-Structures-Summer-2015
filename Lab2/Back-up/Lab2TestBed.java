import java.io.IOException;


public class Lab2TestBed {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String filename = "Lab2Input.txt";
		int n;
		float ans;
		Data problem = new Data();
		problem = IOMethods.readInfo(filename);
		int count = 0;/*
		while(problem.getMatrix(count)!=null){
			problem.print(count);
			ans = Matrix.det(problem.getMatrix(count));
			System.out.println();
			System.out.println("Determinant = " + ans);
			count++;
		}*/
		
		String outfile = "Lab2Output.txt";
		
		try {
			problem.writeData(outfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Program Execution Complete");
		
		/*int n = 3;
		int count = 0;
		
		int qwe = 1;
		
		Data problem = new Data();
		int[][] values = new int [n][n];
		int[][] values2 = new int [n][n];
		for(int i =0;i<n; i++){
			for(int j=0;j<n;j++){
				values[i][j] = qwe;
				values2[i][j] = i+j+1;
				qwe++;
			}
		}
		
		Matrix mat1 = new Matrix(n,values);
		problem.addMatrix(n, values);
		problem.addMatrix(n,values2);
		problem.addMatrix(n);
		problem.addMatrix(1);
		problem.getMatrix(3).setValue(0, 0, 5);
		float g = Matrix.det(problem.getMatrix(3));
		
		//System.out.println(g);
		// mat1.print();		
		int[][] a = new int[3][3];
		a[0][0] = 8;
		a[0][1] = 10;
		a[0][2] = 11;
		
		a[1][0] = 14;
		a[1][1] = 8;
		a[1][2] = 2;
		
		a[2][0] = 1;
		a[2][1] = 1;
		a[2][2] = 1;
		
		problem.getMatrix(0).setValues(a);
		//System.out.println(problem.isEmpty(0));
		//System.out.println(problem.isEmpty(4));
		
		//problem.getMatrix(0).print();
		//System.out.println("starting loop");
		System.out.println();
		while (!problem.isEmpty(count)){
			//System.out.println(count);
			//System.out.println(problem.isEmpty(count));
			//problem.print(count);
			count++;
		}
		
		System.out.print("Determinant: ");
		System.out.println(Matrix.det(problem.getMatrix(0)));
		*/
	}

}
