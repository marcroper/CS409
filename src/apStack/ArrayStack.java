package apStack;

/*
public class ArrayStack <E> implements StackADT <E> {
	// holds the stack elements
	protected E S[ ];
	// index to top element
	protected int top = -1;
	// constructor
	public ArrayStack(int cap) {
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
*/

public class ArrayStack<E> implements StackADT<E> {

    // the object store in which we store the stack
    private E store[];

    // the index into the array of the top element, or -1 if stack is empty
    private int top;

    public ArrayStack(int capacity) {
        System.out.print("ArrayStack");
        System.out.println(this.objectId);
        objectId++;
        {
            store = (E[]) new Object[capacity];
            top = -1;
        }
    }

    public void push(E element) {
        // just to test variables...
        int x = 1;
        top = top + x;
        store[top] = element;
    }

    public E pop() throws EmptyStackException {
        if (!isEmpty()) {
            // put in to test
            E temp = store[top];
            top--;
            return temp;
        } else {
            throw new EmptyStackException("E");
        }
    }

    public E top() throws EmptyStackException {
        if (!isEmpty()) {
            return store[top];
        } else {
            throw new EmptyStackException("E");
        }
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return (top < 0);
    }

    static int objectId;
}



