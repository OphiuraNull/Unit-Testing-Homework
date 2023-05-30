package com.example.verhoeff;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VerhoeffTest {

	@InjectMocks
	Verhoeff verhoeff;
	
	@Test
	void emptyTest() {
	}
	
	@Test
	void testValidateVerhoeff(){
		String number = "2363";	
		boolean response = verhoeff.validateVerhoeff(number);
		assertTrue(response);
	}	
	
	@Test
	void testInvalidateVerhoeff(){
		String number = "2364";	
		boolean response = verhoeff.validateVerhoeff(number);
		assertFalse(response);
	}	
	
	@Test
	void testGenerateVerhoeff(){
		String number = "236";	
		String checkDigit = "3";
		String response = verhoeff.generateVerhoeff(number);
		assertEquals(checkDigit, response);
	}	
}
