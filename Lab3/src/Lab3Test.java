import java.util.Random;;
public class Lab3Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 4;
		int count = 0;
		Matrix matrix = new Matrix(n);
		int[][] values = new int[n][n];
		int[] nums = new int[16];
		nums[0] = 2;
		nums[1] = 4;
		nums[2] = 5;
		nums[3] = 6;
		nums[4] = 0;
		nums[5] = 3;
		nums[6] = 6;
		nums[7] = 9;
		nums[8] = 0;
		nums[9] = 0;
		nums[10] = 9;
		nums[11] = 8;
		nums[12] = 0;
		nums[13] = 0;
		nums[14] = 0;
		nums[15] = 5;
		
		
		
		
		for(int i = 0;i <n; i ++ ){
			for(int j = 0;j<n;j++){
				values[i][j] = nums[count];
				count++;
				Util.print(values[i][j]);
			}
		}
		Util.println("");
		Node[] nodeList = new Node[3];
		
		for(int i = 0; i< nodeList.length; i ++){
			nodeList[i] = new Node();
			nodeList[i].setInfo(i);
		}
			
		Matrix m = new Matrix();
		
		m.addCol(nodeList);
		m.addCol(nodeList);
		m.addCol(nodeList);
		
		m.printVals();
		
		Util.println("");
		//Util.println(m.getNumCols());
		//System.out.println(matr)
		matrix.setValues(values);
		matrix.printVals();
		Util.println("");
		//Util.println(matrix.dim());
		//Util.println(matrix.getNode().getInfo());
		Util.println(Matrix.det(matrix));
		Matrix h = new Matrix(1);
		int[][] val = new int[1][1];
		val[0][0] = 4;
		h.setValues(val);
		//h.printVals();
		Util.println(Matrix.det(h));
		
		
	}

}
