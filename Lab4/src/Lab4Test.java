
public class Lab4Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String filename = "ran50.dat";
		int x[];
		int[] data = IOMethods.readInfo(filename);
		//Util.printFile(data);
		//HeapSort.heapsort(data);
		x = Heap.Sort(data);
		Util.println(data.length);
		Util.println("Heap Sorted");
		Util.printFile(x);
		Util.println("");
		Util.println(x.length);
		int[] k = {17, 1, 5, 149, 1123, 373, 53};
		ShellSort sort1 = new ShellSort(k);
		x = sort1.sort(data);
		
		//Util.println("Shell Sorted");
		//Util.printFile(x);
		
		//Util.printFile(sort1.getIncrements());
	}

}
