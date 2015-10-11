import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

/**
 * 
 * @author Nicholas
 *
 */
public class Lab4Driver {
/*
 * 
 */
	static final int NUM_SORTS = 5;
	public static void main(String[] args) {
	/**
	 * 	 
	 */
		//*********************Constants*****************************
		final int[] INCR1 = {1,4,13,40,121,364,1093};
		final int[] INCR2 = {1,5,17,53,149,373,1123};
		final int[] INCR3 = {1,10,30,60,120,360,1080};
		final int[] INCR4 = {1,5,25,50,150,300,900};
		final String DEFAULT_FOLDER = "Lab 4 Input Files";
		
		//***********************************************************
		
		//*********************Variables*****************************
		int data[] = null;			// Array to hold the data sorted/unsorted
		String filename;	// Filename to be read from
		String outData;		// Filename to for writing runtime data
		String outFile;		// Filename for writing sorted data
		int sort;			// index of the sort
		String sortName="";	// String containing the type of sort run
		float sortTime=-1;		// The time (in nanoseconds) to run the sort
		int len;
		ShellSort shell1 = new ShellSort(INCR1);
		ShellSort shell2 = new ShellSort(INCR2);
		ShellSort shell3 = new ShellSort(INCR3);
		ShellSort shell4 = new ShellSort(INCR4);
		//***********************************************************
		
		final File folder = new File(DEFAULT_FOLDER);
		//listFilesForFolder(folder);
		String[] files = IOMethods.getFiles(folder);
		//Util.printFile(files);
		Util.println("Importing Files...");
		Data sortData[] = new Data[files.length];
		Util.print("Sorting.");
		for(int k = 0; k < files.length; k ++){
			if (k%(10)==0)
				Util.print(".");
			filename = folder+ "\\" +files[k];
			outFile = "Sorted_";
			sortData[k] = new Data(NUM_SORTS);
			sortData[k].setFile(files[k]);
			for(sort = 0; sort < NUM_SORTS; sort++){
				data = IOMethods.readInfo(filename);
				len = data.length;
				if (sort == 0){
					Heap.Sort(data);
					sortName = "Heap Sort";
					sortTime = Heap.sortTime()/1000000;
				}
				else if (sort == 1) {
					shell1.sort(data);
					sortName = "Shell Sort (k1)";
					sortTime = shell1.sortTime()/1000000;
				}
				else if (sort == 2){
					shell2.sort(data);
					sortName = "Shell Sort (k2)";
					sortTime = shell2.sortTime()/1000000;
				}
				else if (sort == 3){
					shell3.sort(data);
					sortName = "Shell Sort (k3)";
					sortTime = shell3.sortTime()/1000000;
				}
				else if (sort == 4 ){
					shell4.sort(data);
					sortName = "Shell Sort (k4)";
					sortTime = shell4.sortTime()/1000000;
				}
				outFile += +(char)k + files[k];
				
				// Create record of sort data for this file
				sortData[k].setData(sortName, sortTime);
				//sortData[k].setFile(files[k]);
				
				/*
				 * Write the sorted data to a file if the file size is 50 or less.
				 * Each sort will get its own file
				 */
				if(files[k].length() <= 50){
					try {
						IOMethods.writeData(outFile, files[k], data);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} // End Try/Catch
				} // End If
			} // End For
			sortData[k].setSize(data.length);
		} // End For
		Util.println("");
		printSummary(sortData);
		
	} // End Main
	
	public static void summaryFile(String outfile, Data[] sortData){
		
	}
	
	// Unused: delete prior to submitting
	public static void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            System.out.println(fileEntry.getName());
	        }
	    }
	}
	
	public static void printSummary(Data sortData[]){
		DecimalFormat df = new DecimalFormat("#.##");
		Util.print("Filename \t      Heap Sort\t\t   Shell Sort (k1)\t Shell Sort (k2)"
				+ "\t    Shell Sort (k3) \t  Shell Sort (k4) \t      File Size");
		Util.println("");
		for (int i =0;i < sortData.length; i++){
			System.out.print(sortData[i].getFile() + "\t");
			for (int k = 0; k < NUM_SORTS; k++){
				//System.out.print(sortData[i].getFile() + "\t");
				//System.out.print(sortData[i].getSorts()[k]+ "\t");
				if (k > 1)
					Util.print("");
				Util.print("\t");
				System.out.print(df.format(sortData[i].getTimes()[k])+ "\t");
				if(sortData[i].getTimes()[k] == 0.0)
					Util.print("\t");
				else if(sortData[i].getTimes()[k] / 100000 < 1.0)
					Util.print("\t");
				//System.out.print(sortData[i].getSize()+ "\t");
				//System.out.println();
			}
			
			Util.print("\t");
			System.out.print(sortData[i].getSize()+ "\t");
			Util.println("");
		}
	}


}
