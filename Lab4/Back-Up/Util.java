
public class Util {
	
	public static void print(Object n){
		System.out.print(n);
	}
	
	public static void println(Object n){
		System.out.println(n);
	}
	
	public static void printFile(Object[] data){
		for( int i = 0; i<data.length; i ++)
			println(data[i]);
	}
	public static void printData(int[] data){
		for( int i = 0; i<data.length; i ++)
			println(data[i]);
	}
}
