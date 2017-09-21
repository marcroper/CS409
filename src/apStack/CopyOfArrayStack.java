package apStack;

public class CopyOfArrayStack <E> implements StackADT <E> {
	// holds the stack elements
	protected E S[ ];
	// index to top element
	protected int top = -1;
	// constructor
	public CopyOfArrayStack(int cap) {
		// compiler may give warning, but this is OK
		S = (E[]) new Object[cap];
	}
	
	public int size () {
		return (top +1);
	}
	
	public boolean isEmpty() {
		return (top <0);
	}

	public E pop() throws EmptyStackException {
		E element;
		if (isEmpty())
			throw new EmptyStackException("Empty stack: cannot pop");
		element = S[top];
		// to facilitate garbage collection
		S[top] = null;
		top = top - 1;
		return element;
	}
	
	public E top() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException("Stack is empty. Cannot obtain an element");
		return S[top];
	}
	
	public void push(E element){
		top++;
		S[top]=element;
	}
}
