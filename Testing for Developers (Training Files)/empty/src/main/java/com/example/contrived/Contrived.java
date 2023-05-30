package com.example.contrived;

import org.springframework.stereotype.Service;

@Service
public class Contrived {
	public String concatenate(String one, String two) {
		if(one == null || "IGNORE".equals(one)) {
			throw new IllegalArgumentException("one");
		}
		if(two == null || "IGNORE".equals(two)) {
			throw new IllegalArgumentException("two");
		} 
		return one + two;
	}

}
