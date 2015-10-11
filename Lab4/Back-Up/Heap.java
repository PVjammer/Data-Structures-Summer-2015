
public class Heap {
	
	private static float sortTime = -1;
	private static float sortStart;
	private static float sortEnd;
	
	public static void Sort(int[] x){
		sortStart = System.nanoTime();
		buildHeap(x);
		sortHeap(x);
		sortEnd = System.nanoTime();
		sortTime = sortEnd - sortStart;
	}


	private static void buildHeap(int[] x){
		int elem;
		//int[] answer = new int[x.length];
		int child;
		int parent;
		if (x.length == 0)
			return;
		
		for (int i = 0; i <x.length; i ++){
			elem = x[i];
			
			child = i;
			parent = (child-1)/2;
			while(child>0 && x[parent]<elem){
				x[child] = x[parent];
				child = parent;
				parent = (child-1)/2;
			}
			x[child] = elem;
		}
	}
	
	private static void sortHeap(int[] x){
		int num;
		int child;
		int parent;
		for (int i = x.length - 1; i > 0; i--){
			num = x[i];
			x[i] = x[0];
			parent = 0;
			
			if (i == 1)
				child = -1;
			else
				child = 1;
			if (i > 2 && x[2] > x[1])
				child = 2;
			while (child >= 0 && num < x[child]) {
				x[parent] = x[child];
				parent = child;
				child = 2*parent + 1;
				if(child + 1 <= i-1 && x[child] < x[child+1])
					child = child+1;
				if (child > i-1)
					child = -1;
			}
			x[parent] = num;
		}
	}
	
	public static float sortTime(){
		return sortTime;
	}

}