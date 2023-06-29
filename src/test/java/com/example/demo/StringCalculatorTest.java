package com.example.stringcalculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StringCalculatorTest {
	
	@InjectMocks
	StringCalculator calc;
	
	private static int expectedCallCount = 0;
	
	@Test
	void contextLoads() {
	}

	@Test
	void testEmptyAdd(){
		expectedCallCount++;
		String num = "";	
		int response = calc.Add(num);
		assertEquals(0, response);
	}
		
	@Test
	void testSingleAdd(){
		expectedCallCount++;
		String num = "7";	
		int response = calc.Add(num);
		assertEquals(7, response);
	}
	
	@Test
	void testPairAdd(){
		expectedCallCount++;
		String num = "7,4";	
		int response = calc.Add(num);
		assertEquals(11, response);
	}
	
	@Test
	void testTripleAdd(){
		expectedCallCount++;
		String num = "7,14,9";	
		int response = calc.Add(num);
		assertEquals(30, response);
	}
	
	@Test
	void testQuadAdd(){
		expectedCallCount++;
		String num = "7,14,9,21";	
		int response = calc.Add(num);
		assertEquals(51, response);
	}
	
	@Test
	void testNewlineAdd(){
		expectedCallCount++;
		String num = "7\n14";	
		int response = calc.Add(num);
		assertEquals(21, response);
	}
	
	@Test
	void testNewlineAddDifferentSeperators(){
		expectedCallCount++;
		String num = "7\n14,31";	
		int response = calc.Add(num);
		assertEquals(52, response);
	}
	
	//TESTS FOR DATA VALIDATION - NOT PART OF SPEC (but using regex makes me feel like I'm cheating)
	@Test
	void testInvalidExtraSeperator(){
		expectedCallCount++;
		String num = "7,14\n,31";	
		try{
			int response = calc.Add(num);
		}	
		catch(RuntimeException e){
			assertEquals(StringCalculator.genericError, e.getMessage());
			return;
		}	
		fail();
	}
	
	@Test
	void testInvalidCharacter(){
		expectedCallCount++;
		String num = "7,14b31";	
		try{
			int response = calc.Add(num);
		}	
		catch(RuntimeException e){
			assertEquals(StringCalculator.genericError, e.getMessage());
			return;
		}	
		fail();
	}
	
	@Test
	void testInvalidDelimiter(){
		expectedCallCount++;
		String num = "7;14,31";	
		try{
			int response = calc.Add(num);
		}	
		catch(RuntimeException e){
			assertEquals(StringCalculator.genericError, e.getMessage());
			return;
		}	
		fail();
	}
	
	@Test
	void testInvalidCustomSeperatorA(){
		expectedCallCount++;
		String num = "//;\n7,;14;31";	
		try{
			int response = calc.Add(num);
		}	
		catch(RuntimeException e){
			assertEquals(StringCalculator.seperatorError, e.getMessage());
			return;
		}	
		fail();
	}
	
	@Test
	void testInvalidCustomSeperatorB(){
		expectedCallCount++;
		String num = "//;\n7;14;31;";	
		try{
			int response = calc.Add(num);
		}	
		catch(RuntimeException e){
			assertEquals(StringCalculator.seperatorError, e.getMessage());
			return;
		}	
		fail();
	}
	
	@Test
	void testNegativeNumber(){
		expectedCallCount++;
		String num = "4,-3";	
		try{
			int response = calc.Add(num);
		}	
		catch(RuntimeException e){
			assertEquals(StringCalculator.negativeError+" -3", e.getMessage());
			return;
		}	
		fail();
	}	
	
	@Test
	void testMultiNegativeNumbers(){
		expectedCallCount++;
		String num = "4,-3,-1,-13,5,-6";	
		try{
			int response = calc.Add(num);
		}	
		catch(RuntimeException e){
			assertEquals(StringCalculator.negativeError+" -3 -1 -13 -6", e.getMessage());
			return;
		}	
		fail();
	}	
	//END OF VALIDATION TESTS
	
	@Test
	void testCustomSeperatorA(){
		expectedCallCount++;
		String num = "//;\n7,14;31";	
		int response = calc.Add(num);
		assertEquals(52, response);
	}
	
	@Test
	void testCustomSeperatorB(){
		expectedCallCount++;
		String num = "//!\n7!14!31";	
		int response = calc.Add(num);
		assertEquals(52, response);
	}
	
	@Test
	void testTimesCalled(){
		assertEquals(expectedCallCount, StringCalculator.getCalledCount());
	}

}
