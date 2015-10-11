import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//import LabOneStack;

public class Lab1Driver {
	
	static boolean isDebug = false;

	public static void main(String[] args) {
		
		final String DEFAULT_INPUT = "NBLab1_input.txt";
		final String DEFAULT_OUTPUT = "NBoutput.txt"; 
		final int numLang = Translate.getnumLang();
		String inFilename[] = new String[10];
		String outFilename ="output.txt";
		String langStr;
		int arraySize = 30;
		int count;
		String[] languages = null;
		String[] outputString = null;
		int[] result;
		String[][] final_output = null;
		
		
		/* The Driver only expects and handles one input argument.
		 * The argument can be a filename or "-d" which will run the
		 * program in debug mode with hardcoded filenames.
		 */
		if(args.length == 0){
			System.out.println("No parameters detected...Aborting Program.");
			System.exit(1);
		}
		
		for (int k =0;k<args.length;k++){
			if (args[k].equals("-i")){
				k++;
				count = 0;
				while (!args[k].equals("-o")&& !args[k].equals("-d") && k< args.length){
					System.out.println(count +" " +k + " " + args.length);
					inFilename[count] = args[k];
					count++;
					k++;
					if(k>=args.length)
						break;
				} // End While Loop 
				count = 0;
				if(k>=args.length)
					break;
			} // End IF Statement
			System.out.println(args[k]);
			if (args[k].equals("-o")){
				k++;
				outFilename = args[k];
				
			} // End Else IF Statement
			if(args[k].equals("-d")){
				isDebug=true;
				System.out.println("Debug Mode!");
				break;
			} //End If Statement
			
		} // End For Loop
		if (isDebug==true){
			inFilename = new String[1];
			inFilename[0] = DEFAULT_INPUT;
			outFilename = DEFAULT_OUTPUT;
		}
		for(int g = 0;g<inFilename.length;g++)
			//System.out.println(inFilename[g]);
		
		//System.out.println(inFilename);
		
		/* Begin Meat & Potatoes
		 * 
		 * 
		 */
			
	    final_output = new String[inFilename.length][40];	
		for (int ii = 0; ii<inFilename.length;ii++){
			System.out.println(inFilename[ii]);
			
			if(inFilename[ii] == null)
				break;
			try { 
				BufferedReader input = new BufferedReader(new 
						FileReader(inFilename[ii]));	 
				 arraySize = IOMethods.countInput(input);
				 input.close(); 
			} catch (IOException exception) { 
			System.out.print("Error: " + exception); 
			System.exit(1); 
			}
			
			languages = new String[arraySize];
			outputString = new String[arraySize];
			
			// File input is encapsulated in try statement. 
			try { 
				BufferedReader input = new BufferedReader(new 
						FileReader(inFilename[ii]));
				 languages = IOMethods.readData(input, arraySize);		 
				input.close(); 
			} catch (IOException exception) { 
			System.out.print("Error: " + exception); 
			System.exit(1); 
			}
			
			for(int i = 0;i<languages.length;i++){
				
				if(languages[i] != null){
					langStr = allCaps(languages[i]);
					result = Translate.calcLang(langStr,numLang);
					//outputString[i] = formatResult(result,languages[i],numLang);
					final_output[ii][i]= formatResult(result,languages[i],numLang);
				} // End If
				
			} // End For
			
		} // End For Loop	
			System.out.println("Program Execution Complete");
			// File output is encapsulated in try statement.
			
			try { 
				PrintWriter output = new PrintWriter(new 
						FileWriter(outFilename));
				String header = formatHeader(numLang);
				IOMethods.writeHeader(header, output);
				//IOMethods.writeData(outputString, output);
				for(int h = 0; h<inFilename.length;h++)
					IOMethods.writeData(final_output[h], output);
				output.close(); 
			} // End Try
			catch(IOException exception) { 
				System.out.print("Error: " + exception); 
				System.exit(1); 
			} // End Catch
			
		
	} // End Main method
	/*
	 * Language 1: Equal numbers of A's and B's; Any order
	 * Language 2: A^n, B^n
	 * Language 3: A^n, B^2n
	 * Language 4: (A^n, B^m)p
	 * Language 5: ?????
	 */
	
	public static void printResult(int[] result, String s,int num){
		String output = "";
		System.out.print(s);
		System.out.print("\t\t\t");
		for(int i=0;i<num; i++){
			System.out.print(" ");
			switch (result[i]){
				case 0: output = "No";
						break;
				case 1: output = "Yes";
						break;
			}
			System.out.print(output);
				
		}
		
		System.out.println("");
	}
	
	public static String formatResult(int[] result, String s,int num){
		String formatted = "";
		String output = "";
		formatted += s;
		if(s.length()>7)
			formatted += "\t\t";
		else
			formatted += "\t\t\t";
		
		for(int i=0;i<num; i++){
			switch (result[i]){
				case 0: output = "No";
						break;
				case 1: output = "Yes";
						break;
			}		
		formatted += output;
		formatted+="\t";
		}
		
		return formatted;
	}
	
	private static String formatHeader(int num){
		String output = "";
		output+="Language String";
		output +="\t\t";
		for (int i=1;i<=num;i++)
			output+="L"+i+"\t";
		return output;
	}
	private static String allCaps(String s){
		String caps = "";
		caps = s.toUpperCase();
		return caps;
	}
	
} // End Lab1Driver Class


