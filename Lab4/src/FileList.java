
public class FileList {
		
	FileNode files;
	FileNode last;
	int numFiles = 0;
	
	private class FileNode{
		public Object info;
		public FileNode next;
	}
	
	public FileList(){
		files = null;
		//last = files;
		//files = last;
	}
	
	public boolean isEmpty(){
		return files == null;
	}
	
	public void addFile(Object x){
		FileNode P = new FileNode();
		P.info = x;
		P.next = null;
		if(isEmpty()){
			insertFirst(x);
			numFiles = 1;
		}
		else {
			insertAfter(x);
			numFiles++;
		}
	}
	
	private void insertFirst(Object x){
		FileNode q = new FileNode();
		q.info = x;
		q.next = null;
		files = q;
		last = files;
	}
	
	private void insertAfter(Object x){
		FileNode q = new FileNode();
		q.info = x;
		q.next = null;
		last.next =q;
		last = q;
	}
	
	public String[] output(){
		FileNode P = new FileNode();
		String[] out= new String[numFiles];
		P = files;
		int count = 0;
		while(P!=null){
			out[count] = (String) P.info;
			P = P.next;
			count++;
		}
		return out;
	}
}
