
public class Heap {
	
	private static float sortTime = -1;
	private static long sortStart = 0;
	private static long sortEnd = 0;
	
	public static int[] Sort(int[] x){
		int numRuns = 5;
		int i =0;
		int [][] data = new int[numRuns][x.length];
		int[] result = new int[x.length];
		if (x.length==0)
			return x;
		for (int g = 0; g<numRuns ; g++)
			for(int j = 0;j<x.length; j ++)
				data[g][j] = x[j];
	
		sortStart = System.nanoTime();
		
		for(i = 0 ; i < numRuns; i ++){
			for(int ii = 0; ii < x.length; ii++){
				Util.print(data[i][ii]);
				Util.print(" ");
			}
			buildHeap(data,i);
			sortHeap(data,i);
			Util.println("");
			Util.println("");
		}
		sortEnd = System.nanoTime();
		sortTime = sortEnd - sortStart;
		sortTime = sortTime/numRuns;;
		for (int k = 0; k < x.length;k++)
			result[k] = data[0][k];
		return result;
	}


//	private static void buildHeap(int[][] x, int j){
//		int elem;
//		//int[] answer = new int[x.length];
//		int child;
//		int parent;
//		if (x.length == 0)
//			return;
//		
//		for (int i = 1; i <x.length; i ++){
//			elem = x[j][i];
//			
//			child = i;
//			parent = (child-1)/2;
//			while(child>0 && x[j][parent]<elem){
//				x[j][child] = x[j][parent];
//				child = parent;
//				parent = (child-1)/2;
//			}
//			x[j][child] = elem;
//		}
//	}
//	
//	private static void sortHeap(int[][] x, int j){
//		int i;
//		int num;
//		int child;
//		int parent;
//		if (x.length==0)
//			return;
//		for ( i = x.length - 1; i > 0; i--){
//			num = x[j][i];
//			x[j][i] = x[j][0];
//			parent = 0;
//			
//			if (i == 1)
//				child = -1;
//			else
//				child = 1;
//			if (i > 2 && x[j][2] > x[j][1])
//				child = 2;
//			while (child >= 0 && num < x[j][child]) {
//				x[j][parent] = x[j][child];
//				parent = child;
//				child = 2*parent + 1;
//				if(child + 1 <= i-1 && x[j][child] < x[j][child+1])
//					child = child+1;
//				if (child > (i-1))
//					child = -1;
//			}
//			x[j][parent] = num;
//		}
//	}
	
	public static float sortTime(){
		return sortTime;
	}

	private static void buildHeap(int[][] x, int j){
		int elem;
		//int[] answer = new int[x.length];
		int child;
		int parent;
		if (x.length == 0)
			return;
		
		for (int i = 0; i <x.length; i ++){
			elem = x[j][i];
			
			child = i;
			parent = (child-1)/2;
			while(child>0 && x[j][parent]<elem){
				x[j][child] = x[j][parent];
				child = parent;
				parent = (child-1)/2;
			}
			x[j][child] = elem;
		}
	}
	
	private static void sortHeap(int[][] x, int j){
		int num;
		int child;
		int parent;
		for (int i = x.length - 1; i > 0; i--){
			num = x[j][i];
			x[j][i] = x[j][0];
			parent = 0;
			
			if (i == 1)
				child = -1;
			else
				child = 1;
			if (i > 2 && x[j][2] > x[j][1])
				child = 2;
			while (child >= 0 && num < x[j][child]) {
				x[j][parent] = x[j][child];
				parent = child;
				child = 2*parent + 1;
				if(child + 1 <= i-1 && x[j][child] < x[j][child+1])
					child = child+1;
				if (child > i-1)
					child = -1;
			}
			x[j][parent] = num;
		}
	}
	
}