//Jack Schneiderhan
//CS 284-C
//I pledge my honor that I have adided by the Stevens Honor System.

package test;

public class IsoTriangle {
	
	private class Node{
		
		int value;
		
		Node l_child;
		Node r_child;
		
		Integer depth;
		
		private Node(int thisValue, Node left_child, Node right_child) {
			value = thisValue;
			l_child = left_child;
			r_child = right_child;
		}
		
		private Node(int thisValue, Node left_child, Node right_child, int thisDepth) {
			value = thisValue;
			l_child = left_child;
			r_child = right_child;
			depth = thisDepth;
		}
		
		private void set_depth(int thisDepth) {
			depth = thisDepth;
		}
		
		private int get_depth() {
			return depth;
		}
		
		private void set_value(int new_value) {
			value = new_value;
		}
	}
	
	/*
	 * builds a test case tree
	 */
	public Node build_tree_int() {
		Node three = new Node(3, null, null, 3);
		Node four = new Node(4, null, null, 3);
		
		Node seven = new Node(7, three, four, 2);
		Node five = new Node(5, null, null, 3);
	
		Node six = new Node(6, null, null, 3);
		Node two = new Node(2, five, six, 2);
		
		Node one = new Node(0, seven, two, 1);
		
		return one;
	}
	
	/*
	 * builds a second test case tree
	 */
	public Node build_tree_int2() {
		Node twelve = new Node(12, null, null, 7);
		
		Node eleven = new Node(11, null, twelve, 6);
		
		Node ten = new Node(10, null, eleven, 5);
		Node nine = new Node(9, null, null, 5);
		
		Node seven = new Node(7, nine, ten, 4);
		Node eight = new Node(8, null, null, 4);
		
		Node three = new Node(3, seven, eight, 3);
		Node four = new Node(4, null, null, 3);
		Node five = new Node(5, null, null, 3);
		Node six = new Node(6, null, null, 3);
		
		Node one = new Node(1, three, four, 2);
		Node two = new Node(2, five, six, 2);
		
		Node zero = new Node(0, one, two, 1);
		
		return zero;
	}
	
	int total_iso_triangle = 0;
	/**
	 * 4 step process:
	 * 1) check if the root is null, if so break
	 * 2) traverse down the left side of the tree, counting triangles
	 * 3) traverse down the right side of the tree, counting triangles
	 * 4) once both sides are traversed, call the method again on the children of the root
	 * count(n as root) = min(n.left_path_len, n.top_path_len)
	 * I didn't end up using this formula, I just wrote it to be safe!
	 * @param root of the tree
	 */
	public void count_iso_triangle2(Node root) throws NullPointerException{
		if(root == null) {
			throw new NullPointerException("There can't be triangles if the tree is empty!");
		}
		else {
			int leftcount = 0;
			int rightcount = 0;
			Node leftpoint = root;
			Node rightpoint = root;
			
			
			//traversing down the left side of the tree to check for triangles
			while(leftpoint.l_child != null || rightpoint.r_child != null) {
				if(leftpoint.l_child != null && leftpoint == root) {
					leftpoint = leftpoint.l_child;
					rightpoint = leftpoint;
					leftcount++;
					//System.out.println("I get here");
				}
				else if(rightpoint.r_child != null) {
					rightpoint = rightpoint.r_child;
					rightcount++;
					if(leftcount == rightcount) {
						total_iso_triangle++;
					}
					//System.out.println("I get here");
				}
				else if(rightpoint.r_child == null) {
					leftpoint = leftpoint.l_child;
					rightpoint = leftpoint;
					leftcount++;
					rightcount = 0;
				}
			}
			
			leftpoint = root;
			rightpoint = root;
			leftcount = 0;
			rightcount = 0;
			
			//traversing down the right side of the tree to check for triangles
			while(rightpoint.r_child != null || leftpoint.l_child != null) {
				if(rightpoint.r_child != null && rightpoint == root) {
					rightpoint = rightpoint.r_child;
					leftpoint = rightpoint;
					rightcount++;
				}
				else if(leftpoint.l_child != null) {
					leftpoint = leftpoint.l_child;
					leftcount++;
					if(leftcount == rightcount) {
						total_iso_triangle++;
					}
					//System.out.println("I get here");
				}
				else if(leftpoint.l_child == null) {
					rightpoint = rightpoint.r_child;
					leftpoint = rightpoint;
					rightcount++;
					leftcount = 0;
				}
			}
			
			if(root.l_child != null) {
				count_iso_triangle2(root.l_child);
			}
			else if(root.r_child != null) {
				count_iso_triangle2(root.r_child);
			}
		}
	}
	
	/*
	 * test method for the count iso triangle class.
	 */
	public void test_count_iso_triangle2() {
		IsoTriangle test = new IsoTriangle();
		Node root = test.build_tree_int();
		test.count_iso_triangle2(root);
		System.out.println("Total number of Type-2 and Type-3 iso triangles are (test case 1): " + test.total_iso_triangle);
		IsoTriangle test2 = new IsoTriangle();
		Node root2 = test.build_tree_int2();
		test2.count_iso_triangle2(root2);
		System.out.println("Total number of Type-2 and Type-3 iso triangles are (test case 2): " + test2.total_iso_triangle); 
	}
	
	public static void main(String[] args) {
		IsoTriangle test = new IsoTriangle();
		test.test_count_iso_triangle2();
	}
	
	
}
