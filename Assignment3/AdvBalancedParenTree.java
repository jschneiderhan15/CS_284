package test;

import java.util.ArrayList;

import java.util.Stack;

public class AdvBalancedParenTree {
	
	private class Node{
		private String terminal;
		private Node[] children;
		
		/**
		 * constructor for the node class that takes in a terminal and children.
		 * @param thisTerm terminal, or the "data" of the node
		 * @param thisChild children of the current node
		 */
		private Node(String thisTerm, Node[] thisChild) {
			terminal = thisTerm;
			children = thisChild;
		}
		
		/**
		 * default constructor for node that creates an empty node with no children
		 */
		private Node() {
			terminal = null;
			children = null;
		}

	}
	
	public Node root;
	public Node currentNode;
	public String parenth;
	public ArrayList<Character> parenthList = new ArrayList<Character>();
	
	/**
	 * constructor that takes in a string of parenthesis
	 * @param string of balanced parenthesis
	 */
	public AdvBalancedParenTree(String str) {
		parenth = str;
		char[] parenthL = parenth.toCharArray();
		for(int i = 0; i < parenthL.length; i++) {
			parenthList.add(i, parenthL[i]);
		}
		parse(str);
	}
	
	/**
	 * returns the given parenthesis into mountain format
	 * @param paren_str string of balanced parentheses
	 * @return mountain format of the given parentheses
	 */
	public String getMountain(String paren_str) {
		char[] collect = paren_str.toCharArray();
		String mountain = "0";
		int count = 0;
		for(int i = 0; i < collect.length; i++)
		{
			if(collect[i] == '(' || collect[i] == '[') {
				count++;
				mountain += count;
			}
			else if(collect[i] == ')' || collect[i] == ']') {
				count--;
				mountain += count;
			}
		}
		return mountain;
	}
	
	/**
	 * parses parentheses into a tree. uses parseHelper() function
	 * @param paren_str string of balanced parentheses
	 */
	public void parse(String paren_str) {
		if(!this.isBalanced(paren_str))
			System.out.println("ERROR: PARENTHESES ARE UNBALANCED.");
		String mountain = this.getMountain(paren_str);
		Node thisRoot = new Node(null, parseHelper(mountain));
		this.root = thisRoot;
	}
	
	/**
	 * helper function for the parse() function
	 * @param mtn_str mountain format of balanced parentheses
	 * @return the balanced parentheses, formatted into a tree
	 */
	public Node[] parseHelper(String mtn_str) {
		//System.out.println(parenthList);
		char[] collect = mtn_str.toCharArray();
		Character leftSoft = '(';
		Character rightSoft = ')';
		Character leftHard = '[';
		Character rightHard = ']';
		ArrayList<Integer> zeroes = new ArrayList<Integer>();
		for(int i = 1; i < collect.length - 1; i++) {
			if(collect[i] == '0') {
				zeroes.add(i);
			}
		}
		if(zeroes.size() == 0) {
			if(mtn_str.length() == 1) {
				return null;
			}
			if(parenthList.get(0) == '(') {
				parenthList.remove(leftSoft);
				parenthList.remove(rightSoft);
				Node leftPar = new Node("(", null);
				Node child = new Node(null, parseHelper(mtn_str.substring(1, mtn_str.length() - 1 )));
				Node rightPar = new Node(")", null);
				Node[] group = new Node[3];
				group[0] = leftPar;
				group[1] = child;
				group[2] = rightPar;
				return group;
			}
			else {
				parenthList.remove(leftHard);
				parenthList.remove(rightHard);
				Node leftPar = new Node("[", null);
				Node child = new Node(null, parseHelper(mtn_str.substring(1, mtn_str.length() - 1 )));
				Node rightPar = new Node("]", null);
				Node[] group = new Node[3];
				group[0] = leftPar;
				group[1] = child;
				group[2] = rightPar;
				return group;
			}
		} 
		else {
			Node[] group = new Node[zeroes.size() + 1];
			group[0] = new Node(null, parseHelper(mtn_str.substring(0, zeroes.get(0) + 1)));
			int point = 1;
			while(point < zeroes.size()) {
				group[point] = new Node(null, parseHelper(mtn_str.substring(zeroes.get(point - 1), zeroes.get(point) + 1)));
				point++;
			}
			group[zeroes.size()] = new Node(null, parseHelper(mtn_str.substring(zeroes.get(point - 1), collect.length)));
			return group;
		}
		
	}
	
	/**
	 * checks to see if the given string of parentheses is balanced.
	 * @param paren_str string of parentheses (includes () and []).
	 * @return whether or not the string is balanced
	 */
	public boolean isBalanced(String paren_str) {
		Stack<Integer> stack = new Stack<Integer>();
		int index = 0;
		boolean soft = false;
		while(index < paren_str.length()) {
			char nextCh = paren_str.charAt(index);
			if(nextCh == '(') {
				stack.push(1);
				soft = true;
			}
			else if(nextCh == ')') {
				try {
					stack.pop();
					soft = false;
				} catch (Exception e) {
					return false;
				}
			}
			else if(nextCh == '[' && !soft) {
				stack.push(1);
			}
			else if (nextCh == ']' && !soft) {
				try {
					stack.pop();
				} catch (Exception e) {
					return false;
				}
			}
			if(nextCh == '[' && soft) {
				return false;
			}
			index++;
		}
		return stack.isEmpty();
	}
	
	/**
	 * helper function for the print() function
	 * @param root the root of the tree looking to be printed
	 * @return the tree formatted into balanced parentheses again
	 */
	public String printHelp(Node root) {
		String tree = "";
		if(root.terminal != null) {
			tree += root.terminal;
		}
		else if(root.children == null) {

		}
		else {
			for(int i = 0; i < root.children.length; i++) {
				tree += printHelp(root.children[i]);
			}
		}
		return tree;
	}
	
	/**
	 * prints the BalancedParenTree into a string of balanced parentheses
	 * @return string of balanced parentheses
	 */
	public String print() {
		return printHelp(this.root);	
	}
}
