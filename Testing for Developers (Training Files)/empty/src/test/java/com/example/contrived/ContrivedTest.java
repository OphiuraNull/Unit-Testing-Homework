package com.example.contrived;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ContrivedTest {
	
	@InjectMocks
	Contrived contrived;

	@Test
	void emptyTest() {
	}
	
	
	@Test
	void testConcatenateValid(){
		String one = "Happy";
		String two = "Kitty";
		String joined = "HappyKitty";
		String response = contrived.concatenate(one,two);
		assertEquals(joined, response);
	}
	
	
	@Test
	void testConcatenateOneNull(){
		String one = null;
		String two = "Kitty";
		
		Exception exception = assertThrows(IllegalArgumentException.class, ()->{
			contrived.concatenate(one,two);
		});
		
		String expectedMessage = "one";
		String actualMessage = exception.getMessage();
		
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testConcatenateTwoNull(){
		String one = "Kitty";
		String two = null;
		
		Exception exception = assertThrows(IllegalArgumentException.class, ()->{
			contrived.concatenate(one,two);
		});
		
		String expectedMessage = "two";
		String actualMessage = exception.getMessage();
		
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testConcatenateOneIgnore(){
		String one = "IGNORE";
		String two = "Kitty";
		
		Exception exception = assertThrows(IllegalArgumentException.class, ()->{
			contrived.concatenate(one,two);
		});
		
		String expectedMessage = "one";
		String actualMessage = exception.getMessage();
		
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testConcatenateTwoIgnore(){
		String one = "Kitty";
		String two = "IGNORE";
		
		Exception exception = assertThrows(IllegalArgumentException.class, ()->{
			contrived.concatenate(one,two);
		});
		
		String expectedMessage = "two";
		String actualMessage = exception.getMessage();
		
		assertEquals(expectedMessage, actualMessage);
	}
	
}
