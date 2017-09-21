package apStack;

public class StackAppn {

	public static <E> void reverse(E[] a) { 
		StackADT<E> S = new ArrayStack<E> (a.length); 
			for (int i=0; i < a.length; i++) 
				S.push(a[i]); 
			for (int i=0; i < a.length; i++) a[i] = 
				S.pop(); 
		} 	
	
	public static void main (String [] args) { 
		// Test 1. Output should be in the order:
		// Pugh Pugh Barney McGrew Cuthbert Dibble Grub
		System.out.println("Test 1:"); 
		String[] myArray = {"Grub", "Dibble", "Cuthbert", "Barney McGrew", "Pugh", "Pugh"}; 
		reverse(myArray); 
		for (String each: myArray) {
			System.out.print(each); 
			System.out.print(" ");
		}
		System.out.println();
		
		System.out.println("Test 2:");
		
	
		StackADT<String> S1 = new ArrayStack<String> (3);
		S1.push("a");
		S1.push("b");
		S1.push("c");
		System.out.println("Top:"+S1.top());
//		S1.push("d");
		System.out.println("Test 3:");
		StackADT<String> S2 = new ArrayStack<String> (2);
		S2.push("a");
		S2.push("a");
		S2.pop();
		System.out.println("Top:"+S2.top());
		/*
		 * if (match("((()))"))
			System.out.println("((())) Correct - brackets match");
		else
			System.out.println("((())) INCORRECT! - brackets should match");
		if (match("()()()"))
			System.out.println("()()() Correct - brackets match");
		else
			System.out.println("()()() INCORRECT! - brackets should match");
		if (match("((())"))
			System.out.println("((()) INCORRECT! - brackets should not match");
		else
			System.out.println("((()) Correct - brackets do no match");
		if (match("(()))"))
			System.out.println("(())) INCORRECT! - brackets should not match");
		else
			System.out.println("(())) Correct - brackets do no match");
			*/
	}
		
	private static boolean match(String str) {
		// Algorithm:
		// Creat a stack instance parameterised to type String
		StackADT<String> S = new ArrayStack<String> (str.length());
		// for each character in the input string
		//    if the character is an opening bracket
		//       push a "(" onto the stack
		//    if the character is a closing bracket
		//       if the stack is empty
		//          return false // there is no matching opening bracket
		//       else
		//          pop an element from the stack
		// if the stack is not empty
		//    return false  // there have not been enough closing brackets
		// else
		//    return true  // all brackets match
		/* Solution to Q1b
		for(int i = 0; i < str.length(); i++){
			if (str.charAt(i)=='(')
				S.push("(");
			if (str.charAt(i)==')')
				if (S.isEmpty())
					return false; 
				else
					S.pop();
		}
		if (!S.isEmpty())
			return false;
		else */
			return true;

	}
} 


