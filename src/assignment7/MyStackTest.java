package assignment7;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyStackTest
{
	private MyStack<Integer> testStack, emptyStack, oneElementStack;

	@Before
	public void setUp() throws Exception
	{
		// stack with 50 elements
		testStack = new MyStack<Integer>();
		for(int i = 0; i < 50; i++)
			testStack.push(i);
		
		// empty stack
		emptyStack = new MyStack<Integer>();
		
		// oneElementStack
		oneElementStack = new MyStack<Integer>();
		oneElementStack.push(5);
		
	}

	@After
	public void tearDown() throws Exception
	{
	}


	@Test
	public void testClear()
	{
		testStack.clear();
		assertTrue(testStack.size() == 0);
	}
	
	@Test
	public void testClearEmptySet()
	{
		emptyStack.clear();
		assertTrue(emptyStack.size() == 0);
	}
	
	@Test
	public void testClearOneElementSet()
	{
		oneElementStack.clear();
		assertTrue(oneElementStack.size() == 0);
	}

	@Test
	public void testIsEmpty()
	{
		assertFalse(testStack.isEmpty());
	}
	
	@Test
	public void testIsEmptyEmptySet()
	{
		assertTrue(emptyStack.isEmpty());
	}
	
	@Test
	public void testIsEmptyOneElementStack()
	{
		assertFalse(oneElementStack.isEmpty());
	}

	@Test
	public void testPeek()
	{
		assertTrue(testStack.peek() == 49);
	}
	
	
	@Test (expected = NoSuchElementException.class)
	public void testPeekEmptySet()
	{
		emptyStack.peek();
	}
	
	@Test
	public void testPeekOneElementSet()
	{
		assertTrue(oneElementStack.peek() == 5);
	}

	@Test
	public void testPop()
	{
		assertTrue(testStack.pop() == 49);
		assertFalse(testStack.peek() == 49);
		assertTrue(testStack.size() == 49);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testPopEmptySet()
	{
		emptyStack.pop();
	}
	
	@Test
	public void testPopOneElementSet()
	{
		assertTrue(oneElementStack.pop() == 5);
		assertTrue(oneElementStack.isEmpty());
	}

	@Test
	public void testPush()
	{
		testStack.push(127);
		assertTrue(testStack.peek() == 127);
		assertTrue(testStack.size() == 51);
	}
	
	@Test
	public void testPushEmptySet()
	{
		emptyStack.push(127);
		assertTrue(emptyStack.peek() == 127);
		assertTrue(emptyStack.size() == 1);
		assertFalse(emptyStack.isEmpty());
	}
	
	@Test
	public void testPushOneElementSet()
	{
		oneElementStack.push(127);
		assertTrue(oneElementStack.peek() == 127);
		assertTrue(oneElementStack.size() == 2);
	}

	@Test
	public void testSize()
	{
		assertTrue(testStack.size() == 50);
		assertFalse(testStack.isEmpty());
	}
	
	@Test
	public void testSizeEmptySet()
	{
		assertTrue(emptyStack.size() == 0);
		assertTrue(emptyStack.isEmpty());
	}
	
	@Test
	public void testSizeOneElementSet()
	{
		assertTrue(oneElementStack.size() == 1);
		assertFalse(oneElementStack.isEmpty());
	}

}
