package test;

public class IDLListTest {
	public static void main(String[] args) {
		
		//Testing the IDLList() Constructor
		System.out.println("Creating an Empty Double Linked List & printing.");
		IDLList<String> L = new IDLList<String>();
		System.out.println("Expected: Empty! ");
		System.out.println("Result: " + L + "\n"); 
		
		//Testing the add function (without index)
		System.out.println("Adding a, b, and c to the head list.");
		System.out.println("Adding a: " + L.add("a"));
		System.out.println("Adding b: " + L.add("b"));
		System.out.println("Adding c: " + L.add("c") + "\n");
		System.out.println("Printing out the list:");
		System.out.println("Expected: c => b => a");
		System.out.println("Result: " + L + "\n");
		
		//Testing the add function (with index)
		System.out.println("Adding d at index 2, e at 0, and ");
		System.out.println("Adding d: " + L.add(2, "d") + "\n");
		System.out.println("Adding e: " + L.add(0, "e") + "\n");
		System.out.println("Adding f: " + L.add(5, "f") + "\n");
		//Next line will print a NullPointerException because the index is out of bounds. 
		//Uncomment it to test.
		//System.out.println("Adding g: " + L.add(6, "g") + "\n");
		System.out.println("Printing out the list:");
		System.out.println("Expected: e => c => b => d => a => f");
		System.out.println("Result: " + L + "\n");
		
		//Testing the append function
		System.out.println("Appending h to the end of the list.");
		System.out.println("Adding g: " + L.append("g") + "\n");
		System.out.println("Printing out the list:");
		System.out.println("Expected:  e => c => b => d => a => f => g");
		System.out.println("Result: " + L + "\n");
		
		//Testing the get function
		System.out.println("Getting letter from index 0, 3, and 8 from the list.");
		System.out.println("Expected: e; Result: " + L.get(0));
		System.out.println("Expected: d; Result: " + L.get(3));
		System.out.println("Expected: null; Result: " + L.get(8));
		IDLList<String> L2 = new IDLList<String>();
		System.out.println("Getting a letter from an empty list.");
		System.out.println("Expected: null; Result: " + L2.get(2) + "\n");
		
		//Testing the getHead() function
		System.out.println("Retrieving head node from the list.");
		System.out.println("Expected: e");
		System.out.println("Result: " + L.getHead() + "\n");
		
		//Testing the getLast() function
		System.out.println("Retrieving tail node from the list.");
		System.out.println("Expected: g");
		System.out.println("Result: " + L.getLast() + "\n");
		
		//Testing the size() function
		System.out.println("Printing the size of the list.");
		System.out.println("Expected: 7");
		System.out.println("Result: " + L.size() + "\n");
		
		//Testing the remove() function
		System.out.println("Removing the head element.");
		System.out.println("Removing " + L.remove() + "...");
		System.out.println("Printing the new list:");
		System.out.println(L);
		System.out.println("New size of list: " + L.size() + "\n");
		//Following lines of code try to remove the head of an empty list.
		//Expected to return an IllegalStateException. Remove comments to test.
		//System.out.println(L2.remove());
		
		//Testing the removeLast() function
		System.out.println("Removing the last element.");
		System.out.println("Removing " + L.removeLast() + "...");
		System.out.println("Printing the new list:");
		System.out.println(L);
		System.out.println("New size of list: " + L.size() + "\n");
		//Following lines of code try to remove the tail of an empty list.
		//Expected to return an IllegalStateException. Remove comments to test.
		//System.out.println(L2.removeLast());
		
		//Testing the removeAt() function
		System.out.println("Removing the element at index 1 from the list.");
		System.out.println("Removing " + L.removeAt(1) + "...");
		System.out.println("Printing the new list:");
		System.out.println(L);
		System.out.println("New size of the list: " + L.size() + "\n");
		//Following lines of code try to remove the from index that doesn't exist.
		//Expected to return an IllegalStateException. Remove comments to test.
		//System.out.println(L.removeAt(9));
		
		//Testing the remove() function with elem parameter.
		System.out.println("Removing element d from the list.");
		System.out.println("Removing d: " + L.remove("d"));
		System.out.println("Printing the new list:");
		System.out.println(L);
		System.out.println("New size of the list: " + L.size() + "\n");
		System.out.println("Removing element x from the list.");
		System.out.println("Removing x: " + L.remove("x"));
		System.out.println(L);
		System.out.println("Size of the list: " + L.size() + "\n");
		
		
		
	}
}
