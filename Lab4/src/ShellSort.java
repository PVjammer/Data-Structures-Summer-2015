
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
	
	public int[] sort(int[] x){
		int numRuns = 10;
		int [][] subfiles;
		int [][] data = new int[numRuns][x.length];
		int[] result = new int[x.length];
		int[] incr;
		int span;
		int y,k;
		int n = x.length;
		incr = detK(n);
		for (int i = 0; i < numRuns; i ++)
			for (int j = 0 ; j <x.length; j++)
				data[i][j] = x[j];
		
	
		
		sortStart = System.nanoTime();
		for ( int g = 0; g < numRuns; g++){
			for (int i = 0; i < incr.length; i ++){
				span = incr[i];
				for(int j = span; j < x.length; j ++){
					y = data[g][j];
					for(k = j-span; k >= 0 && y < data[g][k]; k -=span)
						data[g][k + span] = x[k];
					data[g][k + span] = y;
				}
			}
		}
		sortEnd = System.nanoTime();
		sortTime = sortEnd - sortStart;
		sortTime = sortTime / numRuns;
		for (int k1 = 0; k1 < x.length;k1++)
			result[k1] = data[0][k1];
		return result;
		
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
