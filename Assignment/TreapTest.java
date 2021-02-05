//Jack Schneiderhan
//CS 284-C
//I pledge my honor that I have adided by the Stevens Honor System.

package test;

public class TreapTest {
	public static void main(String[] args) {
		//creating a treap
		Treap<Integer> testTree = new Treap<Integer>();
		
		//testing the add methods
		testTree.add(4, 19);
		testTree.add(2, 31);
		testTree.add(6, 70);
		testTree.add(1, 84);
		testTree.add(3, 12);
		testTree.add(5, 83);
		testTree.add(7, 26);
		testTree.add(5, 30); //should return an error, key already exists
		testTree.add(10);
		System.out.println(testTree);
		
		//testing the find method
		System.out.println(testTree.find(10)); //should return true
		System.out.println(testTree.find(15)); //should return false;
		
		//testing the delete method
		testTree.delete(4); 
		testTree.delete(24); //should return an error, key does not exist
		
		System.out.println(testTree);
	}
}
