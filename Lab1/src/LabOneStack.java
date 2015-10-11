/*
 * A custom stack built for the processing of characters.  The stack 
 * utilizes and array of characters to store the data and has the 3
 * standard methods push(char c), pop(), and isEmpty(), as well as a custom
 * method for this stack extendStack(), which doubles the size of the stack
 * if capacity is reached.
 */
public class LabOneStack {
	/*
	 * Class Variables
	 */
	public static int DEFAULT_SIZE = 10;	// The stack size if the default constructor is used
	char[] items;							// The array which holds the stack data (type char)
	int top = -1;							// int which points to the top of the stack
	char[] itemsOverflow;					// Array used as temporary storage when 
											//    extending the stack
	int stacksize;							// The current size ot the stack
	
	/*
	 * LabOneStack Methods
	 * 
	 * public LabOneStack()
	 * 
	 * public LabOneStack(int arraysize)
	 * 
	 * public void push(char item)
	 * 
	 * public char pop()
	 * 
	 * public boolean isEmpty()
	 * 
	 * private void extendStack()
	 */
	public LabOneStack(){
		/*
		 * Default constructor for the labOneStack.  Initializes
		 * the stack to a size of 10 and creates a second array of
		 * size 20 for temporary storage if the stack needs to be
		 * extended. 
		 */
		items = new char[DEFAULT_SIZE];
		itemsOverflow = new char[DEFAULT_SIZE*2];
		stacksize = DEFAULT_SIZE;
	}
	
	public LabOneStack(int arraysize){
		/*
		 * Alternate constructor for the labOneStack.  Initializes
		 * the stack to a size of "arraySize" and creates a second array of
		 * size "arraySize"*2 for temporary storage if the stack needs to be
		 * extended. 
		 */
		
		// Check for valid stack size argument
		if (arraysize >0){
			items = new char[arraysize];
			itemsOverflow = new char[arraysize*2];
			stacksize = arraysize;
		}
		else System.out.println("Stack not created, size must be greater than 0");
	}
	
	public void push(char item){
		/*
		 * Push method which accepts a single character and adds it to the top
		 * of the stack.  Extends the stack size if the stack is full. 
		 */
		if (top < stacksize-1){
			top++;
			items[top] = item;
		}
		else{
			this.extendStack();
			top++;
			items[top] = item;
		}		
	}
	
	public char pop(){
		/*
		 * Pop method which takes no parameters and removes and returns
		 * the item a the top of the stack.  Gives an error message and aborts
		 * the program if an attempt is made to pop from an empty stack.
		 */		
		char item;
		if(!isEmpty()){
			item = items[top];
			top--;
			return item;
		}			
		else{
			System.out.println("Stack underflow error: Can't pop from an empty stack!");
			System.exit(1);
			return '@';
		}
	}
	
	private void extendStack(){
		/*
		 * ExtendStack method accepts no parameters and doubles the size of 
		 * the current stack so that more characters can be accepted.  It
		 * returns no values.
		 */	
		for(int i = 0; i<items.length;i++){
			itemsOverflow[i] = items[i];
		}
		this.items = itemsOverflow;
		stacksize = items.length;
		itemsOverflow = new char[stacksize*2];
	}
	
	public boolean isEmpty(){
		/*
		 * IsEmpty method accepts no parameters and returns "true" if the 
		 * stack is empty and "false" if there are items in the stack.
		 */	
		if (top<0) return true;
		else return false;		
	}
}
