
public class Lab4Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String filename = "ran10k.dat";
		
		int[] data = IOMethods.readInfo(filename);
		//Util.printFile(data);
		//HeapSort.heapsort(data);
		//Heap.Sort(data);
		int[] k = {17, 1, 5, 149, 1123, 373, 53};
		ShellSort sort1 = new ShellSort(k);
		sort1.sort(data);
		Util.println("Sorted");
		//Util.printFile(data);
		
		//Util.printFile(sort1.getIncrements());
	}

}
