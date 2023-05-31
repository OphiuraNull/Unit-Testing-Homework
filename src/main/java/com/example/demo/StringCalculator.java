package com.example.stringcalculator;

import org.springframework.stereotype.Service;

@Service 
public class StringCalculator {
	public int Add(String numbers){
		if (numbers == "") return 0;
		
		int startPos = 0;
		int seperator = numbers.indexOf(",", startPos);
		int total = 0;
		while (seperator > -1){
			total += Integer.parseInt(numbers.substring(startPos,seperator));
			startPos = seperator+1;
			seperator = numbers.indexOf(",", startPos);
		}	
		total += Integer.parseInt(numbers.substring(startPos));
		return total;
		
		
		/*
		if (!numbers.contains(",")){
			return Integer.parseInt(numbers);
		}
		else {
			int a = Integer.parseInt(numbers.substring(0,numbers.indexOf(",")));
			int b = Integer.parseInt(numbers.substring(numbers.indexOf(",")+1));
			return a + b;
		}	
		*/
	}
	
}	
