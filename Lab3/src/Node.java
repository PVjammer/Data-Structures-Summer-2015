/**
 * 
 * @author Nicholas
 *
 */
public class Node {
	private int info;
	private Node next;
	private Node right;
	
	
	public Node(){
		//info;
		next = null;
	}
	
	public Node(int x, Node n){
		info = x; 
		next = n;
	}
	
	public int getInfo(){
		return info;
	}
	
	public Node getnext(){
		return next ;
	}
	
	public Node setRight(){
		return right;
	}
	
	public void setInfo(int x){
		info = x;
	}
	
	public void setNext(Node x){
		next = x;
	}
	
	public void setRight(Node x){
		right = x;
	}
	
}
