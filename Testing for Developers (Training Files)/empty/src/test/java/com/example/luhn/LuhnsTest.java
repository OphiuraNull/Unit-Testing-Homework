package com.example.luhn;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LuhnsTest {
	
	@InjectMocks
	Luhns luhns;

	@Test
	void emptyTest() {
	}
	
	@Test
	void testValidLuhn(){
		String cardNo = "79927398713";	
		boolean response = luhns.checkLuhn(cardNo);
		assertTrue(response);
	}	
	
	@Test
	void testInvalidLuhn(){
		String cardNo = "79927398718";	
		boolean response = luhns.checkLuhn(cardNo);
		assertFalse(response);
	}	
	
	@Test
	void testCalculateLuhnDigit(){
		String cardNo = "7992739871";
		int checkDigit = 3;
		int response = luhns.calculateDigit(cardNo);
		assertEquals(checkDigit, response);
		
	}
}