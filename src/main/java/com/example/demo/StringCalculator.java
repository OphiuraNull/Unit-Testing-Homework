//Command to run jacoco tests:
//mvn clean package jacoco:report

package com.example.stringcalculator;

import org.springframework.stereotype.Service;

@Service 
public class StringCalculator {
	
	//public static String[] SEPERATORS = {","}; Not useful yet
	
	public boolean IsNumber(char num){
		try {
			int i = Integer.parseInt(Character.toString(num));
		}
		catch (NumberFormatException error){
			return false;
		}	
		
		return true;
	}	
	
	public int NextSeperator(String numbers, int startPosition){
		int position = startPosition;
		while (position < numbers.length() && IsNumber(numbers.charAt(position))){
			position++;
		}
		if (position == numbers.length()){
			return -1; 		//reached end of string
		}
		return position;
	}	
	
	public int Add(String numbers){
		if (numbers == "") return 0;
		
		int startPos = 0;
		int seperator = NextSeperator(numbers, startPos);
		int total = 0;
		while (seperator > -1){
			total += Integer.parseInt(numbers.substring(startPos,seperator));
			startPos = seperator+1;
			seperator = NextSeperator(numbers, startPos);
		}	
		total += Integer.parseInt(numbers.substring(startPos));
		return total;
		
	}
	
}	
