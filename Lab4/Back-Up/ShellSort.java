
public class ShellSort {
	private int[] increments;
	private float sortStart;
	private float sortEnd;
	private float sortTime = -1;
	
	public ShellSort(int[] k){
		increments = new int[k.length];
		for(int i =0;i<k.length;i++)
			increments[i] = k[i];
		insertionSort(increments);
	}
	
	public void sort(int[] x){
		int [][] subfiles;
		int[] incr;
		int span;
		int y,k;
		int n = x.length;
		incr = detK(n);
		sortStart = System.nanoTime();
		for (int i = 0; i < incr.length; i ++){
			span = incr[i];
			for(int j = span; j < x.length; j ++){
				y = x[j];
				for(k = j-span; k >= 0 && y < x[k]; k -=span)
					x[k + span] = x[k];
				x[k + span] = y;
			}
		}
		sortEnd = System.nanoTime();
		sortTime = sortEnd - sortStart;
	}
	
	private void insertionSort(int[] x){
		int temp;
		int i,j;
		for (i = 1; i < x.length; i ++){
			temp = x[i];
			for (j = i - 1; j >=0 && temp < x[j]; j--){
				x[j+1] = x[j];
			}
			x[j+1] = temp;
		}
		
	}
	
	private int[] detK (int n){
		int[] incr;
		int num = increments.length-3;
		int count = 0;
		
		for(int i = 0; i < increments.length; i ++){
			if(n < increments[i]){
				if(i < 2)
					num = 0;
				else
					num = i-2;
			}				
		}
		incr = new int[num+1];
		for(int k = num; k>=0;k--){
			incr[count] = increments[k];
			count ++;
		}
		
		return incr;
	}
	public int[] getIncrements(){
		return increments;
	}
	
	public float sortTime(){
		return sortTime;
	}
	
	
}
