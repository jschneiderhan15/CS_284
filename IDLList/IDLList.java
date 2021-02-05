package test;

import java.util.ArrayList;

public class IDLList<E> {
	
	private class Node<E>{
		private E data;
		private Node<E> next;
		private Node<E> prev;
		
		/** creates a new node with a null next field.
		@param elem the data stored in the node
		*/
		Node (E elem){
			data = elem;
			next = null;
			prev = null;
		}
		
		/** creates a node holding elem, with nextNode as next and prevNode as prev.
		 * @param elem the data stored in the node
		 * @param prevNode the node previous to this node
		 * @param nextNode the node next after this node
		 */
		Node(E elem, Node<E> prevNode, Node<E> nextNode){
			data = elem;
			next = nextNode;
			prev = prevNode;
		}
		
		/** returns the next node after this node.
		 * @return node in the "next" position
		 */
		public Node<E> getNext() {
			return next;
		}
		
		/** returns the data in this node.
		 * @return data contained within the node
		 */
		public E getData() {
			return data;
		}
	}
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	/** constructor that creates an empty double linked list.
	 */
	IDLList() {
		head = null;
		tail = null;
		size = 0;
		indices = new ArrayList<Node<E>>();
	}
	
	/** adds elem at position index (Uses the index for fast access)
	 * @param index where the element is added to the list
	 * @param elem element to be added to the list
	 * @return true if can be added, false if not
	 */
	public boolean add (int index, E elem) throws NullPointerException{
		Node<E> new_node = new Node<E>(elem);
		Node<E> point = head;
		if (index > size) {
			throw new NullPointerException();
		}
		else if(index == 0) {
			this.add(elem);
		}
		else {
			for(int i = 1; i < index; i++) {
				point = point.next;
			}
			new_node.next = point.next;
			point.next = new_node;
			indices.add(index, new_node);
			size++;
		}
		return true;
	}
	
	/** adds elem to the head of the list.
	 * @param elem element to be added to the list
	 * @return true, always
	 */
	public boolean add (E elem) {
		Node<E> new_node = new Node<E>(elem);
		if(head == null) {
			head = new_node;
			size++;
			indices.add(new_node);
		}
		else {
			 new_node.next = head; 
			 new_node.prev = null; 
			 head.prev = new_node;
			 head = new_node;
			 size++;
			 indices.add(0, new_node);
		}
		return true;
	}
	
	/** adds elem as the new last element of the list
	 * @param elem element to be added to the list
	 * @return true, always
	 */
	public boolean append(E elem) {
		Node<E> new_node = new Node<E>(elem);
		Node<E> point = head;
		if(point == null) {
			head = new_node;
			size++;
			indices.add(new_node);
		}
		else {
			while(point.next != null) {
				point = point.next;
			}
			point.next = new_node;
			new_node.prev = point;
			tail = new_node;
			size++;
			indices.add(new_node);
		}
		return true;
	}
	
	/** returns the object at position index from the head.
	 *  uses the index for fast access.
	 *  note: indexing starts from 0
	 * @param index where the element is being pulled from the list
	 * @return object at position index 
	 */
	public E get (int index) {
		Node<E> point = head;
		if(head == null || index >= size) {
			return null;
		}
		else if(index == 0) {
			return point.getData();
		}
		else {
			for(int i = 0; i < index; i++) {
				point = point.next;
			}
		}
		return point.getData();
		
	}
	
	/** returns the object at the head
	 * @return object at head of list
	 */
	public E getHead() {
		return head.getData();
	}
	
	/** returns the object at the tail
	 * @return object at tail of list
	 */
	public E getLast() {
		return tail.getData();
	}
	
	/** returns the list size
	 * @return the size of the list
	 */
	public int size() {
		return size;
	}
	
	/** removes and returns the element at the head.
	 *  throws an IllegalStateException if there is no such element.
	 * @return the head element
	 */
	public E remove() throws IllegalStateException{
		Node<E> point = head;
		if(head == null) {
			throw new IllegalStateException();
		}
		else {
			head = head.next;
			size--;
			indices.remove(0);
		}
		return point.getData();
		
	}
	
	/** removes and returns the element at the last.
	 *  throws an IllegalStateException if there is no such element.
	 * @return the last element
	 */
	public E removeLast() throws IllegalStateException{
		Node<E> point = tail;
		Node<E> temp = tail;
		if(head == null) {
			throw new IllegalStateException();
		}
		else {
			while(point.next != null) {
				point = point.next;
			}
			point.prev.next = null;
		}
		size--;
		indices.remove(size - 1);
		return temp.getData();
	}
	
	/** removes and returns the element at given index.
	 *  throws an IllegalStateException if there is no such element.
	 * @param index where the element is being removed from the list
	 * @return element being removed
	 */
	public E removeAt(int index) throws IllegalStateException{
		Node<E> point = head;
		Node<E> save;
		if(index == 0) {
			save = new Node<E>(this.remove());
		}
		else if(index >= size || head == null) {
			throw new IllegalStateException();
		}
		else {
			for(int i = 0; i < index; i++) {
				point = point.next;
			}
			save = point;
				if(point.prev != null)
					point.prev.next = point.next;
				if(point.next != null)
					point.next.prev = point.prev;
			size--;
			indices.remove(index - 1);
		}
		return save.getData();
	}
	
	/** removes the first occurence of elem in the list.
	 * @param elem element to be removed from the list
	 * @return true if elem is in list, false if not
	 */
	public boolean remove (E elem) {
		Node<E> point = head;
		if(head.getData() == elem) {
			this.remove();
			return true;
		}
		else {
			for (int i = 0; i < size - 1; i++) {
				point = point.next;
				if(point.getData() == elem) {
					break;
				}
				
			}
			if(point.getData() != elem && point.getNext() == null) {
				return false;
			}
			if(point.prev != null)
				point.prev.next = point.next;
			if(point.next != null)
				point.next.prev = point.prev;
			size--;
			indices.remove(elem);
			return true;
		}
		
}
	
	/** presents a string representation of the list.
	 * @return the list in string form
	 */
	public String toString() {
		String retStr = "";
		
		if(head == null) {
			retStr = "Empty!";
		}
		else {
	        Node<E> current = head;
	        while(current.next != null){
	            retStr += current.getData() + " => ";
	            current = current.getNext();
	        }
	        retStr += current.getData();
		}
        return retStr;
	}
}
