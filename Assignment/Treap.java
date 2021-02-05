//Jack Schneiderhan
//CS 284-C
//I pledge my honor that I have adided by the Stevens Honor System.

package test;

import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {

	private static class Node<E>{
		E data;
		int priority;
		Node<E> left;
		Node<E> right;
		
		/**
		 * constructor for the node class. if data is empty, throws a nullpointerexception
		 * @param thisData "key" of the node
		 * @param thisPriority "priority" of the node
		 * @throws NullPointerException if the data is empty
		 */
		public Node(E thisData, int thisPriority) throws NullPointerException{
			if(thisData == null) {
				throw new NullPointerException();
			}
			else {
				data = thisData;
				priority = thisPriority;
				left = null;
				right = null;
			}
		}
		
		/**
		 * "rotates" the treap to the left
		 * @return the root of the rotated treap
		 */
		public Node<E> rotateRight(){
			Node<E> temp = new Node<E>(this.data, this.priority);
			if(this.left != null) {
				//creating a temp node to hold the third level pair
				if(this.right != null) {
					temp.right = this.right;
				}
				if(this.left.right != null) {
					temp.left = this.left.right;
				}
				
				//setting the root to the left node
				this.data = this.left.data;
				this.priority = this.left.priority;
				
				//adding temp to the right of the root
				this.right = temp;
				
				//setting the left to the left of the last left (confusing lol)
				if(this.left.left != null) {
					this.left = this.left.left;
				}
				else {
					this.left = null;
				}
			}
			return this;
		}
		
		/**
		 * "rotates" the treap to the right 
		 *  similar to the rotateLeft code, just swapped rights and lefts
		 * @return the head of the rotated treap
		 */
		public Node<E> rotateLeft(){
			Node<E> temp = new Node<E>(this.data, this.priority);
			if(this.right != null) {
				//creating a temp node to hold the third level pair
				if(this.left != null) {
					temp.left = this.left;
				}
				if(this.right.left != null) {
					temp.right = this.right.left;
				}
				
				//setting the root to the right node
				this.data = this.right.data;
				this.priority = this.right.priority;
				
				//adding the temp to the left of the root
				this.left = temp;
				
				//setting the right to the right of the last right (confusing lol)
				if(this.right.right != null) {
					this.right = this.right.right;
				}
				else {
					this.right = null;
				}
			}
			return this;
		}
	}
	
	private Random priorityGenerator;
	private Node<E> root;
	private boolean added;
	private boolean deleted;
	
	/**
	 * default constructor for the treap class. 
	 * sets root to null and creates a new random number generator
	 */
	public Treap() {
		root = null;
		priorityGenerator = new Random();
	}
	/**
	 * constructor for the treap class that takes in a seed for the random number generator
	 * @param seed seed to input to the random number generator
	 */
	public Treap(long seed) {
		root = null;
		priorityGenerator = new Random(seed);
	}
	
	/**
	 * method that adds a node with given key and random priority
	 * @param key key value for the node to be added
	 * @return true if the node is added, false if the key already exists
	 */
	public boolean add(E key) {
		int prior = priorityGenerator.nextInt(100);
		return add(key, prior);
	}
	
	/**
	 * method that adds a node with given key and given priority
	 * @param key key value for the node being added
	 * @param priority priority value for the node being added
	 * @return true if the node is added, false if not
	 */
	public boolean add(E key, int priority) {
		Node<E> new_node = new Node<E>(key, priority);
		if(find(key)) {
			System.out.println("ERROR: Key already exists! Treap will not be modified.");
			return false;
		}
		else if(root == null) {
			root = new_node;
			return true;
		}
		else {
			root = addHelper(root, new_node);
			return added;
		}
	}
	
	/**
	 * helper function for the add method
	 * @param thisRoot root of the treap
	 * @param new_node new node to be added to the treap
	 * @return root of the new treap 
	 */
	public Node<E> addHelper(Node<E> thisRoot, Node<E> new_node){
		//base case, if the root is empty then the new root is the new node
		if(thisRoot == null) {
			added = true;
			return new_node;
		}
		//is the new node key less than the current root?
		else if(new_node.data.compareTo(thisRoot.data) < 0) {
			thisRoot.left = addHelper(thisRoot.left, new_node);
			
			if (thisRoot.left.priority > thisRoot.priority) {
				thisRoot.rotateRight();
			}
			
		}
		//is the new node key greater than the current root?
		else if(new_node.data.compareTo(thisRoot.data) > 0 ) {
			thisRoot.right = addHelper(thisRoot.right, new_node);
			
			if(thisRoot.right.priority > thisRoot.priority) {
				thisRoot.rotateLeft();
			}
			
		}
		else {
			added = false;
		}
		
		return thisRoot;
	}
	
	
	/**
	 * deletes a node from the treap with a given key
	 * @param key key of the node to be deleted
	 * @return true if the node was found and deleted, false if not
	 */
	public boolean delete(E key) {
		if(!find(key)) {
			System.out.println("ERROR: Key doesn't exist. Treap will not be modified.");
			deleted = false;
		}
		else if(root == null) {
			deleted = false;
		}
		else {
			root = deleteHelper(root, key);
			deleted = true;
		}
		
		return deleted;
	}
	
	public Node<E> deleteHelper(Node<E> thisRoot, E key){
		//base case, if the root is null then nothing can be deleted
		if(thisRoot == null) {
			deleted = false;
			return null;
		}
		//if the key is found within the treap
		else if(key == thisRoot.data) {
			//no child at all?
			if(thisRoot.left == null && thisRoot.right == null) {
				return null;
			}
			//no right child?
			else if (thisRoot.right == null) {
				thisRoot.rotateRight();
				thisRoot.right = deleteHelper(thisRoot.right, key);
			}
			//no left child?
			else if(thisRoot.left == null) {
				thisRoot.rotateLeft();
				thisRoot.left = deleteHelper(thisRoot.left, key);
			}
			else {
				//if the left child has a higher # than the right
				if(thisRoot.left.priority > thisRoot.right.priority) {
					thisRoot.rotateLeft();
					thisRoot.left = deleteHelper(thisRoot.left, key);
				}
				//if the right child has a higher # than the left
				else {
					thisRoot.rotateRight();
					thisRoot.right = deleteHelper(thisRoot.right, key);
				}
			}
			deleted = true;
		}
		//key isn't found, keep going down the treap
		else if(key.compareTo(thisRoot.data) < 0) {
			thisRoot.left = deleteHelper(thisRoot.left, key);
		}
		else if(key.compareTo(thisRoot.data) > 0) {
			thisRoot.right = deleteHelper(thisRoot.right, key);
		}
		
		return thisRoot;

	}
	
	/**
	 * finds a node with given key at the specified root
	 * @param root root to be searched from
	 * @param key the key of the node to be found
	 * @return true if the key exists, false if not
	 */
	private boolean find(Node<E> root, E key) {
		boolean isFound = false;
		if(root.data == key) {
			return true;
		}
		if(root.left != null) {
			isFound = find(root.left, key);
		}
		if(root.right != null && !isFound) {
			isFound = find(root.right, key);
		}
		
		return isFound;
	}
	
	/**
	 * finds a node with a given key
	 * @param key key of the node to be found
	 * @return true if the key exists, false if not
	 */
	public boolean find(E key) {
		boolean isFound = false;
		if(key == null) {
			return false;
		}
		if(root == null) {
			return false;
		}
		if(root.data == key) {
			return true;
		}
		if(root.left != null) {
			isFound = find(root.left, key);
		}
		if(root.right != null && !isFound) {
			isFound = find(root.right, key);
		}
		
		return isFound;
	}
	
	/**
	 * returns a formatted list of the treap.
	 */
	public String toString() {
		return toStringHelper(root, 0);
	}
	
	/**
	 * helper for the toString method
	 * @param root root of the treap
	 * @param spaces number of spaces to be added (for formatting)
	 * @return formatted list of the treap
	 */
	public String toStringHelper(Node<E> root, int spaces) {
		String returnString = "";
		for(int i = 0; i < spaces; i++) {
			returnString += "   ";
		}
		if(root == null){
			returnString += "null\n";
		}
		else {
			returnString += "(key=" + root.data + ", priority=" + root.priority + ")\n";
			returnString += toStringHelper(root.left, spaces + 1);
			returnString += toStringHelper(root.right, spaces + 1);
		}
		return returnString;
	}
	
	
}
