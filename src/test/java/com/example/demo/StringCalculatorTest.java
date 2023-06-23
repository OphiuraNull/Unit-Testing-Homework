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
	
	@Test
	void contextLoads() {
	}

	@Test
	void testEmptyAdd(){
		String num = "";	
		int response = calc.Add(num);
		assertEquals(0, response);
	}
		
	@Test
	void testSingleAdd(){
		String num = "7";	
		int response = calc.Add(num);
		assertEquals(7, response);
	}
	
	@Test
	void testPairAdd(){
		String num = "7,4";	
		int response = calc.Add(num);
		assertEquals(11, response);
	}
	
	@Test
	void testTripleAdd(){
		String num = "7,14,9";	
		int response = calc.Add(num);
		assertEquals(30, response);
	}
	
	@Test
	void testQuadAdd(){
		String num = "7,14,9,21";	
		int response = calc.Add(num);
		assertEquals(51, response);
	}
	
	@Test
	void testNewlineAdd(){
		String num = "7\n14";	
		int response = calc.Add(num);
		assertEquals(21, response);
	}
	
	@Test
	void testNewlineAddDifferentSeperators(){
		String num = "7\n14,31";	
		int response = calc.Add(num);
		assertEquals(52, response);
	}
	
	//TESTS FOR DATA VALIDATION - NOT PART OF SPEC (but using regex makes me feel like I'm cheating)
	@Test
	void testInvalidExtraSeperator(){
		String num = "7,14\n,31";	
		int response = calc.Add(num);
		assertEquals(-1, response);
	}
	
	@Test
	void testInvalidCharacter(){
		String num = "7,14b31";	
		int response = calc.Add(num);
		assertEquals(-1, response);
	}
	
	@Test
	void testInvalidDelimiter(){
		String num = "7;14,31";	
		int response = calc.Add(num);
		assertEquals(-1, response);
	}
	
	@Test
	void testInvalidCustomSeperator(){
		String num = "//;\n7,;14;31";	
		int response = calc.Add(num);
		assertEquals(-1, response);
	}
	//END OF VALIDATION TESTS
	
	@Test
	void testCustomSeperator(){
		String num = "//;\n7,14;31";	
		int response = calc.Add(num);
		assertEquals(52, response);
	}
}
