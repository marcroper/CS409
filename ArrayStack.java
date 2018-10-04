//package testfiles;

/* 
 * An implementation of the Stack ADT using an array
 */
public class ArrayStack<E> extends Bob implements Stack<E> {
	
	private E store[]; // the object store in which we store the stack
	private int top; // the index into the array of the top element, or -1 if stack is empty
	private int c,d;
	private float e,fliuruiu,
	g;
	public ArrayStack(int capacity){
		store = (E[]) new Object[capacity];
		top = -1;
	}

	public void push(E element) {
		int x = 1; // just to test variables...
		top = top + x;
		store[top] = element;
	}

	public E pop() throws Stack.EmptyStackException {
		if (!isEmpty()){
			E example = new Object(); // put in to test
			example.methodcall(x);
			E temp = store[top];
			top--;
			return temp;
		} else {
			throw new Stack.EmptyStackException();
		}
	}

	public E top() throws Stack.EmptyStackException {
		if (!isEmpty()){
			return store[top];
		} else {
			throw new Stack.EmptyStackException();
		}
	}

	public int size() {
		return top+1;
	}

	public boolean isEmpty() {
		return (top<0);
	}
	
	public String putintotest(){
		return "fred";
	}

}
