package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class BalancedParenTreeTest {

	@Test
	public void testCaseOne() {
		BalancedParenTree tree = new BalancedParenTree("(())()");
		tree.print();
		assertEquals(tree.print(),"(())()");
	}
	
	@Test
	public void testCaseTwo() {
		AdvBalancedParenTree tree = new AdvBalancedParenTree("[()]()");
		tree.print();
		assertEquals(tree.print(),"[()]()");
	}
	
	@Test
	public void testCaseThree() {
		AdvBalancedParenTree tree = new AdvBalancedParenTree("[()]()");
		tree.print();
		assertEquals(tree.isBalanced("[()]()"), true);
	}
	
	@Test
	public void testCaseFour() {
		AdvBalancedParenTree tree = new AdvBalancedParenTree("([])()");
		tree.print();
		assertEquals(tree.isBalanced("([])()"), false);
	}
	

}
