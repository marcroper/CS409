package LnkdLst;

public class LinkedList
{
	private Node head;
	private int listCount;

	public LinkedList() {
		head = new Node(null);
		listCount = 0;
	}
	
//	public void add(String data) {
//		Node temp = new Node(data);
//		Node current = head;
//		while(current.getNext() != null){
//			current = current.getNext();
//		}
//		current.setNext(temp);
//		listCount++;
//	}
	
	public void add(String data) {
		Node temp = new Node(data);
		Node current = head;
		while(current.next != null){
			current = current.next;
		}
		current.next = temp;
		listCount++;
	}
	
//	public void add(String data, int index)
//	// post: inserts the specified element at the specified position in this list.
//	{
//		Node temp = new Node(data);
//		Node current = head;
//		// crawl to the requested index or the last element in the list,
//		// whichever comes first
//		for(int i = 1; i < index && current.getNext() != null; i++)
//		{
//			current = current.getNext();
//		}
//		// set the new node's next-node reference to this node's next-node reference
//		temp.setNext(current.getNext());
//		// now set this node's next-node reference to the new node
//		current.setNext(temp);
//		listCount++;// increment the number of elements variable
//	}
	
//	public String get(int index)
//	// post: returns the element at the specified position in this list.
//	{
//		// index must be 1 or higher
//		if(index <= 0)
//			return null;
//		
//		Node current = head.getNext();
//		for(int i = 1; i < index; i++)
//		{
//			if(current.getNext() == null)
//				return null;
//			
//			current = current.getNext();
//		}
//		return current.getData();
//	}
	
//	public boolean remove(int index)
//	// post: removes the element at the specified position in this list.
//	{
//		// if the index is out of range, exit
//		if(index < 1 || index > size())
//			return false;
//		
//		Node current = head;
//		for(int i = 1; i < index; i++)
//		{
//			if(current.getNext() == null)
//				return false;
//			
//			current = current.getNext();
//		}
//		current.setNext(current.getNext().getNext());
//		listCount--; // decrement the number of elements variable
//		return true;
//	}
	
//	public int size()
//	// post: returns the number of elements in this list.
//	{
//		return listCount;
//	}
	
//	public String toString()
//	{
//		Node current = head.getNext();
//		String output = "";
//		while(current != null)
//		{
//			output += "[" + current.getData().toString() + "]";
//			current = current.getNext();
//		}
//		return output;
//	}
	
	public String toString()
	{
		Node current = head.next;
		String output = "";
		while(current != null)
		{
			output += "[" + current.data.toString() + "]";
			current = current.next;
		}
		return output;
	}
	
	private class Node{
		Node next;
		String data;
		
		public Node(String _data) {
			next = null;
			data = _data;
		}
//				// specify the node to point to.
//		public Node(String _data, Node _next){
//			next = _next;
//			data = _data;
//		}
//		
//		public String getData() {
//			return data;
//		}
//		
//		public void setData(String _data){
//			data = _data;
//		}
//		
//		public Node getNext() {
//			return next;
//		}
//		
//		public void setNext(Node _next){
//			next = _next;
//		}
	}
	
	public static void main (String [] args) { 
		String[] myData = {"Pugh", "Pugh", "Barney McGrew", "Cuthbert", "Dibble", "Grub"};
		LinkedList LL = new LinkedList();
		for (String each: myData) {
			LL.add(each); 
		}
		System.out.println(LL.toString());	
	}
}

