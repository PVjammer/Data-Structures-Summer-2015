/**
 * 
 * @author Nicholas
 *
 */
public class Data {
	
	String filename;
	String[] sortMethod;
	float[] sortTime;
	int fileSize;
	int numSort;
	int top = -1;
	
	public Data(int numSort){
		sortMethod = new String[numSort];
		sortTime = new float[numSort];
		fileSize = 0;
	}

	public void setData(String s, float t){
		top ++;
		sortMethod[top] = s;
		sortTime[top] = t;
		//fileSize[top] = n;
	}
	/*
	public void setSort(String s){
		sortMethod = s;
	}
	
	public void setTime(float x){
		sortTime = x;
	}
	
	public void setSize(int n){
		fileSize = n;
	}
*/	
	public void setFile(String file){
		filename = file;
	}
	
	public String getFile(){
		return filename;
	}
	
	public String[] getSorts(){
		return sortMethod;
	}
	
	public float[] getTimes(){
		return sortTime;
	}
	
	public int getSize(){
		return fileSize;
	}
	
	public void setSize(int n){
		fileSize = n;
	}
}
