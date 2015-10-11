public class LinkedList {
	Node files;
	Node last;
	int numFiles = 0;
	
	private class Node{
		public Object info;
		public Node next;
	}
	
	public LinkedList(){
		files = null;
		//last = files;
		//files = last;
	}
	
	public boolean isEmpty(){
		return files == null;
	}
	
	public void addFile(Object x){
		Node P = new Node();
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
		Node q = new Node();
		q.info = x;
		q.next = null;
		files = q;
		last = files;
	}
	
	private void insertAfter(Object x){
		Node q = new Node();
		q.info = x;
		q.next = null;
		last.next =q;
		last = q;
	}
	
	public int[] output(){
		Node P = new Node();
		int[] out= new int[numFiles];
		P = files;
		int count = 0;
		while(P!=null){
			out[count] = (int) P.info;
			P = P.next;
			count++;
		}
		return out;
	}
}
