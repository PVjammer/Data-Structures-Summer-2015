/*
 * Translate:
 * 
 * This class houses the various methods used to translate the language
 * strings passed in to the public method: calcLang(Strings, int size).
 * By housing all the methods in this singular class file new languages
 * can be added to the class without requiring changes to the main 
 * program.
 */
public class Translate {
	
	private static int numLang = 6;		// The number of languages translated
	
	public static int getnumLang(){
		/*
		 * A method which returns the number of languages processed by
		 * the class (i.e. number of language translation methods.
		 * 
		 * parameters:
		 * None
		 * 
		 * output: 
		 * numLang - Returns the class variable "numLang" 
		 */
		return numLang;
	}
		
	
	public static int[] calcLang (String s, int size){
		/* This method calls the language translation methods and
		 * compiles the resulting output into a single array which is 
		 * returned.
		 * 
		 * Parameters:  
		 * String s - The language string to be interpreted by 
		 * the method
		 * 
		 * int size;
		 * 
		 * Output: The method returns a 1 if s is an example of the
		 * language, and a 0 if s is not an example of the language.
		 */
		int[] result = new int[size];
		// Load the result array with the output of the language
		// translation methods.
		result[0] = lang1(s);
		result[1] = lang2(s);
		result[2] = lang3(s);
		result[3] = lang4(s);
		result[4] = lang5(s);
		result[5] = lang6(s);
		return result;
	} // End calcLang method
	
	private static int lang1(String s){
		/* This method checks to see if the input string "s" 
		 * is in fact an example of language 5, which is defined
		 * as such:
		 * 
		 * L1 = a string of only A's and B's with equal numbers 
		 * of A's and B's.
		 * 
		 * Parameters:  
		 * String s - The language string to be interpreted by 
		 * the method
		 * 
		 * Output: The method returns a 1 if s is an example of the
		 * language, and a 0 if s is not an example of the language.
		 */
		LabOneStack s1 = new LabOneStack();
		LabOneStack s2 = new LabOneStack();
		char letter;
		
		if (s.length() == 0)
			return 1;
		
		for (int i = 0; i<s.length(); i++){
			letter = s.charAt(i);
			if (letter == 'A'){
				s1.push(letter);
			}
			else if (letter == 'B'){
				s2.push(letter);
			}
			else {
				//ystem.out.println("Invalid Letter for language 1");
				return 0;
			}
		}
		
		while (s1.isEmpty()==false && s2.isEmpty()==false){
			s1.pop();
			s2.pop();
		}
		
		if (s1.isEmpty()==true && s2.isEmpty()==true)
			return 1;
		else
			return 0;
	} // End lang1 method
	
	private static int lang2(String s){
		/* This method checks to see if the input string "s" 
		 * is in fact an example of language 5, which is defined
		 * as such:
		 * 
		 * L2 = (A^nB^n) where n is the number of sequential A's, and
		 * n is the number of sequential B's.  The empty string is a
		 * valid example of this language.
		 * 
		 * Parameters:  
		 * String s - The language string to be interpreted by 
		 * the method
		 * 
		 * Output: The method returns a 1 if s is an example of the
		 * language, and a 0 if s is not an example of the language.
		 */
		LabOneStack s1 = new LabOneStack();	//  Stack used to hold string characters ('A')
		LabOneStack s2 = new LabOneStack();	//  Stack used to hold string characters ('B')
		
		char letter;
		int count = 0;
		
		if (s.length() == 0)
			return 1;
		
		letter = s.charAt(count);
		while(letter == 'A'&& count < s.length()){
			s1.push(letter);
			count++;
			if(count<s.length())
				letter = s.charAt(count);
		}
		
		while (letter == 'B' && count < s.length()){
			s2.push(letter);
			count++;
			if(count<s.length())
				letter = s.charAt(count);
		}
		
		// Return a 0 if there are still letters in the string
		
		if (count<s.length())
			return 0;
		
		while (s1.isEmpty()==false && s2.isEmpty()==false){
			s1.pop();
			s2.pop();
		}
		
		if (s1.isEmpty()==true && s2.isEmpty()==true)
			return 1;
		else
			return 0;
	} // End lang2 method
	
	private static int lang3(String s){
		/* This method checks to see if the input string "s" 
		 * is in fact an example of language 5, which is defined
		 * as such:
		 * 
		 * L3 = (A^nB^2n) where n is the number of sequential A's, and
		 * 2n is the number of sequential B's.  The empty string is a
		 * valid example of this language.
		 * 
		 * Parameters:  
		 * String s - The language string to be interpreted by 
		 * the method
		 * 
		 * Output: The method returns a 1 if s is an example of the
		 * language, and a 0 if s is not an example of the language.
		 */
		LabOneStack s1 = new LabOneStack();	//  Stack used to hold string characters ('A')
		LabOneStack s2 = new LabOneStack();	//  Stack used to hold string characters ('B')
		
		char letter;
		int count = 0;
		int popCount = 0;
		
		if (s.length() == 0)
			return 1;
		
		letter = s.charAt(count);
		while(letter == 'A'&& count < s.length()){
			s1.push(letter);
			count++;
			if(count<s.length())
				letter = s.charAt(count);
		}
		
		while (letter == 'B' && count < s.length()){
			s2.push(letter);
			count++;
			if(count<s.length())
				letter = s.charAt(count);
		}
		
		// Return a 0 if there are still letters in th string
		
		if (count<s.length())
			return 0;
		
		while (s1.isEmpty()==false){
			s1.pop();
			popCount++;
		}
		
		for (int i=0;i<popCount*2;i++){
			if(s2.isEmpty()==true)
				return 0;
			else
				s2.pop();
		}
		
		if (s1.isEmpty()==true && s2.isEmpty()==true)
			return 1;
		else
			return 0;
	} // End lang3 method

	private static int lang4(String s){
		/* This method checks to see if the input string "s" 
		 * is in fact an example of language 5, which is defined
		 * as such:
		 * 
		 * L4 = (A^nB^m)p where n is the number of sequential A's,
		 * m is the number of sequential B's, and p is the number of
		 * times the string of A's and B's repeats.  The empty string,
		 * a string of only A's and a string of only B's are all valid
		 * examples of this language.
		 * 
		 * Parameters:  
		 * String s - The language string to be interpreted by 
		 * the method
		 * 
		 * Output: The method returns a 1 if s is an example of the
		 * language, and a 0 if s is not an example of the language. 
		 * 
		 */
		LabOneStack s1 = new LabOneStack();	//  Stack used to hold string characters ('A')
		LabOneStack s2 = new LabOneStack();	//  Stack used to hold string characters ('B')
		char letter;
		int count = 0;
		
		// Check for the empty string
		if (s.length() == 0)
			return 1;
		
		//pull the first letter from the string
		letter = s.charAt(count);
		
		// Push "A's" onto the stack until a "B" is encountered or the string ends
		while(letter == 'A'&& count < s.length()){
			s1.push(letter);
			count++;
			if(count<s.length())
				letter = s.charAt(count);
		}
		
		// Push "B's" onto the stack until a different letter is encountered or the string ends
		while(letter == 'B'&& count < s.length()){
			s1.push(letter);
			count++;
			if(count<s.length())
				letter = s.charAt(count);
		}		
		
		while (count< s.length()){
			
			while(s1.isEmpty()==false)
				s2.push(s1.pop());
			
			// Push "A's" onto the stack until a "B" is encountered or the string ends
			if(letter!='A'&&letter!='B')
				return 0;
			while(letter == 'A'&& count < s.length()){
				if(s2.isEmpty()==true)
					return 0;
				else if(s2.pop()==letter){
					s1.push(letter);
					count++;
					if(count<s.length())
						letter = s.charAt(count);
				}
				
			}
			
			// Push "B's" onto the stack until a different letter is encountered or the string ends
			while(letter == 'B'&& count < s.length()){
				if(s2.isEmpty()==true)
					return 0;
				else if(s2.pop()==letter){
					s1.push(letter);
					count++;
					if(count<s.length())
						letter = s.charAt(count);
				}
			}	
		}
			
		if (s2.isEmpty()==false)
			return 0;
		else
			return 1;
	}
	
	private static int lang5(String s){
		/* This method checks to see if the input string "s" 
		 * is in fact an example of language 5, which is defined
		 * as such:
		 * 
		 * L5 = xCy where x is a string of
		 * A's and B's and y is that same string in reverse, while
		 * "C" represents those character "C".  This language
		 * was first introduced in homework #2.  The empty string
		 * does not satisfy this language.
		 * 
		 * Parameters:  
		 * String s - The language string to be interpreted by 
		 * the method
		 * 
		 * Output: The method returns a 1 if s is an example of the
		 * language, and a 0 if s is not an example of the language. 
		 * 
		 */
		LabOneStack s1 = new LabOneStack();
		char letter;
		int count = 0;
		if (s.length() == 0)
			return 0;
		
		letter = s.charAt(count);
		while(letter != 'C'){
			if (letter != 'A'&& letter !='B'&& letter != 'C')
				return 0;
			if(letter != 'A' && letter != 'B')
				return 0;
			s1.push(letter);
			count++;
			if(count<s.length())
				letter = s.charAt(count);
			else
				return 0;
		}
		count++;
		while (count<s.length()){
			letter = s.charAt(count);
			if(s1.isEmpty()==true)
				return 0;
			if(letter!=s1.pop())
				return 0;
			count++;
		}
		if(s1.isEmpty()==false)
			return 0;
		else
			return 1;
	
		
	}
	
	private static int lang6(String s){
		/* This method checks to see if the input string "s" 
		 * is in fact an example of language 6, which is defined
		 * as such:
		 * 
		 * L6 = (x1Cy1)D(x2Cy2)...(xnCyn) where xn is a string of
		 * A's and B's and yn is that same string in reverse, while
		 * "C" and "D" represent those characters.  This language
		 * was first introduced in homework #2.  The empty string
		 * does not satisfy this language.
		 * 
		 * Parameters:  
		 * String s - The language string to be interpreted by 
		 * the method
		 * 
		 * Output: The method returns a 1 if s is an example of the
		 * language, and a 0 if s is not an example of the language. 
		 * 
		 */
		LabOneStack s1 = new LabOneStack();
		char letter;
		String substr = "";
		int count = 0;
		int valid = 0;
		
		for (int i = 0; i < s.length()-1;i++){
			letter =s.charAt(i);
			if (letter == 'D'){
				while(!s1.isEmpty())
					substr += s1.pop();
				valid = lang5(substr);
				substr = "";
				if(valid ==0)
					return valid;
			}
			else if (letter == 'A'||letter == 'B'||letter == 'C')
				s1.push(letter);
			else return 0;
		}
		
		return valid;
	}
}
