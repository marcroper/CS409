package apStack;

public interface StackADT <E> {
	public void push(E element);
	
	public E pop() throws EmptyStackException;
	
	public E top() throws EmptyStackException;
	
	public int size();
	
	public boolean isEmpty();
}
